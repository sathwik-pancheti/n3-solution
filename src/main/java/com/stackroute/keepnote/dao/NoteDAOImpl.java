package com.stackroute.keepnote.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stackroute.keepnote.exception.NoteNotFoundException;
import com.stackroute.keepnote.model.Note;

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
public class NoteDAOImpl implements NoteDAO {

	/*
	 * Autowiring should be implemented for the SessionFactory.(Use
	 * constructor-based autowiring.
	 */
	private SessionFactory sessionFactory;
	
	@Autowired
	public NoteDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/*
	 * Create a new note
	 */
	
	public boolean createNote(Note note) {
		sessionFactory.getCurrentSession().save(note);
	    sessionFactory.getCurrentSession().flush();
		return true;
	}

	/*
	 * Remove an existing note
	 */
	
	public boolean deleteNote(int noteId) {
		Note n;
	       try {
	           n = getNoteById(noteId);
	           if(n!=null)
	           {
	                sessionFactory.getCurrentSession().createQuery("Delete From Note where noteId=:id").setParameter("id", noteId).executeUpdate();
	           return true;
	       }    
	       } catch (NoteNotFoundException e) {
	           
	           e.printStackTrace();
	       }
	       return false;
		
		
	}

	/*
	 * Retrieve details of all notes by userId
	 */
	
	public List<Note> getAllNotesByUserId(String userId) {
		 @SuppressWarnings("unchecked")
		Query<Note> query = sessionFactory.getCurrentSession().createQuery("From Note where createdBy=:user").setParameter("user", userId);
	     return query.getResultList();
		

	}

	/*
	 * Retrieve details of a specific note
	 */
	
	public Note getNoteById(int noteId) throws NoteNotFoundException {
		
		Note n = sessionFactory.getCurrentSession().get(Note.class,noteId);
		
		if(n!=null) {
			return n;
		}
		
		else {
			throw new NoteNotFoundException("note not found");
		}
	}

	/*
	 * Update an existing note
	 */

	public boolean UpdateNote(Note note) {
		sessionFactory.getCurrentSession().update(note);
	    sessionFactory.getCurrentSession().flush();
		return true;

	}

}
