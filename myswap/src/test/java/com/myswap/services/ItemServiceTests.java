package com.myswap.services;

import java.util.HashSet;
import java.util.Set;

import com.myswap.exceptions.ItemInsertException;
import com.myswap.exceptions.ItemNotFoundException;
import com.myswap.models.Item;

import junit.framework.TestCase;

public final class ItemServiceTests extends TestCase {

	public ItemServiceTests(final String s) {
		super(s);
	}

	ItemService itemService = new ItemService();

	public void testFinditemByIdWithInvalidValues() throws Exception {

		// Finds an object with a unknown identifier
		try {
			itemService.findItem(-1);
			fail("Object with unknonw id should not be found");
		} catch (ItemNotFoundException e) {
		}

		// Finds an object with an empty identifier
		try {
			itemService.findItem(Long.parseLong("0"));
			fail("Object with empty id should not be found");
		} catch (ItemNotFoundException e) {
		}

	}

	public void testFindItemByUserWithInvalidValues() throws Exception {

		// Finds an object with a unknown identifier
		try {
			itemService.findItemsByUser(-1);
			fail("Object with unknonw id should not be found");
		} catch (ItemNotFoundException e) {
		}

		// Finds an object with an empty identifier
		try {
			itemService.findItemsByUser(Long.parseLong("0"));
			fail("Object with empty id should not be found");
		} catch (ItemNotFoundException e) {
		}

	}

	public void testInsertDeleteItem() throws Exception {

		// Creates an object
		Item item = itemService.insertItem("name", "description", "1.0", "Informatique", "1");

		// Ensures that the object exists
		long itemId = item.getIdSwapObjet();
		
		item = null;
		
		try {
			item = itemService.findItem(itemId);
		} catch (ItemNotFoundException e) {
			fail("Object has been created it should be found");
		}

		// Cleans the test environment
		itemService.deleteItem(item.getIdSwapObjet());

		try {
			itemService.findItem(itemId);
			fail("Object has been deleted it shouldn't be found");
		} catch (ItemNotFoundException e) {
		}
	}
	
	public void testCreateItemrWithInvalidValues() throws Exception {

        // Creates an object with a null parameter
        try {
        	itemService.insertItem(null, null, null, null, null);
            fail("Object with null parameter should not be created");
        } catch (ItemInsertException e) {
        }

        // Creates an object with empty values
        try {
        	itemService.insertItem(new String(), new String(), new String(), new String(), new String());
            fail("Object with empty values should not be created");
        } catch (ItemInsertException e) {
        }
		
		// Creates an object with invalid User
        try {
        	itemService.insertItem("name", "description", "1.0", "Informatique", "0");
            fail("Object with invalid user should not be created");
        } catch (ItemInsertException e) {
        }

    }
	
	public void testUpdateitem() throws Exception {
		
		Item item = itemService.insertItem("name", "description", "1.0", "Informatique", "1");

		// Ensures that the object exists
		long itemId = item.getIdSwapObjet();
		
		item = null;
		
		item = null;
		
		try {
			item = itemService.findItem(itemId);
		} catch (ItemNotFoundException e) {
			fail("Object has been created it should be found");
		}
		
		Set<String> dealsId = new HashSet<String>();
		dealsId.add("1");
		
		itemService.updateItem(itemId, "name", "description", "2.0", dealsId);
		
		try {
			item = itemService.findItem(itemId);
		} catch (ItemNotFoundException e) {
			fail("Object has been updated it should be found");
		}
		
		itemService.deleteItem(itemId);

		try {
			itemService.findItem(itemId);
			fail("Object has been deleted it shouldn't be found");
		} catch (ItemNotFoundException e) {
		}
	
	}
	
	
}