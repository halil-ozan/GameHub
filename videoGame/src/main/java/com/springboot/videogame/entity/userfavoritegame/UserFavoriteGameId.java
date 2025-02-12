package com.springboot.videogame.entity.userfavoritegame;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserFavoriteGameId implements Serializable {

    private Integer userId;
    private Long gameId;

    // Parametresiz yapıcı (default constructor) ekleyin
    public UserFavoriteGameId() {}

    // Parametreli yapıcı
    public UserFavoriteGameId(Integer userId, Long gameId) {
        this.userId = userId;
        this.gameId = gameId;
    }

    // Getters ve Setters
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

}
