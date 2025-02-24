package com.springboot.videogame.controller;

import com.springboot.videogame.entity.Game;
import com.springboot.videogame.entity.usercomment.UserComment;
import com.springboot.videogame.service.GameService;
import com.springboot.videogame.service.UserCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class GameController {

    private final GameService gameService;
    private final UserCommentService userCommentService;

    public GameController(GameService gameService, UserCommentService userCommentService) {
        this.gameService = gameService;
        this.userCommentService = userCommentService;
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
    @GetMapping("/game-detail/{gameId}")
    public String getGameDetail(@PathVariable Long gameId, Model model) {
        Game game = gameService.findById(gameId);

        if (game == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        }

        model.addAttribute("game", game);

        List<UserComment> comments = userCommentService.getUserCommentByGameId(gameId);
        if (comments == null) {
            comments = new ArrayList<>(); // Prevent null issues
        }
        model.addAttribute("userComments", comments);

        return "game-detail";
    }


    /*@GetMapping("/game-detail/comment/{gameId}")
    public String getGameDetailComment(@PathVariable Long gameId, Model model) {
        Game game = gameService.findById(gameId);

        // Yorumları gameId'ye göre al
        List<UserComment> gameComments = userCommentService.getUserCommentByGameId(gameId);

        // Model'e verileri ekle
        model.addAttribute("game", game);
        model.addAttribute("comments", gameComments);

        return "game-detail";
    } */

}

