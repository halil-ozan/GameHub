package com.springboot.videogame.controller;

import com.springboot.videogame.entity.Game;
import com.springboot.videogame.entity.User;
import com.springboot.videogame.service.UserFavoriteGameService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private UserFavoriteGameService userFavoriteGameService;

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

        return "profile"; // Profil sayfasını döndür
    }


}
