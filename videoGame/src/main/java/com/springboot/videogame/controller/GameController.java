package com.springboot.videogame.controller;

import com.springboot.videogame.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.Map;

@Controller
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/games")
    public String showGames(Model model) {
        List<Map<String, Object>> games = gameService.getPopularGames();
        model.addAttribute("games", games);
        return "games";
    }
}

