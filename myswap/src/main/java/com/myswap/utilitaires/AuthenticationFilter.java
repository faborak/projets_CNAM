package com.myswap.utilitaires;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.google.common.net.HttpHeaders;
import com.myswap.models.User;
import com.myswap.services.UserService;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

	/**
	 * Le UserService.
	 */
	UserService userService = new UserService();
	Session session;

	private static Logger logger = Logger.getLogger(AuthenticationFilter.class);
	
	static final long ONE_MINUTE_IN_MILLIS=60000;//millisecs
	static final long LOGGING_TIME= 7 * ONE_MINUTE_IN_MILLIS;
	
    @Override
    /**
     * Recupère toutes les requêtes au serveur, et les valides avant l'accès aux web services.
     */
    public void filter(ContainerRequestContext requestContext) throws IOException {

        // Get the HTTP Authorization header from the request
        String authorizationHeader = 
            requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        // Check if the HTTP Authorization header is present and formatted correctly 
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }

        // Extract the token from the HTTP Authorization header
        String token = authorizationHeader.substring("Bearer".length()).trim();

        try {

            // Validate the token
            validateToken(token);

        } catch (Exception e) {
            requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    /**
     * Check if it was issued by the server and if it's not expired
     * Throw an Exception if the token is invalid
     * @param token
     * @throws Exception
     */
    private void validateToken(String token) throws Exception {
        
    	User user = new User();
    	user = userService.findUserByToken(token);
    	
    	Date userActivityPlusSevenMinutes = new Date(user.getActivity().getDateDerniereActivite().getTime() + LOGGING_TIME);
    	
    	if (userActivityPlusSevenMinutes.compareTo(new Date()) == -1){
    	    throw new Exception("Le user n'est plus authentifiÃ©");	
    	} else {
    		user.getActivity().setDateDerniereActivite(new Date());
    		userService.updateActivity(user);
    	}
    }
}
