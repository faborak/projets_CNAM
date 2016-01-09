package com.yaps.petstore.common.delegate;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.yaps.petstore.common.locator.ServiceLocator;
import com.yaps.petstore.server.cart.ShoppingCart;
import com.yaps.petstore.server.cart.ShoppingCartBean;
import com.yaps.petstore.server.cart.ShoppingCartHome;
import com.yaps.petstore.server.domain.item.ItemDAO;

/**
 * This class follows the Delegate design pattern. It's a one to one method
 * with the ShoppingCart class. Each method delegates the call to the
 * ShoppingCart class
 */
public final class ShoppingCartDelegate {

	// ======================================
    // =             Constructor            =
    // ======================================
    public ShoppingCartDelegate(String sessionId) {
    	this.sessionId = sessionId;
    	ShoppingCart = getShoppingCart();
	}
	
    // ======================================
    // =             Attributes             =
    // ======================================
    String sessionId;
    ShoppingCart ShoppingCart;

    // ======================================
    // =      Category Business methods     =
    // ======================================
  
	/**
     * Delegates the call to the {@link ShoppingCart#getCart() ShoppingCart().getCart} method.
     */
	public Map getCart() {
        return ShoppingCart.getCart();
    }
	
	/**
     * Delegates the call to the {@link ShoppingCart#getItems() ShoppingCart().getItems} method.
     */
	public Collection getItems() {
		return ShoppingCart.getItems();
	}

	/**
     * Delegates the call to the {@link ShoppingCart#addItem() ShoppingCart().addItem} method.
     */
	public void addItem(String itemId) {
		ShoppingCart.addItem(itemId);
	}

	/**
     * Delegates the call to the {@link ShoppingCart#removeItem() ShoppingCart().removeItem} method.
     */
	public void removeItem(String itemId) {
		ShoppingCart.removeItem(itemId);
	}

	/**
     * Delegates the call to the {@link ShoppingCart#updateItemQuantity() ShoppingCart().updateItemQuantity} method.
     */
	public void updateItemQuantity(String itemId, int newQty) {
		ShoppingCart.updateItemQuantity(itemId, newQty);
	}

	/**
     * Delegates the call to the {@link ShoppingCart#getTotal() ShoppingCart().getTotal} method.
     */
	public Double getTotal() {
		return ShoppingCart.getTotal();
	}

	/**
     * Delegates the call to the {@link ShoppingCart#empty() ShoppingCart().empty} method.
     */
	public void empty() {
		ShoppingCart.empty();	
	}
	
	public String getSessionId() {
		return sessionId;
	}
    
    // ======================================
    // =            Private methods         =
    // ======================================
    private static ShoppingCart getShoppingCart() {
    	ShoppingCart ShoppingCart = null;
        try {
            // Looks up for the home interface
        	ShoppingCart = (ShoppingCart) ServiceLocator.getInstance().getHome(ShoppingCartHome.JNDI_NAME);
        } catch (Exception e) {
//            throw new RemoteException("Lookup or Create exception", e);
        }

        return ShoppingCart;
    }

}
