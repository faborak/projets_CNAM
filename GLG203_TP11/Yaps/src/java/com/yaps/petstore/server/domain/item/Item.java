package com.yaps.petstore.server.domain.item;

import java.io.Serializable;

import com.yaps.petstore.server.domain.DomainObject;
import com.yaps.petstore.server.domain.product.Product;
import com.yaps.petstore.common.exception.CheckException;

/**
 * This class represents an Item in the catalog of the YAPS company.
 * The catalog is divided into categories. Each one divided into products
 * and each product in items.
 */
public final class Item extends DomainObject implements Serializable {

    // ======================================
    // =             Attributes             =
    // ======================================
    private String _name;
    private double _unitCost;
    private String _imagePath;
    private Product _product;

    // ======================================
    // =            Constructors            =
    // ======================================
    public Item() {
    }

    public Item(final String id) {
        setId(id);
    }

    public Item(final String id, final String name, final double unitCost, final Product product) {
        setId(id);
        setName(name);
        setUnitCost(unitCost);
        setProduct(product);
    }

    // ======================================
    // =           Business methods         =
    // ======================================
    public void checkData() throws CheckException {
        if (getName() == null || "".equals(getName()))
            throw new CheckException("Invalid name");
        if (getUnitCost() <= 0)
            throw new CheckException("Invalid unit cost");
        if (getProduct() == null || getProduct().getId() == null || "".equals(getProduct().getId()))
            throw new CheckException("Invalid product");
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

    public double getUnitCost() {
        return _unitCost;
    }

    public void setUnitCost(final double unitCost) {
        _unitCost = unitCost;
    }

    public String getImagePath() {
        return _imagePath;
    }

    public void setImagePath(final String imagePath) {
        _imagePath = imagePath;
    }

    public Product getProduct() {
        return _product;
    }

    public void setProduct(final Product product) {
        _product = product;
    }

    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("\n\tItem {");
        buf.append("\n\t\tId=").append(getId());
        buf.append("\n\t\tName=").append(getName());
        buf.append("\n\t\tUnit Cost=").append(getUnitCost());
        buf.append("\n\t\timagePath=").append(getImagePath());
        buf.append("\n\t\tProduct Id=").append(getProduct().getId());
        buf.append("\n\t\tProduct Name=").append(getProduct().getName());
        buf.append("\n\t}");
        return buf.toString();
    }
}
