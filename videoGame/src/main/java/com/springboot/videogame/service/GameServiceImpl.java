package com.springboot.videogame.service;


import com.springboot.videogame.entity.Game;
import com.springboot.videogame.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    @Value("${api.key}")
    private String apiKey;

    private final String BASE_URL = "https://api.rawg.io/api/games";

    @Autowired
    private GameRepository gameRepository;

    @Override
    @Transactional
    public List<Map<String, Object>> getPopularGames() {
        List<Map<String, Object>> allGames = new ArrayList<>();

        for (int page = 1; page <= 4; page++) { // Fetch 2 pages (25 items each)
            String url = BASE_URL + "?key=" + apiKey + "&page=" + page + "&page_size=25";
            RestTemplate restTemplate = new RestTemplate();

            Map<String, Object> response = restTemplate.getForObject(url, Map.class);
            if (response != null && response.containsKey("results")) {
                List<Map<String, Object>> results = (List<Map<String, Object>>) response.get("results");
                results.forEach(gameData -> saveGameToDatabase(gameData));
                allGames.addAll(results);
            }
        }

        return allGames;
    }

    @Override
    public Game findById(Long gameId) {
        return gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found with ID: " + gameId));
    }

    private void saveGameToDatabase(Map<String, Object> gameData) {
        try {
            Long id = ((Number) gameData.get("id")).longValue();
            Game game = gameRepository.findById(id).orElse(new Game());

            // Eğer oyun zaten varsa güncelle
            if (game.getId() != null) {
                if(game.getGenre() == null) { // Sadece genre eksikse güncelle
                    game.setGenre(extractGenres(gameData));
                }
            }
            // Yeni oyun oluştur
            else {
                game.setId(id);
                game.setName((String) gameData.get("name"));
                game.setBackgroundImage((String) gameData.get("background_image"));

                // Rating işleme
                Object rating = gameData.get("rating");
                if (rating != null) {
                    game.setRating(((Number) rating).doubleValue());
                }

                game.setGenre(extractGenres(gameData)); // Genre ekstraksiyonu
            }

            gameRepository.save(game);

        } catch (Exception e) {
            System.err.println("Error saving game: " + e.getMessage());
        }
    }

    // Genre listesini işleyen yardımcı metod
    private String extractGenres(Map<String, Object> gameData) {
        List<Map<String, Object>> genres = (List<Map<String, Object>>) gameData.get("genres");
        if (genres == null || genres.isEmpty()) return "Unknown";

        return genres.stream()
                .map(genre -> (String) genre.get("name"))
                .filter(name -> name != null)
                .collect(Collectors.joining(", "));
    }
}