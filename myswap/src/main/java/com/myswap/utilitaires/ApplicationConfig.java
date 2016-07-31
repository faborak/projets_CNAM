package com.myswap.utilitaires;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

//@Secured => securiser tout l'application
@ApplicationPath("/rest")
public class ApplicationConfig extends Application {

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
	private void addRestResourceClasses(Set<Class<?>> resources) {
		resources.add(com.myswap.ressources.UserRessource.class);
		resources.add(com.myswap.ressources.ItemRessource.class);
		resources.add(com.myswap.ressources.DealRessource.class);
		resources.add(com.myswap.ressources.CommentRessource.class);
		resources.add(com.myswap.ressources.AuthenticationRessource.class);
		resources.add(com.myswap.utilitaires.AuthenticationFilter.class);
	}
}