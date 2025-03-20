package com.springboot.videogame.entity.usercomment;

import com.springboot.videogame.entity.Game;
import com.springboot.videogame.entity.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Entity
    @Table(name = "user_comments")
    public class UserComment {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        private User user;

        @ManyToOne
        @JoinColumn(name = "game_id", nullable = false)
        private Game game;

        @Column(nullable = false, columnDefinition = "TEXT")
        private String commentText;

        @Column(nullable = false, updatable = false)
        private LocalDateTime createdAt = LocalDateTime.now();

        public UserComment() {

        }

        public UserComment(User user, Game game, String commentText) {
            this.user = user;
            this.game = game;
            this.commentText = commentText;
        }



    public Long getId() {
            return id;
        }

        public void setId(Long id) {
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

        public String getCommentText() {
            return commentText;
        }

        public void setCommentText(String commentText) {
            this.commentText = commentText;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
        }
    }

