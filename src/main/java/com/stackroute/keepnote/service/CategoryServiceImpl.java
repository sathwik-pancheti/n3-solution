package com.stackroute.keepnote.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.keepnote.dao.CategoryDAO;
import com.stackroute.keepnote.exception.CategoryNotFoundException;
import com.stackroute.keepnote.model.Category;

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
public class CategoryServiceImpl implements CategoryService {
	/*
	 * Autowiring should be implemented for the CategoryDAO. (Use Constructor-based
	 * autowiring) Please note that we should not create any object using the new
	 * keyword.
	 */
	private CategoryDAO cat;
	@Autowired
	public CategoryServiceImpl(CategoryDAO cat) {
		this.cat = cat;
	}
	/*
	 * This method should be used to save a new category.
	 */
	public boolean createCategory(Category category) {
		Boolean create = cat.createCategory(category);
		return create;

	}

	/* This method should be used to delete an existing category. */
	public boolean deleteCategory(int categoryId) {
		Boolean delete = cat.deleteCategory(categoryId);
		return delete;

	}

	/*
	 * This method should be used to update a existing category.
	 */

	public Category updateCategory(Category category, int id) throws CategoryNotFoundException {
		
		Category n = cat.getCategoryById(id);
		if(n!=null) {
			cat.updateCategory(category);
			return category;
		}
		else {
			throw new CategoryNotFoundException("category not found");
		}
		

	}

	/*
	 * This method should be used to get a category by categoryId.
	 */
	public Category getCategoryById(int categoryId) throws CategoryNotFoundException {
		Category n = cat.getCategoryById(categoryId);
		if(n!=null) {
			
			return n;
		}
		else {
			throw new CategoryNotFoundException("category not found");
		}

	}

	/*
	 * This method should be used to get a category by userId.
	 */

	public List<Category> getAllCategoryByUserId(String userId) {
		return cat.getAllCategoryByUserId(userId);

	}

}
