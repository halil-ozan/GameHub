package com.springboot.videogame.service;

import com.springboot.videogame.dao.FavGamesDao;
import com.springboot.videogame.dao.UserDao;
import com.springboot.videogame.entity.Game;
import com.springboot.videogame.entity.User;
import com.springboot.videogame.entity.userfavoritegame.UserFavoriteGame;
import com.springboot.videogame.entity.userfavoritegame.UserFavoriteGameId;
import com.springboot.videogame.repository.GameRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserFavoriteGameServiceImpl implements UserFavoriteGameService {

    private final FavGamesDao userFavoriteGameDao;
    private final UserDao userDao;
    private final GameRepository gameDao;

    @Autowired
    public UserFavoriteGameServiceImpl(FavGamesDao userFavoriteGameDao, UserDao userDao, GameRepository gameDao) {
        this.userFavoriteGameDao = userFavoriteGameDao;
        this.userDao = userDao;
        this.gameDao = gameDao;
    }

    @Override
    public List<Game> getFavoriteGamesByUserId(Integer userId) {
        return userFavoriteGameDao.getFavoritesByUser(userId).stream()
                .map(UserFavoriteGame::getGame)
                .toList();
    }

    @Override
    @Transactional
    public void addFavoriteGame(Integer userId, Long gameId) {
        User user = userDao.findById(userId);
        Optional<Game> game = gameDao.findById(gameId);

        if (user == null || game.isEmpty()) {
            throw new RuntimeException("User or Game not found!");
        }

        UserFavoriteGameId id = new UserFavoriteGameId(userId, gameId);
        UserFavoriteGame favoriteGame = new UserFavoriteGame(id, user, game.orElse(null));

        userFavoriteGameDao.addFavoriteGame(favoriteGame);
    }

    @Override
    @Transactional
    public void removeFavoriteGame(Integer userId, Long gameId) {
        userFavoriteGameDao.removeFavoriteGame(userId, gameId);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.findById(userId);
    }

    @Override
    public User findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }
}

