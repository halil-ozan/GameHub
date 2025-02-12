package com.springboot.videogame.service;

import com.springboot.videogame.entity.Game;
import com.springboot.videogame.entity.User;

import java.util.List;

public interface UserFavoriteGameService {
    List<Game> getFavoriteGamesByUserId(Integer userId);
    void addFavoriteGame(Integer userId, Long gameId);
    void removeFavoriteGame(Integer userId, Long gameId);
    User getUserById(Integer userId);

    User findByUserName(String userName);
}
