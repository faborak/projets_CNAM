package com.yaps.petstore.common.dto;

/**
 * This class follows the Data Transfert Object design pattern and for that implements the
 * markup interface DataTransfertObject. It is a client view of an Item. This class only
 * transfers data from a distant service to a client.
 */
public final class ItemDTO implements DataTransfertObject {

    // ======================================
    // =             Attributes             =
    // ======================================
    private String _id;
    private String _name;
    private double _unitCost;
    private String _productId;
    private String _productName;

    // ======================================
    // =            Constructors            =
    // ======================================
    public ItemDTO() {
    }

    public ItemDTO(final String id, final String name, final double unitCost) {
        _id = id;
        _name = name;
        _unitCost = unitCost;
    }

    // ======================================
    // =         Getters and Setters        =
    // ======================================
    public String getId() {
        return _id;
    }

    public void setId(final String id) {
        _id = id;
    }

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

    public String getProductId() {
        return _productId;
    }

    public void setProductId(final String productId) {
        _productId = productId;
    }

    public String getProductName() {
        return _productName;
    }

    public void setProductName(final String productName) {
        _productName = productName;
    }

    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("\nItem {");
        buf.append("\n\tId=").append(_id);
        buf.append("\n\tName=").append(_name);
        buf.append("\n\tUnit Cost=").append(_unitCost);
        buf.append("\n\tProduct Id=").append(_productId);
        buf.append("\n\tProduct Name=").append(_productName);
        buf.append("\n}");
        return buf.toString();
    }
}
