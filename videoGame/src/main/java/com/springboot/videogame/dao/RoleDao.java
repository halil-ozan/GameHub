package com.springboot.videogame.dao;

import com.springboot.videogame.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
