package com.myswap.services;

import com.myswap.exceptions.UserNotFoundException;
import com.myswap.models.User;

import junit.framework.TestCase;

public final class AuthenticationServiceTests extends TestCase {

	public AuthenticationServiceTests(final String s) {
		super(s);
	}

	UserService userService = new UserService();
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
		String token = null;
		
        token = authenticationService.authenticateUser("emailDeTest@test.fr", "wordpass");
        if (token != null){
        	fail("wrong parameters shouldn't generate a token");
        }

        // Creates an object with emptypassword
        token = 	authenticationService.authenticateUser("emailDeTest@test.fr", "");
        if (token != null){
        	fail("wrong parameters shouldn't generate a token");
        }
        
		// Creates an object with mail
        token = 	authenticationService.authenticateUser("", "password");
        if (token != null){
        	fail("wrong parameters shouldn't generate a token");
        }

    }
	
	
}