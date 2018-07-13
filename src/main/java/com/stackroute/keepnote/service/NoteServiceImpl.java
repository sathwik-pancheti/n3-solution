package com.stackroute.keepnote.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.keepnote.dao.CategoryDAO;
import com.stackroute.keepnote.dao.NoteDAO;
import com.stackroute.keepnote.dao.ReminderDAO;
import com.stackroute.keepnote.exception.CategoryNotFoundException;
import com.stackroute.keepnote.exception.NoteNotFoundException;
import com.stackroute.keepnote.exception.ReminderNotFoundException;
import com.stackroute.keepnote.model.Category;
import com.stackroute.keepnote.model.Note;
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
public class NoteServiceImpl implements NoteService {

	/*
	 * Autowiring should be implemented for the NoteDAO,CategoryDAO,ReminderDAO.
	 * (Use Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword.
	 */
	private NoteDAO no;
	private CategoryDAO cat;
	private ReminderDAO rem;
	
	@Autowired
	public NoteServiceImpl(NoteDAO no,CategoryDAO cat,ReminderDAO rem) {
		this.no = no;
		this.cat = cat;
		this.rem = rem;
	}
	
	
	
	
	
    /*
	 * This method should be used to save a new note.
	 */

	public boolean createNote(Note note) throws ReminderNotFoundException, CategoryNotFoundException {
		if(note.getReminder()!=null) {
			Reminder re = rem.getReminderById(note.getReminder().getReminderId());
		}
		
		if(note.getCategory()!=null) {
			Category ca = cat.getCategoryById(note.getCategory().getCategoryId());
		}
		
		Boolean create = no.createNote(note);
		return create;

	}

	/* This method should be used to delete an existing note. */

	public boolean deleteNote(int noteId) {
		
		Boolean delete = no.deleteNote(noteId);
		return delete;
		
	}
	/*
	 * This method should be used to get a note by userId.
	 */

	public List<Note> getAllNotesByUserId(String userId) {
		return no.getAllNotesByUserId(userId);

	}

	/*
	 * This method should be used to get a note by noteId.
	 */
	public Note getNoteById(int noteId) throws NoteNotFoundException {
		Note n = no.getNoteById(noteId);
		if(n!= null) {
			return n;
		}
		else {
			throw new NoteNotFoundException("note not found");
		}

	}

	/*
	 * This method should be used to update a existing note.
	 */

	public Note updateNote(Note note, int id)
			throws ReminderNotFoundException, NoteNotFoundException, CategoryNotFoundException {
		
		Note n = no.getNoteById(id);
		if(n.getReminder()!=null) {
			Reminder re = rem.getReminderById(note.getReminder().getReminderId());
		}
		
		if(n.getCategory()!=null) {
	          Category ca=cat.getCategoryById(note.getCategory().getCategoryId());
	      }
		
		no.UpdateNote(n);
		return n;

	}

}
