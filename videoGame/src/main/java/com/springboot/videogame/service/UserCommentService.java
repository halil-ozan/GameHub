package com.springboot.videogame.service;

import com.springboot.videogame.entity.usercomment.UserComment;

import java.util.List;

public interface UserCommentService {
    List<UserComment> getUserCommentByGameId(Long gameId);
    List<UserComment> getUserCommentByUserId(Integer userId);
    void addUserComment(UserComment userComment);
    void removeUserCommentByUserId(String userId);
}
