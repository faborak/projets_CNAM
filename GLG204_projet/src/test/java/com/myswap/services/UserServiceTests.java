package com.myswap.services;

import com.myswap.exceptions.UserInsertException;
import com.myswap.exceptions.UserNotFoundException;
import com.myswap.models.User;

import junit.framework.TestCase;

public final class UserServiceTests extends TestCase {

	public UserServiceTests(final String s) {
		super(s);
	}

	UserService userService = new UserService();

	public void testFindUserByIdWithInvalidValues() throws Exception {

		// Finds an object with a unknown identifier
		try {
			userService.findUser(-1);
			fail("Object with unknonw id should not be found");
		} catch (UserNotFoundException e) {
		}

		// Finds an object with an empty identifier
		try {
			userService.findUser(Long.parseLong("0"));
			fail("Object with empty id should not be found");
		} catch (UserNotFoundException e) {
		}

		// Finds an object with a null identifier
		try {
			userService.findUser(null);
			fail("Object with null id should not be found");
		} catch (UserNotFoundException e) {
		}
	}

	public void testFindUserByItemWithInvalidValues() throws Exception {

		// Finds an object with a unknown identifier
		try {
			userService.findUserByItem(-1);
			fail("Object with unknonw id should not be found");
		} catch (UserNotFoundException e) {
		}

		// Finds an object with an empty identifier
		try {
			userService.findUserByItem(Long.parseLong("0"));
			fail("Object with empty id should not be found");
		} catch (UserNotFoundException e) {
		}

	}

	/**
	 * This test ensures that the method findAll works. It does a first findAll,
	 * creates a new object and does a second findAll.
	 */
	public void testInsertDeleteUserAccountAdress() throws Exception {

		// Creates an object
		userService.insertUser("name", "lastname", "emailDeTest@test.fr", "password", "0106121212", "street", "state", "zipcode", "city");

		// Ensures that the object exists
		User user = null;
		try {
			user = userService.findUser("emailDeTest@test.fr");
		} catch (UserNotFoundException e) {
			fail("Object has been created it should be found");
		}

		// Cleans the test environment
		userService.deleteUser(user.getId());

		try {
			userService.findUser("emailDeTest@test.fr");
			fail("Object has been deleted it shouldn't be found");
		} catch (UserNotFoundException e) {
		}
	}
	
	public void testCreateUserrWithInvalidValues() throws Exception {

        // Creates an object with a null parameter
        try {
        	userService.insertUser(null, null, null, null, null, null, null, null, null);
            fail("Object with null parameter should not be created");
        } catch (UserInsertException e) {
        }

        // Creates an object with empty values
        try {
        	userService.insertUser(new String(), null, null, null, null, null, null, null, null);
            fail("Object with empty values should not be created");
        } catch (UserInsertException e) {
        }

    }
	
	public void testUpdateUser() throws Exception {
		
		userService.insertUser("name", "lastname", "emailDeTest@test.fr", "password", "0106121212", "street", "state", "zipcode", "city");

		// Ensures that the object exists
		User user = null;
		try {
			user = userService.findUser("emailDeTest@test.fr");
		} catch (UserNotFoundException e) {
			fail("Object has been created it should be found");
		}
		
		userService.updateUser("name", "lastname", "updateDeTest@test.fr", false, "0106121212", false, "street", "state", "zipcode", "city", "school", "job", "about");
		
		try {
			user = userService.findUser("emailDeTest@test.fr");
		} catch (UserNotFoundException e) {
			fail("Object has been created it should be found");
		}
		
		userService.deleteUser(user.getId());

		try {
			userService.findUser("emailDeTest@test.fr");
			fail("Object has been deleted it shouldn't be found");
		} catch (UserNotFoundException e) {
		}
	
	}
	
	
}