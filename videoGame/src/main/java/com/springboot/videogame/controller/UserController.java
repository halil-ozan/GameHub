package com.springboot.videogame.controller;

import com.springboot.videogame.dao.UserDao;
import com.springboot.videogame.entity.User;
import com.springboot.videogame.entity.usercomment.UserComment;
import com.springboot.videogame.service.UserCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserCommentService userCommentService;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/profile/{id}")
    public String getUserProfile(@PathVariable Integer id, Model model) {
        User user = userDao.findById(id);
        List<UserComment> comments = userCommentService.getUserCommentByUserId(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("userComments", comments);
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
