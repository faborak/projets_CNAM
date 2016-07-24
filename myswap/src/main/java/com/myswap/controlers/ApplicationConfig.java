package com.myswap.controlers;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

@ApplicationPath("/rest")
public class ApplicationConfig extends Application {

//	Pour Jersey V2
	
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new java.util.HashSet<>();
		addRestResourceClasses(resources);
		return resources;
	}
	
	// for returns in Json.
	@Override
	public Set<Object> getSingletons() {
	    final Set<Object> instances = new HashSet<Object>();

	    instances.add(new JacksonJsonProvider());
	    return instances;
	}

	/**
	 * Do not modify addRestResourceClasses() method. It is automatically
	 * populated with all resources defined in the project. If required, comment
	 * out calling this method in getClasses().
	 */
	//supprimer le contenu ci-dessous apres avior résolu les erreurs authent
	private void addRestResourceClasses(Set<Class<?>> resources) {
		resources.add(com.myswap.services.UserService.class);
		resources.add(com.myswap.services.ItemService.class);
		resources.add(com.myswap.services.DealService.class);
		resources.add(com.myswap.services.CommentService.class);
	}
}