package com.yaps.petstore.domain.orderline;

import com.yaps.petstore.domain.item.Item;
import com.yaps.petstore.domain.order.Order;
import com.yaps.petstore.exception.CheckException;

public class OrderLine {

	private int Quantity;
	private double UnitCost;
	private Order order;
	private Item item;
	
	public OrderLine(String string, int i, int j, Order order, Item item) {
		// TODO Auto-generated constructor stub
	}

	public OrderLine(String string, int _defaultQuantity, double _defaultUnitCost, Order order, Item item) {
		// TODO Auto-generated constructor stub
	}

	public OrderLine(int parseInt, double parseDouble, Order order2, Item item) {
		// TODO Auto-generated constructor stub
	}

	public void checkData() throws CheckException{
		// TODO Auto-generated method stub
		
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	public double getUnitCost() {
		return UnitCost;
	}

	public void setUnitCost(double unitCost) {
		UnitCost = unitCost;
	}

	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	public Item getItem() {
		return item;
	}

}
