package com.springboot.videogame.dao;

import com.springboot.videogame.entity.usercomment.UserComment;
import java.util.List;

public interface UserCommentsDao {
    void addComment(UserComment userComment);
    void removeUserComment(Long commentId);
    List<UserComment> getUserCommentByGameId(Long gameId);
    List<UserComment> getUserCommentByUserId(Integer userId);
}
