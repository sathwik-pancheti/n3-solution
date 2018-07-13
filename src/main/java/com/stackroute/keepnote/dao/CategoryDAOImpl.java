package com.stackroute.keepnote.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.stackroute.keepnote.exception.CategoryNotFoundException;
import com.stackroute.keepnote.model.Category;

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
public class CategoryDAOImpl implements CategoryDAO {

	/*
	 * Autowiring should be implemented for the SessionFactory.(Use
	 * constructor-based autowiring.
	 */
	private SessionFactory sessionFactory;
	
	@Autowired
	public CategoryDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}

	/*
	 * Create a new category
	 */
	public boolean createCategory(Category category) {
		category.setCategoryCreationDate(new Date());
		sessionFactory.getCurrentSession().save(category);
		sessionFactory.getCurrentSession().flush();
		return true;

	}

	/*
	 * Remove an existing category
	 */
	public boolean deleteCategory(int categoryId) {
		Category n;
	       try {
	           n = getCategoryById(categoryId);
	           if(n!=null)
	           {
	                sessionFactory.getCurrentSession().createQuery("Delete From Category where categoryId=:id").setParameter("id", categoryId).executeUpdate();
	           return true;
	       }    
	       } catch (CategoryNotFoundException e) {
	           
	           e.printStackTrace();
	       }
	       return false;

	}
	/*
	 * Update an existing category
	 */

	public boolean updateCategory(Category category) {
		sessionFactory.getCurrentSession().update(category);
		sessionFactory.getCurrentSession().flush();
		return true;

	}
	/*
	 * Retrieve details of a specific category
	 */

	public Category getCategoryById(int categoryId) throws CategoryNotFoundException {
		Category n = sessionFactory.getCurrentSession().get(Category.class,categoryId);
		
		if(n!=null) {
			return n;
		}
		else {
			throw new CategoryNotFoundException("Category not found");
		}

	}

	/*
	 * Retrieve details of all categories by userId
	 */
	public List<Category> getAllCategoryByUserId(String userId) {
		@SuppressWarnings("unchecked")
		Query<Category> query = sessionFactory.getCurrentSession().createQuery("From Category where categoryCreatedBy=:user").setParameter("user", userId);
	     return query.getResultList();

	}

}
