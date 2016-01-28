package com.yaps.petstore.server.domain.category;

import com.yaps.petstore.server.domain.DomainObject;
import com.yaps.petstore.common.exception.CheckException;

import java.io.Serializable;
import java.util.Collection;

/**
 * This class represents a Category in the catalog of the YAPS company.
 * The catalog is divided into categories. Each one divided into products
 * and each product in items.
 */
public final class Category extends DomainObject implements Serializable {

    // ======================================
    // =             Attributes             =
    // ======================================
    private String _name;
    private String _description;
    private Collection _products;

    // ======================================
    // =            Constructors            =
    // ======================================
    public Category() {
    }

    public Category(final String id) {
        setId(id);
    }

    public Category(final String id, final String name, final String description) {
        setId(id);
        setName(name);
        setDescription(description);
    }

    // ======================================
    // =           Business methods         =
    // ======================================

    public void checkData() throws CheckException {
        if (getName() == null || "".equals(getName()))
            throw new CheckException("Invalid name");
        if (getDescription() == null || "".equals(getDescription()))
            throw new CheckException("Invalid description");
    }

    // ======================================
    // =         Getters and Setters        =
    // ======================================
    public String getName() {
        return _name;
    }

    public void setName(final String name) {
        _name = name;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(final String description) {
        _description = description;
    }

    public Collection getProducts() {
        return _products;
    }
    
    private void setProducts(final Collection products) {
        _products = products;
    }

    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("\n\tCategory {");
        buf.append("\n\t\tId=").append(getId());
        buf.append("\n\t\tName=").append(getName());
        buf.append("\n\t\tDescription=").append(getDescription());
        buf.append("\n\t}");
        return buf.toString();
    }
}
