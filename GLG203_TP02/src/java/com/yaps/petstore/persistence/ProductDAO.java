package com.yaps.petstore.persistence;

import java.util.Collection;

import com.yaps.petstore.domain.Category;
import com.yaps.petstore.domain.Product;
import com.yaps.petstore.exception.CheckException;
import com.yaps.petstore.exception.DuplicateKeyException;
import com.yaps.petstore.exception.ObjectNotFoundException;

public class ProductDAO extends DataAccessObject {

    // ======================================
    // =             Attributes             =
    // ======================================
	private static final String HASHTABLE_FILE_NAME = "persistentProduct.ser";	


	// ======================================
    // =            Constructors            =
    // ======================================
	public ProductDAO() {
		super(HASHTABLE_FILE_NAME);
	}

	// ======================================
    // =           Business methods         =
    // ======================================
	/**
	 * This method gets a Product object from the HashMap.
	 * 
	 * @param id Product identifier to be found in the hastable
	 * @return Product the product object with all its attributs set
	 * @throws ObjectNotFoundException is thrown if the product id not found in the hastable
	 * @throws CheckException is thrown if invalid data is found
	 */
	public Product find(String id) throws ObjectNotFoundException, CheckException{
		 // Checks data integrity
        checkId(id);
        return (Product) super.select(id);
	}
	
	/**
     * This method inserts a Product object into the HashMap and serializes the Hastable on disk.
     *
     * @param product Product Object to be inserted into the hastable
     * @throws CheckException          is thrown if invalid data is found
     * @throws DuplicateKeyException is thrown when an identical object is already in the hastable
     */


    /**
     * This method inserts a Product object into the HashMap and serializes the Hastable on disk.
     *
     * @param product Product Object to be inserted into the hastable
     * @throws CheckException          is thrown if invalid data is found
     * @throws DuplicateKeyException is thrown when an identical object is already in the hastable
     */
	public void insert(Product product) throws DuplicateKeyException, CheckException{
		// Checks data integrity
		product.checkData();
		// Puts the object into the hastable
        super.insert(product, product.getId());
	}
	
    /**
     * This method updates a Product object of the HashMap and serializes the Hastable on disk.
     *
     * @param product Product to be updated from the hastable
     * @throws ObjectNotFoundException     is thrown if the product id not found in the hastable
     * @throws DuplicateKeyException is thrown when an identical object is already in the hastable
     * @throws CheckException is thrown if the product has invalid data 
     */
    public void update(final Product product) throws ObjectNotFoundException, DuplicateKeyException, CheckException {
    	String id = product.getId();
    	checkId(id);
    	product.checkData();
    	remove(id);
        insert(product);
    }
	
    /**
     * This method deletes a Product object from the HashMap and serializes the Hastable on disk.
     *
     * @param id Product identifier to be deleted from the hastable
     * @throws ObjectNotFoundException is thrown if there's a persistent problem
     */
	public void remove(String id) throws ObjectNotFoundException{
		super.remove(id);
	}
	
	/**
	 * This methods returns all the existing products.
	 * 
	 * @return a Collection of all existing Product.
	 * @throws ObjectNotFoundException is thrown if there's a persistent problem.
	 */
	public Collection<Product> findAll() throws ObjectNotFoundException{
		return (Collection<Product>) selectAll();
	}

}
