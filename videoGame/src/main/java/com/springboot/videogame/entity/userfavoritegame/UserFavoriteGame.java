package com.springboot.videogame.entity.userfavoritegame;
import com.springboot.videogame.entity.Game;
import com.springboot.videogame.entity.User;
import jakarta.persistence.*;

@Entity
@Table(name = "user_favorite_games")
public class UserFavoriteGame {

    @EmbeddedId
    private UserFavoriteGameId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @MapsId("gameId")
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    public UserFavoriteGame() {
    }

    public UserFavoriteGame(UserFavoriteGameId id, User user, Game game) {
        this.id = id;
        this.user = user;
        this.game = game;
    }

    public UserFavoriteGameId getId() {
        return id;
    }

    public void setId(UserFavoriteGameId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
