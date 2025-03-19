package com.springboot.videogame.controller;

import com.springboot.videogame.dao.UserDao;
import com.springboot.videogame.entity.Game;
import com.springboot.videogame.entity.User;
import com.springboot.videogame.entity.usercomment.UserComment;
import com.springboot.videogame.service.UserFavoriteGameService;
import com.springboot.videogame.service.UserCommentService;
import com.springboot.videogame.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private UserFavoriteGameService userFavoriteGameService;
    @Autowired
    private UserCommentService userCommentService;
    @Autowired
    private UserDao userDao;

    @GetMapping("/profile")
    public String getProfile(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/games";
        }

        List<Game> favoriteGames = userFavoriteGameService.getFavoriteGamesByUserId(user.getId());

        model.addAttribute("favoriteGames", favoriteGames);

        model.addAttribute("user", user);

        List<UserComment> comments = userCommentService.getUserCommentByUserId(user.getId());

        model.addAttribute("userComments", comments);

        return "profile";
    }


    @PostMapping("/profile/remove")
    public String removeFavorite(@RequestParam Integer userId, @RequestParam Long gameId, RedirectAttributes redirectAttributes) {
        userFavoriteGameService.removeFavoriteGame(userId, gameId);

        redirectAttributes.addFlashAttribute("message", "Game removed from favorites!");
        return "redirect:/profile";
    }

    @PostMapping("/profile/comment/remove")
    public String removeComment(@RequestParam("id") Long commentId, RedirectAttributes redirectAttributes) {
        userCommentService.removeUserComment(commentId);

        redirectAttributes.addFlashAttribute("message", "Comment removed from favorites!");
        return "redirect:/profile";
    }

    @PostMapping("/profile/add")
    public String addFavoriteGame(@RequestParam Integer userId, @RequestParam Long gameId, RedirectAttributes redirectAttributes) {
        userFavoriteGameService.addFavoriteGame(userId, gameId);

        redirectAttributes.addFlashAttribute("message", "Game added to favorites!");
        return "redirect:/games";
    }




}
