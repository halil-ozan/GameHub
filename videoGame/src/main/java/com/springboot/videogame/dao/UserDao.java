package com.springboot.videogame.dao;

import com.springboot.videogame.entity.User;


public interface UserDao {

    User findById(int id);

    User findByUserName(String userName);

    void save(User theUser);

}
