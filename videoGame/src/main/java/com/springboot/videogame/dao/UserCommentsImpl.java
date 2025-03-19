package com.springboot.videogame.dao;

import com.springboot.videogame.entity.usercomment.UserComment;
import com.springboot.videogame.entity.userfavoritegame.UserFavoriteGame;
import com.springboot.videogame.entity.userfavoritegame.UserFavoriteGameId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserCommentsImpl implements UserCommentsDao {

    @PersistenceContext
    private EntityManager entityManager;

    public UserCommentsImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void addComment(UserComment userComment) {
        entityManager.persist(userComment);
    }

    @Override
    @Transactional
    public void removeUserComment(Long Id) {
        UserComment comment = entityManager.find(UserComment.class, Id);
        if (comment != null) {
            entityManager.remove(comment);
        }
    }


    @Override
    public List<UserComment> getUserCommentByGameId(Long gameId) {
        return entityManager.createQuery(
                        "SELECT uc FROM UserComment uc WHERE uc.game.id = :gameId",
                        UserComment.class)
                .setParameter("gameId", gameId)
                .getResultList();
    }

    @Override
    public List<UserComment> getUserCommentByUserId(Integer userId) {
        return entityManager.createQuery(
                        "SELECT uc FROM UserComment uc WHERE uc.user.id = :userId",
                        UserComment.class)
                .setParameter("userId", userId)
                .getResultList();
    }


}
