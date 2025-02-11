package com.springboot.videogame.repository;

import com.springboot.videogame.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
