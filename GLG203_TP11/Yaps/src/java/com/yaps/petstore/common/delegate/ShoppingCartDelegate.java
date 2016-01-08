package com.yaps.petstore.common.delegate;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Map;

import com.yaps.petstore.common.locator.ServiceLocator;
import com.yaps.petstore.server.cart.ShoppingCart;
import com.yaps.petstore.server.cart.ShoppingCartHome;

/**
 * This class follows the Delegate design pattern. It's a one to one method
 * with the ShoppingCart class. Each method delegates the call to the
 * ShoppingCart class
 */
public final class ShoppingCartDelegate {

    // ======================================
    // =             Attributes             =
    // ======================================
    

    // ======================================
    // =      Category Business methods     =
    // ======================================
  
    public ShoppingCartDelegate(String sessionId1) {
		// TODO Auto-generated constructor stub
	}

	/**
     * Delegates the call to the {@link ShoppingCart#getCart() ShoppingCart().getCart} method.
     */
	public Map getCart() {
        return getShoppingCart().getCart();
    }
	
	public Collection getItems() {
		return getShoppingCart().getItems();
	}

	public void addItem(String itemId) {
		getShoppingCart().addItem(itemId);
	}

	public void removeItem(String itemId) {
		getShoppingCart().removeItem(itemId);
	}

	public void updateItemQuantity(String itemId, int newQty) {
		getShoppingCart().updateItemQuantity(itemId, newQty);
	}

	public Double getTotal() {
		return getShoppingCart().getTotal();
	}

	public void empty() {
		getShoppingCart().empty();	
	}
    
    // ======================================
    // =            Private methods         =
    // ======================================
    private static ShoppingCart getShoppingCart() /*throws RemoteException*/ {
    	ShoppingCart ShoppingCart = null;
        try {
            // Looks up for the home interface
        	ShoppingCart = (ShoppingCart) ServiceLocator.getInstance().getHome(ShoppingCartHome.JNDI_NAME);
        } catch (Exception e) {
//            throw new RemoteException("Lookup or Create exception", e);
        }

        return ShoppingCart;
    }

	public String getSessionId() {
		// TODO Auto-generated method stub
		return null;
	}
}
