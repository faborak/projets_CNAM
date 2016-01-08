package com.yaps.petstore.server.cart;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.yaps.petstore.server.domain.item.Item;
import com.yaps.petstore.server.domain.item.ItemDAO;
import com.yaps.petstore.server.service.AbstractRemoteService;

import javax.ejb.Stateless;

/**
 * This class manages un ShoppingCart.
 *
 * @see com.barkbank.verifier.VerifyCreditCardServlet
 */
// @Stateless (name="CreditCartServiceSB", mappedName = CreditCardServiceLocalHome.JNDI_NAME)
@Stateless (name="ShoppingCartSB")
public class ShoppingCartBean extends AbstractRemoteService implements ShoppingCart {

    // ======================================
    // =             Attributes             =
    // ======================================
    
    private static final ItemDAO _itemDAO = new ItemDAO();
    
    Map<String,Integer> cart ;
    
    // ======================================
    // =           Business methods         =
    // ======================================
	public Map getCart() {
		return cart;
	}

	public Collection getItems() {
		return cart.keySet();
	}

	public void addItem(String itemId) {
		cart.put(itemId, 1);
	}

	public void removeItem(String itemId) {
		cart.remove(itemId);
	}

	public void updateItemQuantity(String itemId, int newQty) {
		cart.remove(itemId);
		cart.put(itemId, newQty);
	}

	public Double getTotal() {
		double totalCost = 0;
		for (String itemId : cart.keySet()){
			final Item item = (Item)_itemDAO.findByPrimaryKey(itemId);
			totalCost += (item.getUnitCost() * cart.get(itemId));
		}
		return totalCost;
	}

	public void empty() {
		cart.clear();	
	}

}
