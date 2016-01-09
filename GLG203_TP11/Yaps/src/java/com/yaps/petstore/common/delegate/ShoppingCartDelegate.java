package com.yaps.petstore.common.delegate;

import java.util.Collection;
import java.util.Map;

import com.yaps.petstore.common.locator.ServiceLocator;
import com.yaps.petstore.server.cart.ShoppingCart;
import com.yaps.petstore.server.cart.ShoppingCartBean;
import com.yaps.petstore.server.cart.ShoppingCartHome;

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
    	shoppingCart = getShoppingCart();
	}
	
    // ======================================
    // =             Attributes             =
    // ======================================
    String sessionId;
    ShoppingCart shoppingCart;

    // ======================================
    // =      Category Business methods     =
    // ======================================
  
	/**
     * Delegates the call to the {@link ShoppingCart#getCart() ShoppingCart().getCart} method.
     */
	public Map getCart() {
        return shoppingCart.getCart();
    }
	
	public Collection getItems() {
		return shoppingCart.getItems();
	}

	public void addItem(String itemId) {
		shoppingCart.addItem(itemId);
	}

	public void removeItem(String itemId) {
		shoppingCart.removeItem(itemId);
	}

	public void updateItemQuantity(String itemId, int newQty) {
		shoppingCart.updateItemQuantity(itemId, newQty);
	}

	public Double getTotal() {
		return shoppingCart.getTotal();
	}

	public void empty() {
		shoppingCart.empty();	
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
