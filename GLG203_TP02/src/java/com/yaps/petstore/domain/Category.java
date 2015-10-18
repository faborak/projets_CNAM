package com.yaps.petstore.domain;

import java.io.Serializable;
import java.util.Collection;

import com.yaps.petstore.exception.CheckException;

/**
 * 
 * @author fabo
 * La classe décrit une catégorie du système.
 *
 */
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	// ======================================
    // =             Attributes             =
    // ======================================
	String _id;
	String _name;
	String _description;
	Collection<Product> _products;
	
	// ======================================
    // =            Constructors            =
    // ======================================
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

	public Collection<Product> getProducts() {
		return _products;
	}

	public void setProducts(Collection<Product> products) {
		_products = products;
	}

	// ======================================
    // =           check methods          =
    // ======================================
	/**
	 * 
	 * @param id : id à tester.
	 * @throws CheckException : Erreyr levée en cas de problème sur l'id.
	 */
	public void checkid(String id) throws CheckException{
		if (_id == null || "".equals(_id))
			throw new CheckException("Invalid id first name");
	}
	
	/**
	 * Inspection des variables du composant.
	 * @throws CheckException : erreur levée en cas de problème sur une donnée.
	 */
	public void checkData() throws CheckException {
        if (_name == null || "".equals(_name))
            throw new CheckException("Invalid category name");
        if (_description == null || "".equals(_description))
            throw new CheckException("Invalid category description");
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


