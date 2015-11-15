package com.yaps.petstore.common.dto;

/**
 * This class follows the Data Transfert Object design pattern and for that implements the
 * markup interface DataTransfertObject. It is a client view of an Order Line. This class only
 * transfers data from a distant service to a client.
 */
public final class OrderLineDTO implements DataTransfertObject {

    // ======================================
    // =             Attributes             =
    // ======================================
    private int _quantity;
    private double _unitCost;
    private String _itemId;
    private String _itemName;

    // ======================================
    // =            Constructors            =
    // ======================================
    public OrderLineDTO() {
    }

    public OrderLineDTO(final int quantity, final double unitCost) {
        _quantity = quantity;
        _unitCost = unitCost;
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

    public String getItemId() {
        return _itemId;
    }

    public void setItemId(final String itemId) {
        _itemId = itemId;
    }

    public String getItemName() {
        return _itemName;
    }

    public void setItemName(final String itemName) {
        _itemName = itemName;
    }

    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("\nOrderLine {");
        buf.append("\n\tQuantity=").append(_quantity);
        buf.append("\n\tUnit Cost=").append(_unitCost);
        buf.append("\n\tItem Id=").append(_itemId);
        buf.append("\n\tItem Name=").append(_itemName);
        buf.append('}');
        return buf.toString();
    }
}
