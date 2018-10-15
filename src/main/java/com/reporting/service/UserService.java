package com.reporting.service;

import java.util.List;

import com.reporting.pojo.User;

public interface UserService {
	public void createUser(User user);
	public List<User> getUser();
	public User findById(long id);
	public User update(User user, long l);
    public void deleteUserById(long id);
}
