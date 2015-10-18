package com.yaps.petstore.persistence;

import java.util.Collection;

import com.yaps.petstore.domain.Category;
import com.yaps.petstore.exception.CheckException;
import com.yaps.petstore.exception.DuplicateKeyException;
import com.yaps.petstore.exception.ObjectNotFoundException;

public class CategoryDAO extends DataAccessObject {
	
	private static final String HASHTABLE_FILE_NAME = "persistentCategory.ser";	

	public CategoryDAO() {
		super(HASHTABLE_FILE_NAME);
	}


	public Category find(String id) throws ObjectNotFoundException, CheckException{
		 // Checks data integrity
        checkId(id);
        return (Category) super.select(id);
	}
	
	public Collection<Category> findAll() throws ObjectNotFoundException{
		return (Collection<Category>) selectAll();
	}

	public void insert(Category category) throws DuplicateKeyException, CheckException{
		// Checks data integrity
		category.checkData();
		// Puts the object into the hastable
        super.insert(category, category.getId());
	}
	
    public void update(final Category category) throws ObjectNotFoundException, DuplicateKeyException, CheckException {
    	String id = category.getId();
    	checkId(id);
    	category.checkData();
    	remove(id);
        insert(category);
    }
	
	public void remove(String id) throws ObjectNotFoundException{
		super.remove(id);
	}
	
}
