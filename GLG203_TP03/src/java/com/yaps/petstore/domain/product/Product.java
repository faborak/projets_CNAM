package com.yaps.petstore.domain.product;

import java.io.Serializable;
import java.util.Collection;

import com.yaps.petstore.domain.DomainObject;
import com.yaps.petstore.domain.category.Category;
import com.yaps.petstore.domain.item.Item;
import com.yaps.petstore.exception.CheckException;

/**
 * 
 * @author fabo
 * La classe decrit un produit du systeme.
 *
 */
public class Product extends DomainObject implements Serializable{

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

	public Product(String id) {
		this._id = id;
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
	 * @param id : id a tester.
	 * @throws CheckException : Erreur levee en cas de probleme sur l'id.
	 */
	public void checkid(String id) throws CheckException {
		if (_id == null || "".equals(_id))
			throw new CheckException("Invalid id");
	}

	/**
	 * Inspection des variables du composant.
	 * @throws CheckException : erreur levee en cas de probleme sur une donnee.
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
	 * Methode d'impression.
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
