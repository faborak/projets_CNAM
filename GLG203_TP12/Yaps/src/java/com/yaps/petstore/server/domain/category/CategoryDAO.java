package com.yaps.petstore.server.domain.category;

import com.yaps.petstore.server.util.persistence.AbstractDataAccessObject;

/**
 * This class does all the database access for the class Category.
 *
 * @see Category
 */
public final class CategoryDAO extends AbstractDataAccessObject<String, Category>  {

    // ======================================
    // =             Attributes             =
    // ======================================
    // Used to get a unique id with the UniqueIdGenerator
    private static final String COUNTER_NAME = "Category";
	protected String getCounterName() {
		return COUNTER_NAME;
	}

    // ======================================
    // =            Constructors            =
    // ======================================
    public CategoryDAO() {
    	this("petstorePU");
    }
    
    public CategoryDAO(String persistenceUnitName) {
    	super(persistenceUnitName);
    }
    // ======================================
    // =           Business methods         =
    // ======================================
    
}
