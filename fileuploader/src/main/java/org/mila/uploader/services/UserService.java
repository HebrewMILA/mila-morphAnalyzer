package org.mila.uploader.services;

import java.util.List;

import org.mila.uploader.entities.User;

public interface UserService {
	public List<User> findAll();
	public void save(User request);
	public void remove(String mail);
	public User find(String mail);
}
