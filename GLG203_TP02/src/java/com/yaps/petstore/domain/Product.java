package com.yaps.petstore.domain;

import java.util.Collection;

import com.yaps.petstore.exception.CheckException;

public class Product {
	String _id;
	String _name;
	String _description;
	Collection<Item> _items;
	Category category;


	public Product(String id, String name, String description, Category category) {
		this._id = id;
		this._name =name;
		this._description = description;
		this.category = category;
	}

	public Product() {
	}

	public void checkid(String id) throws CheckException {
		if (_id == null || "".equals(_id))
			throw new CheckException("Invalid id");
	}

	public void checkData() throws CheckException {
        if (_name == null || "".equals(_name))
            throw new CheckException("Invalid product name");
        if (_description == null || "".equals(_description))
            throw new CheckException("Invalid product description");
        if (category == null || category.getId() == null)
            throw new CheckException("Invalid category");
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

	public Collection<Item> getItems() {
		return _items;
	}

	public void setItems(Collection<Item> items) {
		_items = items;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Object getCategory() {
		return category;
	}

}
