package com.niit.crudhibernate.daoimpl;

import java.util.List;

import org.hibernate.Session;

import com.niit.crudhibernate.config.DBConfig;
import com.niit.crudhibernate.dao.UserDAO;
import com.niit.crudhibernate.model.User;

public class UserDAOImpl implements UserDAO
{
	Session session = DBConfig.getSession();

	
	public boolean addUser(User user) {
		session.getTransaction().begin();
		session.save(user);
		session.getTransaction().commit();
		return true;
	}

	public User getUserById(int userid) {
		User user= (User) session.createQuery("from User where userId = "+ userid).list().get(0);
		return user;
	}

	public List<User> getUserList() {
		session.getTransaction().begin();
		System.out.println("Session is Opened ::"+session.isOpen());
		System.out.println("Session is Connected ::"+session.isConnected());
		//annotation is used to suppress compiler warnings for the annotated element.
		@SuppressWarnings("unchecked")
		List<User> getUserList = session.createQuery("from User").list();
		session.getTransaction().commit();
		return getUserList;
	}

	public boolean updateRecord(User user)
	{
		session.getTransaction().begin();
		session.update(user);
		session.getTransaction().commit();	
		return true;
	}
	public boolean deleteRecord(User user)
	{
		session.getTransaction().begin();
		session.delete(user);
		session.getTransaction().commit();	
		return true;
	}

}
