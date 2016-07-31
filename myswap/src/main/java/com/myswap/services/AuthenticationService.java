package com.myswap.services;

import java.io.IOException;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.myswap.models.Activity;
import com.myswap.models.User;
import com.myswap.ressources.UserRessource;

@Path("/authentication")
public class AuthenticationService{

	public static final String CLIENT_ID = "853972608044-m14mdhc3q2k437nfqbob5hti7div33u6.apps.googleusercontent.com";
	public static final String SECRET_ID = "W0q5CJb7tSqSMmeK4hHPLeBF";

	/**
	 * Le UserService.
	 */
	UserRessource userRessource = new UserRessource();
	Session session;

	private static Logger logger = Logger.getLogger(AuthenticationService.class);

	 @POST
	 @Produces("application/json")
	 @Consumes("application/x-www-form-urlencoded")
	 @Path("/authenticate")
	public Response authenticateUser(String mail, String password) {

		try {

			User user = new User();

			// Authenticate the user using the credentials provided
			user = authenticate(mail, password, user);

			// Issue a token for the user
			String token = issueToken(user);

			// Return the token on the response
			return Response.ok(token).build();

		} catch (Exception e) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}

	private User authenticate(String mail, String password, User user) throws Exception {
		user = userRessource.findUser(mail);
		if (user == null) {
			logger.debug("User non trouve à l'authentication par mail/password : ");
			throw new Exception();
		}

		return user;
	}

	private String issueToken(User user) throws IOException {

		user.setActivity(new Activity());
		user.getActivity().setId(user.getId());
		user.getActivity().setDateDerniereActivite(new Date());
		user.getActivity().setToken(generateToken());

		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();

			session.beginTransaction();

			session.saveOrUpdate(user.getActivity());

			session.getTransaction().commit();

		} catch (RuntimeException e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
			logger.error("RuntimeException in UserService/updateUser : " + e.getMessage());
		} finally {
			session.close();
		}

		return user.getActivity().getToken();
	}

	// Vérification token Google
	// private String issueToken(User user) throws IOException {
	// JsonFactory jsonFactory = new JacksonFactory();
	// HttpTransport httpTransport = new NetHttpTransport();
	//
	// GoogleTokenResponse tokenResponse = new
	// GoogleAuthorizationCodeTokenRequest(
	// httpTransport, jsonFactory,CLIENT_ID, SECRET_ID ,"code",
	// "postmessage").execute();
	//
	// GoogleCredential credential = new GoogleCredential.Builder()
	// .setJsonFactory(jsonFactory)
	// .setTransport(httpTransport)
	// .setClientSecrets(CLIENT_ID, SECRET_ID).build()
	// .setFromTokenResponse(tokenResponse);
	//
	// Oauth2 oauth2 = new Oauth2.Builder(httpTransport, jsonFactory,
	// credential).setApplicationName("MySwap").build();
	// Tokeninfo tokenInfo =
	// oauth2.tokeninfo().setAccessToken(credential.getAccessToken()).execute();
	//
	// // notes : retour des infos user au format JSON
	// return oauth2.userinfo().get().execute().toString();
	// }

	/**
	 * génère une chaine de 30 caractères aléatoires
	 * 
	 * @return la chaine, qui sert de token simple.
	 */
	private static String generateToken() {
		String chars = "a";
		StringBuilder pass = new StringBuilder(30);
		for (int x = 0; x < 30; x++) {
			int i = (int) (Math.random() * chars.length());
			pass.append(chars.charAt(i));
		}
		return pass.toString();
	}
}