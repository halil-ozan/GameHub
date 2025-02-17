package com.springboot.videogame.service;

import com.springboot.videogame.entity.Game;

import java.util.List;
import java.util.Map;

public interface GameService {

    List<Map<String, Object>> getPopularGames();

    Game findById(Long gameId);
}

