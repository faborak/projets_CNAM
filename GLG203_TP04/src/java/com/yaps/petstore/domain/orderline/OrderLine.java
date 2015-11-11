package com.yaps.petstore.domain.orderline;

import java.io.Serializable;

import com.yaps.petstore.domain.DomainObject;
import com.yaps.petstore.domain.item.Item;
import com.yaps.petstore.domain.order.Order;
import com.yaps.petstore.exception.CheckException;

/**
 * This class represents a orderline for the YAPS company.
 */
public class OrderLine extends DomainObject implements Serializable {

	// ======================================
	// = Attributes =
	// ======================================
	private int _quantity;
	private double _unitCost;
	private Order _order;
	private Item _item;

	// ======================================
	// = Constructors =
	// ======================================
	public OrderLine() {
	}

	public OrderLine(final String id) {
		_id = id;
	}

	public OrderLine(String id, int i, int j, Order order, Item item) {
		_id = id;
		_quantity = i;
		_unitCost = Double.parseDouble(Integer.toString(j));
		_order = order;
		_item = item;
	}

	public OrderLine(String id, int _defaultQuantity, double _defaultUnitCost,
			Order order, Item item) {
		_id = id;
		_quantity = _defaultQuantity;
		_unitCost = _defaultUnitCost;
		_order = order;
		_item = item;
	}

	public OrderLine(int parseInt, double parseDouble, Order order, Item item) {
		_quantity = parseInt;
		_unitCost = parseDouble;
		_order = order;
		_item = item;
	}

	// ======================================
	// = Business methods =
	// ======================================
	/**
	 * This method checks the integrity of the object data.
	 * 
	 * @throws CheckException
	 *             if data is invalid
	 */
	public void checkData() throws CheckException {
		if (_quantity == 0)
			throw new CheckException("Invalid orderline quantity");
		if (_unitCost == 0)
			throw new CheckException("Invalid orderline unitcost");
		if (_order == null)
			throw new CheckException("Invalid orderline order");
		if (_item == null)
			throw new CheckException("Invalid orderline item");
	}

    // ======================================
    // =         Getters and Setters        =
    // ======================================
	public int getQuantity() {
		return _quantity;
	}

	public void setQuantity(int quantity) {
		_quantity = quantity;
	}

	public double getUnitCost() {
		return _unitCost;
	}

	public void setUnitCost(double unitCost) {
		_unitCost = unitCost;
	}

	public Order getOrder() {
		return _order;
	}

	public void setOrder(Order order) {
		this._order = order;
	}

	public Item getItem() {
		return _item;
	}

    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("\n\tCustomer {");
        buf.append("\n\t\tId=").append(_id);
        buf.append("\n\t\tQuantity=").append(_quantity);
        buf.append("\n\t\tUnitCost=").append(_unitCost);
        buf.append("\n\t}");
        return buf.toString();
    }
	
}
