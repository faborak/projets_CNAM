package com.yaps.petstore.server.domain.orderline;

import com.yaps.petstore.server.domain.DomainObject;
import com.yaps.petstore.server.domain.item.Item;
import com.yaps.petstore.server.domain.order.Order;
import com.yaps.petstore.common.exception.CheckException;

import java.io.Serializable;

/**
 * An Order has several order lines. This class represent one order line.
 */
public final class OrderLine extends DomainObject implements Serializable {

    // ======================================
    // =             Attributes             =
    // ======================================
    private int _quantity;
    private double _unitCost;
    private Item _item;
    private Order _order;

    // ======================================
    // =            Constructors            =
    // ======================================
    public OrderLine() {
    }

    public OrderLine(final String id) {
        setId(id);
    }

    public OrderLine(final String id, final int quantity, final double unitCost, final Order order, final Item item) {
        setId(id);
        setQuantity(quantity);
        setUnitCost(unitCost);
        setOrder(order);
        setItem(item);
    }

    public OrderLine(final int quantity, final double unitCost, final Order order, final Item item) {
        setQuantity(quantity);
        setUnitCost(unitCost);
        setOrder(order);
        setItem(item);
    }

    // ======================================
    // =           Business methods         =
    // ======================================
    public void checkData() throws CheckException {
        if (getUnitCost() <= 0)
            throw new CheckException("Invalid unit cost");
        if (getQuantity() <= 0)
            throw new CheckException("Invalid quantity");
        if (getOrder() == null)
            throw new CheckException("Invalid order");
        if (getItem() == null)
            throw new CheckException("Invalid item");
    }

    // ======================================
    // =         Getters and Setters        =
    // ======================================
    public int getQuantity() {
        return _quantity;
    }

    public void setQuantity(final int quantity) {
        _quantity = quantity;
    }

    public double getUnitCost() {
        return _unitCost;
    }

    public void setUnitCost(final double unitCost) {
        _unitCost = unitCost;
    }

    public Order getOrder() {
        return _order;
    }

    public void setOrder(final Order order) {
        _order = order;
    }

    public Item getItem() {
        return _item;
    }

    public void setItem(final Item item) {
        _item = item;
    }

    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("\nOrderLine {");
        buf.append("\n\tId=").append(getId());
        buf.append("\n\tQuantity=").append(getQuantity());
        buf.append("\n\tUnit Cost=").append(getUnitCost());
        buf.append("\n\tItem Id=").append(getItem().getId());
        buf.append("\n\tItem Name=").append(getItem().getName());
        buf.append("\n}");
        return buf.toString();
    }
}
