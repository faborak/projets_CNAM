package com.yaps.petstore.domain;

import java.util.Collection;

import com.yaps.petstore.exception.CheckException;

public class Category {

	String _id;
	String _name;
	String _description;
	Collection<Product> _products;
	
	public Category(String id, String name, String description) {
		this._id = id;
		this._name =name;
		this._description = description;
	}

	public Category() {
	}

	public Category(String id) {
		this._id = id;
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

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public Collection<Product> getProducts() {
		return _products;
	}

	public void setProducts(Collection<Product> products) {
		_products = products;
	}

	public void checkid(String id) throws CheckException{
		if (_id == null || "".equals(_id))
			throw new CheckException("Invalid id first name");
	}
	
	public void checkData() throws CheckException {
        if (_name == null || "".equals(_name))
            throw new CheckException("Invalid category name");
        if (_description == null || "".equals(_description))
            throw new CheckException("Invalid category description");
    }
	
	public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("\n\tCategory {");
        buf.append("\n\t\tId=").append(_id);
        buf.append("\n\t\tName=").append(_name);
        buf.append("\n\t\tDescription=").append(_description);
        buf.append("\n\t}");
        return buf.toString();
    }
	
}


