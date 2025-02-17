package com.springboot.videogame.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game {
    @Id
    private Long id;
    private String name;// Use appropriate date type
    private String backgroundImage;
    private Double rating;
    private String genre;
    private String description;


    @ManyToMany(mappedBy = "favoriteGames")
    private Set<User> users = new HashSet<>();

    // Getters, setters, and default constructor
    public Game() {}

    public Game(Long id, String name, String backgroundImage, Double rating, String genre, String description) {
        this.id = id;
        this.name = name;
        this.backgroundImage = backgroundImage;
        this.rating = rating;
        this.genre = genre;
        this.description = description;
    }

// Additional constructors if needed

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
