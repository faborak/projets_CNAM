package com.yaps.petstore.server.cart;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.ejb.Stateful;

import com.yaps.petstore.server.domain.item.Item;
import com.yaps.petstore.server.domain.item.ItemDAO;
import com.yaps.petstore.server.service.AbstractRemoteService;

/**
 * This class manages un ShoppingCart.
 *
 */
@Stateful(name = "ShoppingCartSB")
public class ShoppingCartBean extends AbstractRemoteService implements ShoppingCart {

	// ======================================
	// = Attributes =
	// ======================================
	private static final ItemDAO _itemDAO = new ItemDAO();
	private Map<String, Integer> cart;
	
    // ======================================
    // =            Constructors            =
    // ======================================
	public ShoppingCartBean(){
		cart = new HashMap<String, Integer>(); 
    }
	
	// ======================================
	// = Business methods =
	// ======================================
	public Map getCart() {
		return cart;
	}

	public Collection getItems() {
		Collection<String> itemsId = new HashSet();
		for (String itemId : cart.keySet()){
			itemsId.add(itemId);
		}
		return itemsId;
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
		Item item = null;
		for (String itemId : cart.keySet()) {
			try {
				item = (Item) _itemDAO.findByPrimaryKey(itemId);
			} catch (Exception e){
				
			}
			totalCost += (item.getUnitCost() * cart.get(itemId));
		}
		return totalCost;
	}

	public void empty() {
		cart.clear();
	}
	
}
