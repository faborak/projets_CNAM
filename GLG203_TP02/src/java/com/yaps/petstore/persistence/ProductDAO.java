package com.yaps.petstore.persistence;

import java.util.Collection;

import com.yaps.petstore.domain.Category;
import com.yaps.petstore.domain.Product;
import com.yaps.petstore.exception.CheckException;
import com.yaps.petstore.exception.DuplicateKeyException;
import com.yaps.petstore.exception.ObjectNotFoundException;

public class ProductDAO extends DataAccessObject {

	private static final String HASHTABLE_FILE_NAME = "persistentProduct.ser";	

    // ======================================
    // =             Attributes             =
    // ======================================
	public ProductDAO() {
		super(HASHTABLE_FILE_NAME);
	}

	public Product find(String id) throws ObjectNotFoundException, CheckException{
		 // Checks data integrity
        checkId(id);
        return (Product) super.select(id);
	}
	
	public Collection<Product> findAll() throws ObjectNotFoundException{
		return (Collection<Product>) selectAll();
	}

	public void insert(Product product) throws DuplicateKeyException, CheckException{
		// Checks data integrity
		product.checkData();
		// Puts the object into the hastable
        super.insert(product, product.getId());
	}
	
    public void update(final Product product) throws ObjectNotFoundException, DuplicateKeyException, CheckException {
    	String id = product.getId();
    	checkId(id);
    	product.checkData();
    	remove(id);
        insert(product);
    }
	
	public void remove(String id) throws ObjectNotFoundException{
		super.remove(id);
	}

}
