package com.yaps.petstore.common.delegate;

import java.rmi.RemoteException;

import com.yaps.petstore.server.service.catalog.CatalogService;

/**
 * This class follows the Delegate design pattern. It's a one to one method
 * with the CatalogService class. Each method delegates the call to the
 * CatalogService class
 */
public final class CatalogLocalDelegate extends CatalogDelegate {

    // ======================================
    // =             Attributes             =
    // ======================================
    private static CatalogServiceRemote CatalogServiceRemote;

    // ======================================
    // =            Private methods         =
    // ======================================
    protected CatalogServiceRemote getCatalogService() throws RemoteException {
    	if ( CatalogServiceRemote == null )
    		CatalogServiceRemote = new CatalogService();
    	return CatalogServiceRemote;
    }
}
