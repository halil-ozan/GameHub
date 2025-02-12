package com.springboot.videogame.service;


import com.springboot.videogame.entity.User;
import com.springboot.videogame.user.WebUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	public User findByUserName(String userName);

	void save(WebUser webUser);
}
