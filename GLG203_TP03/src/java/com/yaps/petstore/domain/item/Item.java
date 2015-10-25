package com.yaps.petstore.domain.item;

import java.io.Serializable;

import com.yaps.petstore.domain.DomainObject;
import com.yaps.petstore.domain.product.Product;
import com.yaps.petstore.exception.CheckException;

/**
 * 
 * @author fabo
 * La classe decrit un objet du systeme.
 *
 */
public class Item extends DomainObject implements Serializable {

	private static final long serialVersionUID = 1L;

	// ======================================
    // =             Attributes             =
    // ======================================
	String _id;
	String _name;
	double unitCost;
	Product product;

	// ======================================
    // =            Constructors            =
    // ======================================
	public Item(String id, String name, double unitCost, Product product) {
		this._id = id;
		this._name = name;
		this.unitCost = unitCost;
		this.product = product;
	}

	public Item() {
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

	public double getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Product getProduct() {
		return product;
	}
	
	// ======================================
    // =           check methods          =
    // ======================================
	/**
	 * 
	 * @param id : id à tester.
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
            throw new CheckException("Invalid item name");
        if (unitCost == 0)
            throw new CheckException("Invalid Cost");
        if (product == null || product.getId() == null)
            throw new CheckException("Invalid Product");
	}

	/**
	 * Methode d'impression.
	 */
	public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("\n\tCategory {");
        buf.append("\n\t\tId=").append(_id);
        buf.append("\n\t\tName=").append(_name);
        buf.append("\n\t}");
        return buf.toString();
    }
}
