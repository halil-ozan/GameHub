package com.springboot.videogame.service;

import com.springboot.videogame.dao.UserCommentsDao;
import com.springboot.videogame.dao.UserDao;

import com.springboot.videogame.entity.usercomment.UserComment;
import com.springboot.videogame.entity.userfavoritegame.UserFavoriteGame;
import com.springboot.videogame.repository.GameRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCommentServiceImpl implements UserCommentService {

    private final UserCommentsDao userCommentsDao;
    private final UserDao userDao;
    private final GameRepository gameDao;

    public UserCommentServiceImpl(UserCommentsDao userCommentsDao, UserDao userDao, GameRepository gameDao) {
        this.userCommentsDao = userCommentsDao;
        this.userDao = userDao;
        this.gameDao = gameDao;
    }

    @Override
    public List<UserComment> getUserCommentByGameId(Long gameId) {
        return userCommentsDao.getUserCommentByGameId(gameId);
    }

    @Override
    public List<UserComment> getUserCommentByUserId(Integer userId) {
        return userCommentsDao.getUserCommentByUserId(userId);
    }


    @Override
    public void addUserComment(UserComment userComment) {

    }

    @Override
    public void removeUserCommentByUserId(String userId) {

    }
}
