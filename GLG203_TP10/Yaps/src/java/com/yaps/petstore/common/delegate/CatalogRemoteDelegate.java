package com.yaps.petstore.common.delegate;

import java.rmi.Naming;
import java.rmi.RemoteException;

import com.yaps.petstore.common.rmi.RMIConstant;

/**
 * This class follows the Delegate design pattern. It's a one to one method
 * with the CatalogService class. Each method delegates the call to the
 * CatalogService class
 */
public final class CatalogRemoteDelegate extends CatalogDelegate implements RMIConstant {

    // ======================================
    // =             Attributes             =
    // ======================================
    private static CatalogServiceRemote catalogServiceRemote;

    // ======================================
    // =            Private methods         =
    // ======================================
    protected CatalogServiceRemote getCatalogService() throws RemoteException {
        try {
            catalogServiceRemote = (CatalogServiceRemote) Naming.lookup(CATALOG_SERVICE_URL);
        } catch (Exception e) {
            throw new RemoteException("Lookup exception", e);
        }
        return catalogServiceRemote;
    }
}
