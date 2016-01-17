package com.yaps.petstore.server.domain.product;

import java.util.Collection;
import java.util.List;

import javax.persistence.Query;

import com.yaps.petstore.common.exception.ObjectNotFoundException;
import com.yaps.petstore.server.domain.orderline.OrderLine;
import com.yaps.petstore.server.util.persistence.AbstractDataAccessObject;

/**
 * This class does all the database access for the class Product.
 *
 * @see Product
 */
public final class ProductDAO extends AbstractDataAccessObject<String, Product> {

    // ======================================
    // =             Attributes             =
    // ======================================
    // Used to get a unique id with the UniqueIdGenerator
    private static final String COUNTER_NAME = "Order";
	protected String getCounterName() {
		return COUNTER_NAME;
	}

    // ======================================
    // =            Constructors            =
    // ======================================
    public ProductDAO() {
    	this("petstorePU");
    }
    
    public ProductDAO(String persistenceUnitName) {
    	super(persistenceUnitName);
    }
    // ======================================
    // =           Business methods         =
    // ======================================

    /**
     * This method return all the order line from the database for a given order id.
     *
     * @param categoryId
     * @return collection of Product
     * @throws ObjectNotFoundException is thrown if the collection is empty
     */
	public Collection<Product> findAllInCategory(String categoryId) throws ObjectNotFoundException {
		Query query = _em.createNamedQuery("OrderLine.findAllInCategory");
		query.setParameter("orderId", categoryId);
		List<Product> entities = query.getResultList();
		if (entities.isEmpty())
            throw new ObjectNotFoundException();
		return entities;
	}
	
}