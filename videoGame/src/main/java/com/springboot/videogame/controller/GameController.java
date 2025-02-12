package com.springboot.videogame.controller;

import com.springboot.videogame.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/games")
    public String showGames(Model model) {
        List<Map<String, Object>> games = gameService.getPopularGames();

        // Tüm genre'leri topla
        Set<String> allGenres = games.stream()
                .flatMap(game -> {
                    List<Map<String, Object>> genres = (List<Map<String, Object>>) game.get("genres");
                    return genres != null ?
                            genres.stream()
                                    .map(genre -> (String) genre.get("name"))
                                    .filter(Objects::nonNull) :
                            Stream.empty();
                })
                .collect(Collectors.toCollection(() -> new TreeSet<>()));

        model.addAttribute("games", games);
        model.addAttribute("allGenres", allGenres);
        return "games";
    }
}

