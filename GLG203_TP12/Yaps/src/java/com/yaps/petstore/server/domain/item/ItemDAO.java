package com.yaps.petstore.server.domain.item;

import java.util.Collection;
import java.util.List;

import javax.persistence.Query;

import com.yaps.petstore.common.exception.ObjectNotFoundException;
import com.yaps.petstore.server.domain.orderline.OrderLine;
import com.yaps.petstore.server.util.persistence.AbstractDataAccessObject;

/**
 * This class does all the database access for the class Item.
 * 
 * @see Item
 */
public final class ItemDAO extends AbstractDataAccessObject<String, Item> {

    // ======================================
    // =             Attributes             =
    // ======================================
    // Used to get a unique id with the UniqueIdGenerator
    private static final String COUNTER_NAME = "Item";
	protected String getCounterName() {
		return COUNTER_NAME;
	}

    // ======================================
    // =            Constructors            =
    // ======================================
    public ItemDAO() {
    	this("petstorePU");
    }
    
    public ItemDAO(String persistenceUnitName) {
    	super(persistenceUnitName);
    }
    // ======================================
    // =           Business methods         =
    // ======================================
    /**
     * This method return all the items from the database for a given order id.
     *
     * @param productId
     * @return collection of Item
     * @throws ObjectNotFoundException is thrown if the collection is empty
     */
	public Collection<Item> findAllInProduct(String productId) throws ObjectNotFoundException {
		Query query = _em.createNamedQuery("Item.findAllInProduct");
		query.setParameter("productId", productId);
		List<Item> entities = query.getResultList();
		 if (entities.isEmpty())
	            throw new ObjectNotFoundException();
		return entities;
	}
	
	public Collection<Item> search(String id) throws ObjectNotFoundException{
		Query query = _em.createNamedQuery("Item.search");
		query.setParameter("id", id);
		List<Item> entities = query.getResultList();
		 if (entities.isEmpty())
	            throw new ObjectNotFoundException();
		return entities;
	}
}
