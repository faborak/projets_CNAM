package com.yaps.petstore.server.cart;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;

import com.yaps.petstore.common.exception.CheckException;

/**
 * This class manages un ShoppingCart.
 *
 * @see com.barkbank.verifier.VerifyCreditCardServlet
 */
// @Stateless (name="CreditCartServiceSB", mappedName = CreditCardServiceLocalHome.JNDI_NAME)
@Stateless (name="CreditCartServiceSB")
public class ShoppingCartBean implements ShoppingCart {

    // ======================================
    // =             Attributes             =
    // ======================================
    // Used for logging
    private final transient String _cname = this.getClass().getName();
    
    Collection items ;

    // ======================================
    // =           Business methods         =
    // ======================================
	public Map getCart() {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection getItems() {
		return items;
	}

	public void addItem(String itemId) {
		items.add(itemId);
	}

	public void removeItem(String itemId) {
		Iterator iter=items.iterator();
		while(iter.hasNext()){
		    Object o=iter.next();
		    if(o.equals(itemId)){
		        iter.remove();
		    }
		}
	}

	public void updateItemQuantity(String itemId, int newQty) {
		// TODO Auto-generated method stub
		
	}

	public Double getTotal() {
		// TODO Auto-generated method stub
		return null;
	}

	public void empty() {
		Iterator iter=items.iterator();
		while(iter.hasNext()){
		        iter.remove();
		}
		
	}

}
