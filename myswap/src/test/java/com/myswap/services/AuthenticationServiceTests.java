package com.myswap.services;

import com.myswap.exceptions.UserInsertException;
import com.myswap.exceptions.UserNotFoundException;
import com.myswap.exceptions.AuthenticatetException;
import com.myswap.models.User;

import junit.framework.TestCase;

public final class AuthenticationServiceTests extends TestCase {

	public AuthenticationServiceTests(final String s) {
		super(s);
	}

	UserService userService = new ItemService();
	AuthenticationService authenticationService = new AuthenticationService();

	public void testAuthentication() throws Exception {

		// Creates an object
		userService.insertUser("name", "lastname", "emailDeTest@test.fr", "password", "0106121212", "street", "state", "zipcode", "city");

		// Ensures that the object exists
		User user = null;
		try {
			user = userService.findUser("emailDeTest@test.fr");
		} catch (UserNotFoundException e) {
			fail("Object has been created it should be found");
		}

		authenticationService.authenticateUser("emailDeTest@test.fr", "password");
		
		// Cleans the test environment
		userService.deleteUser(user.getId());

		try {
			userService.findUser("emailDeTest@test.fr");
			fail("Object has been deleted it shouldn't be found");
		} catch (UserNotFoundException e) {
		}
	}
	
	public void testAuthenticationWithInvalidValues() throws Exception {

        // authenticate with a wrong password
        try {
        	authenticationService.authenticateUser("emailDeTest@test.fr", "wordpass");
            fail("Object with null parameter should not be created");
        } catch (AuthenticateException e) {
        }

        // Creates an object with emptypassword
        try {
        	authenticationService.authenticateUser("emailDeTest@test.fr", "");
            fail("Object with null parameter should not be created");
        } catch (AuthenticateException e) {
        }
		
		// Creates an object with mail
        try {
        	authenticationService.authenticateUser("", "password");
            fail("Object with null parameter should not be created");
        } catch (UserNotFoundException e) {
        }

    }
	
	
}