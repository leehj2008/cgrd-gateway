package com.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface UserDao {
	
	User getUser(String username);
	User getUserByWinUser(String winuser);

}
