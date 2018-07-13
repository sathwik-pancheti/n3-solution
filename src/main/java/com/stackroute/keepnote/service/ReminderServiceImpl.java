package com.stackroute.keepnote.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.keepnote.dao.ReminderDAO;
import com.stackroute.keepnote.exception.ReminderNotFoundException;
import com.stackroute.keepnote.model.Reminder;

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
public class ReminderServiceImpl implements ReminderService {

	/*
	 * Autowiring should be implemented for the ReminderDAO. (Use Constructor-based
	 * autowiring) Please note that we should not create any object using the new
	 * keyword.
	 */
	private ReminderDAO rem; 
	@Autowired
	public ReminderServiceImpl(ReminderDAO rem) {
		this.rem = rem;
	}

	/*
	 * This method should be used to save a new reminder.
	 */

	public boolean createReminder(Reminder reminder) {
		Boolean create = rem.createReminder(reminder);
		return create;

	}

	/*
	 * This method should be used to update a existing reminder.
	 */

	public Reminder updateReminder(Reminder reminder, int id) throws ReminderNotFoundException {
		Reminder n = rem.getReminderById(id);
		if(n!=null) {
			rem.updateReminder(reminder);
			return reminder;
		}
		else {
			throw new ReminderNotFoundException("reminder not found");
		}
	}

	/* This method should be used to delete an existing reminder. */
	
	public boolean deleteReminder(int reminderId) {
		Boolean delete = rem.deleteReminder(reminderId);
		return delete;
	}

	/*
	 * This method should be used to get a reminder by reminderId.
	 */
	
	public Reminder getReminderById(int reminderId) throws ReminderNotFoundException {
		Reminder n = rem.getReminderById(reminderId);
		if(n!=null) {
			return n;
		}
		else {
			throw new ReminderNotFoundException("reminder not found");
		}

	}

	/*
	 * This method should be used to get a reminder by userId.
	 */

	public List<Reminder> getAllReminderByUserId(String userId) {
		return rem.getAllReminderByUserId(userId);

	}
}
