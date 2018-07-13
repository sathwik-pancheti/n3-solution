package com.stackroute.keepnote.dao;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stackroute.keepnote.exception.UserNotFoundException;
import com.stackroute.keepnote.model.User;

/*
 * This class is implementing the UserDAO interface. This class has to be annotated with 
 * @Repository annotation.
 * @Repository - is an annotation that marks the specific class as a Data Access Object, 
 * thus clarifying it's role.
 * @Transactional - The transactional annotation itself defines the scope of a single database 
 * 					transaction. The database transaction happens inside the scope of a persistence 
 * 					context.  
 * */
@Repository
@Transactional
public class UserDaoImpl implements UserDAO {

	/*
	 * Autowiring should be implemented for the SessionFactory.(Use
	 * constructor-based autowiring.
	 */
	
	private SessionFactory sessionFactory;
	@Autowired
	public UserDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/*
	 * Create a new user
	 */

	public boolean registerUser(User user) {
		   sessionFactory.getCurrentSession().save(user);
	       sessionFactory.getCurrentSession().flush();
		return true;
	}

	/*
	 * Update an existing user
	 */

	public boolean updateUser(User user) {
		sessionFactory.getCurrentSession().update(user);
	       sessionFactory.getCurrentSession().flush();
		return true;

	}

	/*
	 * Retrieve details of a specific user
	 */
	public User getUserById(String UserId) {

		return (User) sessionFactory.getCurrentSession().get(User.class,UserId);
	}

	/*
	 * validate an user
	 */

	public boolean validateUser(String userId, String password) throws UserNotFoundException {
		 User u = (User) sessionFactory.getCurrentSession().createQuery("From User where userId=:id and userPassword=:pass").setParameter("id", userId).setParameter("pass", password).uniqueResult();

	       if(u!=null)
	       {
	               return true;
	       }
	       else {
	           throw new UserNotFoundException("User with these credentials are not available");
	       }

	}

	/*
	 * Remove an existing user
	 */
	public boolean deleteUser(String userId) {
		   User u = getUserById(userId);
	       if(u!=null) {
	       sessionFactory.getCurrentSession().createQuery("Delete From User where userId=:id").setParameter("id", userId).executeUpdate();
	       return true;
	   }
	       return false;

	}

	}
