package com.yaps.petstore.common.delegate;

import com.yaps.petstore.common.dto.CategoryDTO;
import com.yaps.petstore.common.dto.ItemDTO;
import com.yaps.petstore.common.dto.ProductDTO;
import com.yaps.petstore.common.exception.*;
import com.yaps.petstore.common.locator.ServiceLocator;
import com.yaps.petstore.server.service.catalog.CatalogService;
import com.yaps.petstore.server.service.catalog.CatalogServiceHome;
import com.yaps.petstore.server.service.customer.CustomerService;
import com.yaps.petstore.server.service.customer.CustomerServiceHome;

import java.rmi.RemoteException;
import java.util.Collection;

/**
 * This class follows the Delegate design pattern. It's a one to one method
 * with the CatalogService class. Each method delegates the call to the
 * CatalogService class
 */
public abstract class ShoppingCartDelegate {

    // ======================================
    // =             Attributes             =
    // ======================================
    

    // ======================================
    // =      Category Business methods     =
    // ======================================
  
    /**
     * Delegates the call to the {@link CatalogServiceRemote#findProducts(String) CatalogServiceRemote().findProducts} method.
     */
//    public Collection findProducts(String categoryId) throws FinderException, RemoteException {
//        return getCatalogService().findProducts(categoryId);
//    }
    
    // ======================================
    // =            Private methods         =
    // ======================================
    private static CatalogService getCatalogService() throws RemoteException {
    	CatalogService catalogServiceRemote = null;
        try {
            // Looks up for the home interface
        	catalogServiceRemote = (CatalogService) ServiceLocator.getInstance().getHome(CatalogServiceHome.JNDI_NAME);
            // customerServiceRemote = (CustomerService) ServiceLocator.getInstance().getHome(CustomerServiceHome.JNDI_NAME_FOR_REMOTE_CLIENTS);
        } catch (Exception e) {
            throw new RemoteException("Lookup or Create exception", e);
        }

        return catalogServiceRemote;
    }
    
}
