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
public abstract class CatalogDelegate {

    // ======================================
    // =             Attributes             =
    // ======================================
    

    // ======================================
    // =      Category Business methods     =
    // ======================================
    /**
     * Delegates the call to the {@link CatalogServiceRemote#createCategory(CategoryDTO) CatalogServiceRemote().createCategory} method.
     */
    public static CategoryDTO createCategory(final CategoryDTO categoryDTO) throws CreateException, CheckException, RemoteException {
        return getCatalogService().createCategory(categoryDTO);
    }

    /**
     * Delegates the call to the {@link CatalogServiceRemote#findCategory(String) CatalogServiceRemote().findCategory} method.
     */
    public CategoryDTO findCategory(final String categoryId) throws FinderException, CheckException, RemoteException {
        return getCatalogService().findCategory(categoryId);
    }

    /**
     * Delegates the call to the {@link CatalogServiceRemote#deleteCategory(String) CatalogServiceRemote().deleteCategory} method.
     */
    public void deleteCategory(final String categoryId) throws RemoveException, CheckException, RemoteException {
        getCatalogService().deleteCategory(categoryId);
    }

    /**
     * Delegates the call to the {@link CatalogServiceRemote#updateCategory(CategoryDTO) CatalogServiceRemote().updateCategory} method.
     */
    public void updateCategory(final CategoryDTO categoryDTO) throws UpdateException, CheckException, RemoteException {
        getCatalogService().updateCategory(categoryDTO);
    }

    /**
     * Delegates the call to the {@link CatalogServiceRemote#findCategories() CatalogServiceRemote().findCategories} method.
     */
    public Collection findCategories() throws FinderException, RemoteException {
        return getCatalogService().findCategories();
    }

    // ======================================
    // =      Product Business methods     =
    // ======================================
    /**
     * Delegates the call to the {@link CatalogServiceRemote#createProduct(ProductDTO) CatalogServiceRemote().createProduct} method.
     */
    public ProductDTO createProduct(final ProductDTO productDTO) throws CreateException, CheckException, RemoteException {
        return getCatalogService().createProduct(productDTO);
    }

    /**
     * Delegates the call to the {@link CatalogServiceRemote#findProduct(String) CatalogServiceRemote().findProduct} method.
     */
    public ProductDTO findProduct(final String productId) throws FinderException, CheckException, RemoteException {
        return getCatalogService().findProduct(productId);
    }

    /**
     * Delegates the call to the {@link CatalogServiceRemote#deleteProduct(String) CatalogServiceRemote().deleteProduct} method.
     */
    public void deleteProduct(final String productId) throws RemoveException, CheckException, RemoteException {
        getCatalogService().deleteProduct(productId);
    }

    /**
     * Delegates the call to the {@link CatalogServiceRemote#updateProduct(ProductDTO) CatalogServiceRemote().updateProduct} method.
     */
    public void updateProduct(final ProductDTO productDTO) throws UpdateException, CheckException, RemoteException {
        getCatalogService().updateProduct(productDTO);
    }

    /**
     * Delegates the call to the {@link CatalogServiceRemote#findProducts() CatalogServiceRemote().findProducts} method.
     */
    public Collection findProducts() throws FinderException, RemoteException {
        return getCatalogService().findProducts();
    }

    /**
     * Delegates the call to the {@link CatalogServiceRemote#findProducts(String) CatalogServiceRemote().findProducts} method.
     */
    public Collection findProducts(String categoryId) throws FinderException, RemoteException {
        return getCatalogService().findProducts(categoryId);
    }

    // ======================================
    // =        Item Business methods       =
    // ======================================
    /**
     * Delegates the call to the {@link CatalogServiceRemote#createItem(ItemDTO) CatalogServiceRemote().createItem} method.
     */
    public ItemDTO createItem(final ItemDTO item) throws CreateException, CheckException, RemoteException {
        return getCatalogService().createItem(item);
    }

    /**
     * Delegates the call to the {@link CatalogServiceRemote#findItem(String) CatalogServiceRemote().findItem} method.
     */
    public ItemDTO findItem(final String itemId) throws FinderException, CheckException, RemoteException {
        return getCatalogService().findItem(itemId);
    }

    /**
     * Delegates the call to the {@link CatalogServiceRemote#deleteItem(String) CatalogServiceRemote().deleteItem} method.
     */
    public void deleteItem(final String itemId) throws RemoveException, CheckException, RemoteException {
        getCatalogService().deleteItem(itemId);
    }

    /**
     * Delegates the call to the {@link CatalogServiceRemote#updateItem(ItemDTO) CatalogServiceRemote().updateItem} method.
     */
    public void updateItem(final ItemDTO item) throws UpdateException, CheckException, RemoteException {
        getCatalogService().updateItem(item);
    }

    /**
     * Delegates the call to the {@link CatalogServiceRemote#findItems() CatalogServiceRemote().findItems} method.
     */
    public Collection findItems() throws FinderException, RemoteException {
        return getCatalogService().findItems();
    }

    /**
     * Delegates the call to the {@link CatalogServiceRemote#findItems(String) CatalogServiceRemote().findItems} method.
     */
    public Collection findItems(String productId) throws FinderException, RemoteException {
        return getCatalogService().findItems(productId);
    }

    /**
     * Delegates the call to the {@link CatalogServiceRemote#searchItems(String) CatalogServiceRemote().searchItems} method.
     */
    public Collection searchItems(String keyword) throws FinderException, RemoteException {
        return getCatalogService().searchItems(keyword);
    }
    
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
