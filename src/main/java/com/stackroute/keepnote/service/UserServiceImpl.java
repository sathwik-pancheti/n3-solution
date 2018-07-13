package com.stackroute.keepnote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.keepnote.dao.UserDAO;
import com.stackroute.keepnote.exception.UserAlreadyExistException;
import com.stackroute.keepnote.exception.UserNotFoundException;
import com.stackroute.keepnote.model.User;

/*
* Service classes are used here to implement additional business logic/validation 
* This class has to be annotated with @Service annotation.
* @Service - It is a specialization of the component annotation. It doesn�t currently 
* provide any additional behavior over the @Component annotation, but it�s a good idea 
* to use @Service over @Component in service-layer classes because it specifies intent 
* better. Additionally, tool support and additional behavior might rely on it in the 
* future.
* */
@Service
public class UserServiceImpl implements UserService {

	/*
	 * Autowiring should be implemented for the userDAO. (Use Constructor-based
	 * autowiring) Please note that we should not create any object using the new
	 * keyword.
	 */

	private UserDAO use;
	@Autowired
	public UserServiceImpl(UserDAO use) {
		this.use = use;
	}
	/*
	 * This method should be used to save a new user.
	 */

	public boolean registerUser(User user) throws UserAlreadyExistException {
		if(use.getUserById(user.getUserId())== null) {
			use.registerUser(user);
			return true;
		}
		else {
			throw new UserAlreadyExistException("user already exists");
		}
	}

	/*
	 * This method should be used to update a existing user.
	 */

	public User updateUser(User user, String userId) throws Exception {
		User n = use.getUserById(userId);
		
		if(n != null) {
			use.updateUser(user);
			return user;
		}
		else {
			throw new Exception("user not found");
		}

	}

	/*
	 * This method should be used to get a user by userId.
	 */

	public User getUserById(String UserId) throws UserNotFoundException {
		User n = use.getUserById(UserId);
		if(n!=null) {
			return n;
		}
		else {
			throw new UserNotFoundException("user not found");
		}

	}

	/*
	 * This method should be used to validate a user using userId and password.
	 */

	public boolean validateUser(String userId, String password) throws UserNotFoundException {
		if(use.validateUser(userId, password)) {
			return true;
		}
		else {
			throw new UserNotFoundException("user not found");
		}
	}

	/* This method should be used to delete an existing user. */
	public boolean deleteUser(String UserId) {
		Boolean delete = use.deleteUser(UserId);
		return delete;

	}

}
