package com.springboot.videogame.dao;

import com.springboot.videogame.entity.userfavoritegame.UserFavoriteGame;

import java.util.List;

public interface FavGamesDao {
    void addFavoriteGame(UserFavoriteGame userFavoriteGame);
    void removeFavoriteGame(Integer userId, Long gameId);
    List<UserFavoriteGame> getFavoritesByUser(Integer userId);
}

