package com.atb.dao;

import java.sql.Connection;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.atb.model.User;

public interface UserDao {
	
	public Connection getConnection();
	public void insertUser(User user);
	public List<User> selectAllUsers();
	public void closeConnection();
	public void deleteUser(long id);
	public void updateUser(User user);
	public long getIdMaxUser();
	

}
