package com.yaps.petstore.domain;

import com.yaps.petstore.exception.CheckException;

public class Item {

	String _id;
	String _name;
	double unitCost;
	Product product;

	public Item(String id, String name, double unitCost, Product product) {
		this._id = id;
		this._name = name;
		this.unitCost = unitCost;
		this.product = product;
	}

	public Item() {
	}

	public void checkid(String id) throws CheckException {
		if (_id == null || "".equals(_id))
			throw new CheckException("Invalid id");
	}

	public void checkData() throws CheckException {
        if (_name == null || "".equals(_name))
            throw new CheckException("Invalid item name");
//        if (unitCost == null || unitCost == 0)
        if (unitCost == 0)
            throw new CheckException("Invalid Cost");
        if (product == null || product.getId() == null)
            throw new CheckException("Invalid Product");
	}

	public String getId() {
		return _id;
	}

	public void setId(String id) {
		_id = id;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public double getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Object getProduct() {
		return product;
	}

}
