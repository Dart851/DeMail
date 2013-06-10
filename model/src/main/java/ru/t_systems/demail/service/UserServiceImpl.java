package ru.t_systems.demail.service;

import org.springframework.transaction.annotation.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.t_systems.demail.dao.user.UserDAO;
import ru.t_systems.demail.model.user.User;


@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;

	public User getUser(String login) {
		return userDAO.getUser(login);
	}

	public void addUser(User user) {
		userDAO.addUser(user);
		
	}

	public boolean isRegistred(String login) {
		if (userDAO.getUser(login)==null){
		return false;
		}
		return true;
	}

	public void update(User user) {
		userDAO.update(user);
		
	}

}
