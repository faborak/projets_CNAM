package com.yaps.petstore.persistence;

import java.util.Collection;

import com.yaps.petstore.domain.Category;
import com.yaps.petstore.exception.CheckException;
import com.yaps.petstore.exception.DuplicateKeyException;
import com.yaps.petstore.exception.ObjectNotFoundException;

public class CategoryDAO extends DataAccessObject {
	
    // ======================================
    // =             Attributes             =
    // ======================================
	private static final String HASHTABLE_FILE_NAME = "persistentCategory.ser";	

    // ======================================
    // =            Constructors            =
    // ======================================
	public CategoryDAO() {
		super(HASHTABLE_FILE_NAME);
	}

    // ======================================
    // =           Business methods         =
    // ======================================
	/**
	 * This method gets a Category object from the HashMap.
	 * 
	 * @param id Category identifier to be found in the hastable
	 * @return Category the category object with all its attributs set
	 * @throws ObjectNotFoundException is thrown if the category id not found in the hastable
	 * @throws CheckException is thrown if invalid data is found
	 */
	public Category find(String id) throws ObjectNotFoundException, CheckException{
		 // Checks data integrity
        checkId(id);
        return (Category) super.select(id);
	}
	
    /**
     * This method inserts a Category object into the HashMap and serializes the Hastable on disk.
     *
     * @param category Category Object to be inserted into the hastable
     * @throws CheckException          is thrown if invalid data is found
     * @throws DuplicateKeyException is thrown when an identical object is already in the hastable
     */
	public void insert(Category category) throws DuplicateKeyException, CheckException{
		// Checks data integrity
		category.checkData();
		// Puts the object into the hastable
        super.insert(category, category.getId());
	}
	
    /**
     * This method updates a Category object of the HashMap and serializes the Hastable on disk.
     *
     * @param category Category to be updated from the hastable
     * @throws ObjectNotFoundException     is thrown if the category id not found in the hastable
     * @throws DuplicateKeyException is thrown when an identical object is already in the hastable
     * @throws CheckException is thrown if the category has invalid data 
     */
    public void update(final Category category) throws ObjectNotFoundException, DuplicateKeyException, CheckException {
    	String id = category.getId();
    	checkId(id);
    	category.checkData();
    	remove(id);
        insert(category);
    }
	
    /**
     * This method deletes a Category object from the HashMap and serializes the Hastable on disk.
     *
     * @param id Category identifier to be deleted from the hastable
     * @throws ObjectNotFoundException is thrown if there's a persistent problem
     */
	public void remove(String id) throws ObjectNotFoundException{
		super.remove(id);
	}
	
	/**
	 * This methods returns all the existing categories.
	 * 
	 * @return a Collection of all existing Categories.
	 * @throws ObjectNotFoundException is thrown if there's a persistent problem.
	 */
	public Collection<Category> findAll() throws ObjectNotFoundException{
		return (Collection<Category>) selectAll();
	}
	
}
