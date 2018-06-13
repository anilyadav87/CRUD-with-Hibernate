package com.niit.crudhibernate.service.implement;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.crudhibernate.dao.UserDAO;
import com.niit.crudhibernate.model.User;
import com.niit.crudhibernate.service.UserService;

@Service	
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	@Transactional
	public boolean addUser(User user) {
		userDAO.addUser(user);
		return true;
	}
	@Transactional
	public User getUserById(int userid) {
		return userDAO.getUserById(userid);
	}
	@Transactional
	public List<User> getUserList() {
		return userDAO.getUserList();
	}
	@Transactional
	public boolean updateRecord(User user) {
		userDAO.updateRecord(user);
		return true;
	}
	public boolean updateRecord(int id, User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
