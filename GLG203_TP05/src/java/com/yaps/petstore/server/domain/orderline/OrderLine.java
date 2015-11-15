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
        _id = id;
    }

    public OrderLine(final String id, final int quantity, final double unitCost, final Order order, final Item item) {
        _id = id;
        _quantity = quantity;
        _unitCost = unitCost;
        _order = order;
        _item = item;
    }

    public OrderLine(final int quantity, final double unitCost, final Order order, final Item item) {
        _quantity = quantity;
        _unitCost = unitCost;
        _order = order;
        _item = item;
    }

    // ======================================
    // =           Business methods         =
    // ======================================
    public void checkData() throws CheckException {
        if (_unitCost <= 0)
            throw new CheckException("Invalid unit cost");
        if (_quantity <= 0)
            throw new CheckException("Invalid quantity");
        if (_order == null)
            throw new CheckException("Invalid order");
        if (_item == null)
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
        buf.append("\n\tId=").append(_id);
        buf.append("\n\tQuantity=").append(_quantity);
        buf.append("\n\tUnit Cost=").append(_unitCost);
        buf.append("\n\tItem Id=").append(_item.getId());
        buf.append("\n\tItem Name=").append(_item.getName());
        buf.append("\n}");
        return buf.toString();
    }
}
