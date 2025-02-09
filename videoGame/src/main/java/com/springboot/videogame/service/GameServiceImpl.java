package com.springboot.videogame.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;

@Service
public class GameServiceImpl implements GameService {

 // RAWG API Key
    @Value("${api.key}")
    private String apiKey;

    private final String BASE_URL = "https://api.rawg.io/api/games";

    @Override
    public List<Map<String, Object>> getPopularGames() {
        String url = BASE_URL + "?key=" + apiKey;
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        return (List<Map<String, Object>>) response.get("results");
    }
}
