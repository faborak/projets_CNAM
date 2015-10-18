package com.yaps.petstore.persistence;

import java.util.Collection;
import com.yaps.petstore.domain.Item;
import com.yaps.petstore.exception.CheckException;
import com.yaps.petstore.exception.DuplicateKeyException;
import com.yaps.petstore.exception.ObjectNotFoundException;

/**
 * 
 * @author fabo
 * This class uses a HashTable to store Item objects in it and serializes the hashmap.
 *
 */
public class ItemDAO extends DataAccessObject{

    // ======================================
    // =             Attributes             =
    // ======================================
	private static final String HASHTABLE_FILE_NAME = "persistentItem.ser";	

	// ======================================
    // =            Constructors            =
    // ======================================
	public ItemDAO() {
		super(HASHTABLE_FILE_NAME);
	}

    // ======================================
    // =           Business methods         =
    // ======================================
	public Item find(String id) throws ObjectNotFoundException, CheckException{
		 // Checks data integrity
        checkId(id);
        return (Item) super.select(id);
	}
	
	public Collection<Item> findAll() throws ObjectNotFoundException{
		return (Collection<Item>) selectAll();
	}

	public void insert(Item item) throws DuplicateKeyException, CheckException{
		// Checks data integrity
		item.checkData();
		// Puts the object into the hastable
        super.insert(item, item.getId());
	}
	
    public void update(final Item item) throws ObjectNotFoundException, DuplicateKeyException, CheckException {
    	String id = item.getId();
    	checkId(id);
    	item.checkData();
    	remove(id);
        insert(item);
    }
	
	public void remove(String id) throws ObjectNotFoundException{
		super.remove(id);
	}
	
}
