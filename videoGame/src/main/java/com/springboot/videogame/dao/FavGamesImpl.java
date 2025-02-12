package com.springboot.videogame.dao;

import com.springboot.videogame.entity.userfavoritegame.UserFavoriteGame;
import com.springboot.videogame.entity.userfavoritegame.UserFavoriteGameId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class FavGamesImpl implements FavGamesDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void addFavoriteGame(UserFavoriteGame userFavoriteGame) {
        entityManager.persist(userFavoriteGame);
    }

    @Override
    @Transactional
    public void removeFavoriteGame(Integer userId, Long gameId) {
        UserFavoriteGameId id = new UserFavoriteGameId(userId, gameId);
        UserFavoriteGame favoriteGame = entityManager.find(UserFavoriteGame.class, id);
        if (favoriteGame != null) {
            entityManager.remove(favoriteGame);
        }
    }

    @Override
    public List<UserFavoriteGame> getFavoritesByUser(Integer userId) {
        return entityManager.createQuery("SELECT ufg FROM UserFavoriteGame ufg WHERE ufg.user.id = :userId", UserFavoriteGame.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}