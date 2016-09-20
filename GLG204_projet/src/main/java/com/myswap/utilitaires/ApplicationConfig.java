package com.myswap.utilitaires;

import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
* Jersey Settings. Add all ressources classes
*  to exposes WebServices of MySwap Application.
*/
@ApplicationPath("/rest")
public class ApplicationConfig extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new java.util.HashSet<>();
		addRestResourceClasses(resources);
		return resources;
	}
	
	private void addRestResourceClasses(Set<Class<?>> resources) {
		resources.add(com.myswap.ressources.UserRessource.class);
		resources.add(com.myswap.ressources.ItemRessource.class);
		resources.add(com.myswap.ressources.DealRessource.class);
		resources.add(com.myswap.ressources.CommentRessource.class);
		resources.add(com.myswap.ressources.AuthenticationRessource.class);
		resources.add(com.myswap.utilitaires.AuthenticationFilter.class);
	}
}