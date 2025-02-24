package com.springboot.videogame.controller;

import com.springboot.videogame.entity.Game;
import com.springboot.videogame.entity.User;
import com.springboot.videogame.entity.usercomment.UserComment;
import com.springboot.videogame.service.UserFavoriteGameService;
import com.springboot.videogame.service.UserCommentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private UserFavoriteGameService userFavoriteGameService;
    @Autowired
    private UserCommentService userCommentService;

    @GetMapping("/profile")
    public String getProfile(Model model, HttpSession session) {
        // Session'dan user objesini al
        User user = (User) session.getAttribute("user");

        if (user == null) {
            // Eğer session'da user objesi yoksa, kullanıcıyı login sayfasına yönlendir
            return "redirect:/games";
        }

        // Kullanıcının favori oyunlarını getir
        List<Game> favoriteGames = userFavoriteGameService.getFavoriteGamesByUserId(user.getId());

        // Favori oyunları modele ekle
        model.addAttribute("favoriteGames", favoriteGames);

        // Kullanıcı bilgilerini de modele ekleyebilirsiniz
        model.addAttribute("user", user);

        List<UserComment> comments = userCommentService.getUserCommentByUserId(user.getId());

        model.addAttribute("userComments", comments);

        return "profile"; // Profil sayfasını döndür
    }


    @PostMapping("/profile/remove")
    public String removeFavorite(@RequestParam Integer userId, @RequestParam Long gameId, RedirectAttributes redirectAttributes) {
        userFavoriteGameService.removeFavoriteGame(userId, gameId);

        redirectAttributes.addFlashAttribute("message", "Game removed from favorites!");
        return "redirect:/profile";
    }

    @PostMapping("/profile/add")
    public String addFavoriteGame(@RequestParam Integer userId, @RequestParam Long gameId, RedirectAttributes redirectAttributes) {
        userFavoriteGameService.addFavoriteGame(userId, gameId);

        redirectAttributes.addFlashAttribute("message", "Game added to favorites!");
        return "redirect:/profile";
    }



}
