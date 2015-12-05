package com.yaps.petstore.common.delegate;

public class CatalogDelegateFactory  {
	
	private static CatalogDelegate instance = null;
	
	public CatalogDelegate createCatalogDelegate() {
		if ( instance == null ) {
			String s = System.getProperty("useRMI");
			if ( s != null )
				instance = new CatalogRemoteDelegate();
			else
				instance = new CatalogLocalDelegate();
		}
		return instance;
	}

}
