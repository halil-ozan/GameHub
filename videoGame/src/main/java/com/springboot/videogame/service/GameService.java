package com.springboot.videogame.service;

import java.util.List;
import java.util.Map;

public interface GameService {

    List<Map<String, Object>> getPopularGames();

}

