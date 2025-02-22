package com.springboot.videogame.controller;

import com.springboot.videogame.dao.UserDao;
import com.springboot.videogame.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/profile/{id}")
    public String getUserProfile(@PathVariable Integer id, Model model) {
        User userOptional = userDao.findById(id);

        model.addAttribute("user", userOptional);

        return "userProfile";

    }

    @GetMapping("/profile/search")
    public String searchUserByUsername(
            @RequestParam String username,
            RedirectAttributes redirectAttributes
    ) {
        User user = userDao.findByUserName(username);

        if (user != null) {
            return "redirect:/profile/" + user.getId();
        } else {
            redirectAttributes.addFlashAttribute("error", "User not found");
            return "redirect:/home";
        }
    }


}
