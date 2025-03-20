package com.springboot.videogame.service;

import com.springboot.videogame.entity.usercomment.UserComment;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface UserCommentService {
    List<UserComment> getUserCommentByGameId(Long gameId);
    List<UserComment> getUserCommentByUserId(Integer userId);
    void addUserComment(String userComment, Integer userId, Long gameId);
    void removeUserComment(Long commentId);
}
