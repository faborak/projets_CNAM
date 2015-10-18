package com.yaps.petstore.domain;

import java.io.Serializable;
import java.util.Collection;

import com.yaps.petstore.exception.CheckException;

/**
 * 
 * @author fabo
 * La classe décrit un produit du système.
 *
 */
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// ======================================
    // =             Attributes             =
    // ======================================
	String _id;
	String _name;
	String _description;
	Collection<Item> _items;
	Category category;

	// ======================================
    // =            Constructors            =
    // ======================================	
	public Product(String id, String name, String description, Category category) {
		this._id = id;
		this._name =name;
		this._description = description;
		this.category = category;
	}

	public Product() {
	}


    // ======================================
    // =         Getters and Setters        =
    // ======================================
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

	public Category getCategory() {
		return category;
	}
	
	// ======================================
    // =           check methods          =
    // ======================================
	/**
	 * 
	 * @param id : id à tester.
	 * @throws CheckException : Erreyr levée en cas de problème sur l'id.
	 */
	public void checkid(String id) throws CheckException {
		if (_id == null || "".equals(_id))
			throw new CheckException("Invalid id");
	}

	/**
	 * Inspection des variables du composant.
	 * @throws CheckException : erreur levée en cas de problème sur une donnée.
	 */
	public void checkData() throws CheckException {
        if (_name == null || "".equals(_name))
            throw new CheckException("Invalid product name");
        if (_description == null || "".equals(_description))
            throw new CheckException("Invalid product description");
        if (category == null || category.getId() == null)
            throw new CheckException("Invalid category");
	}
	
	/**
	 * Méthode d'impression.
	 */
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
