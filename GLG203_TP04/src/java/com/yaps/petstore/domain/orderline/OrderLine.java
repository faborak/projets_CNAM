package com.yaps.petstore.domain.orderline;

import java.io.Serializable;

import com.yaps.petstore.domain.DomainObject;
import com.yaps.petstore.domain.item.Item;
import com.yaps.petstore.domain.order.Order;
import com.yaps.petstore.exception.CheckException;

public class OrderLine extends DomainObject implements Serializable {

	// ======================================
	// = Attributes =
	// ======================================
	private int _Quantity;
	private double _UnitCost;
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
		_Quantity = i;
		_UnitCost = Double.parseDouble(Integer.toString(j));
		_order = order;
		_item = item;
	}

	public OrderLine(String id, int _defaultQuantity, double _defaultUnitCost,
			Order order, Item item) {
		_id = id;
		_Quantity = _defaultQuantity;
		_UnitCost = _defaultUnitCost;
		_order = order;
		_item = item;
	}

	public OrderLine(int parseInt, double parseDouble, Order order, Item item) {
		_Quantity = parseInt;
		_UnitCost = parseDouble;
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
		if (_Quantity == 0)
			throw new CheckException("Invalid orderline quantity");
		if (_UnitCost == 0)
			throw new CheckException("Invalid orderline unitcost");
		if (_order == null)
			throw new CheckException("Invalid orderline order");
		if (_item == null)
			throw new CheckException("Invalid orderline item");
	}

	public int getQuantity() {
		return _Quantity;
	}

	public void setQuantity(int quantity) {
		_Quantity = quantity;
	}

	public double getUnitCost() {
		return _UnitCost;
	}

	public void setUnitCost(double unitCost) {
		_UnitCost = unitCost;
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

}
