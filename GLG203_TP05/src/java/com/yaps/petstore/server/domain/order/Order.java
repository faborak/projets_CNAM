package com.yaps.petstore.server.domain.order;

import com.yaps.petstore.server.domain.DomainObject;
import com.yaps.petstore.server.domain.customer.Customer;
import com.yaps.petstore.common.exception.CheckException;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * An order represents the items that a customer buys. This order has several
 * order items and is relevant for one customer. The order has address information
 * like the street, the city, the country... This is because a customer can order
 * a pet and wants it delivered at another adress. By default, the order address
 * is the same that the customer's one but it can be changed.
 *
 * @see Customer
 */
public final class Order extends DomainObject implements Serializable {

    // ======================================
    // =             Attributes             =
    // ======================================
    private Date _orderDate;
    private String _firstname;
    private String _lastname;
    private String _street1;
    private String _street2;
    private String _city;
    private String _state;
    private String _zipcode;
    private String _country;
    private String _creditCardNumber;
    private String _creditCardType;
    private String _creditCardExpiryDate;
    private Customer _customer;
    private Collection _orderLines;

    // ======================================
    // =            Constructors            =
    // ======================================
    public Order() {
    }

    public Order(final String id) {
        _id = id;
    }

    public Order(final String id, final Date orderDate, final String firstname, final String lastname, final String street1, final String city, final String zipcode, final String country, final Customer customer) {
        _id = id;
        _orderDate = orderDate;
        _firstname = firstname;
        _lastname = lastname;
        _street1 = street1;
        _city = city;
        _zipcode = zipcode;
        _country = country;
        _customer = customer;
    }

    public Order(final String firstname, final String lastname, final String street1, final String city, final String zipcode, final String country, final Customer customer) {
        this(null, new Date(), firstname, lastname, street1, city, zipcode, country, customer);
    }

    // ======================================
    // =           Business methods         =
    // ======================================
    public void checkData() throws CheckException {
        if (_firstname == null || "".equals(_firstname))
            throw new CheckException("Invalid first name");
        if (_lastname == null || "".equals(_lastname))
            throw new CheckException("Invalid last name");
        if (_city == null || "".equals(_city))
            throw new CheckException("Invalid city");
        if (_country == null || "".equals(_country))
            throw new CheckException("Invalid country");
        if (_street1 == null || "".equals(_street1))
            throw new CheckException("Invalid street");
        if (_zipcode == null || "".equals(_zipcode))
            throw new CheckException("Invalid zipcode");
        if (_customer == null)
            throw new CheckException("Invalid customer");
    }

    // ======================================
    // =         Getters and Setters        =
    // ======================================
    public Date getOrderDate() {
        return _orderDate;
    }

    public String getFirstname() {
        return _firstname;
    }

    public void setFirstname(final String firstname) {
        _firstname = firstname;
    }

    public String getLastname() {
        return _lastname;
    }

    public void setLastname(final String lastname) {
        _lastname = lastname;
    }

    public String getStreet1() {
        return _street1;
    }

    public void setStreet1(final String street1) {
        _street1 = street1;
    }

    public String getStreet2() {
        return _street2;
    }

    public void setStreet2(final String street2) {
        _street2 = street2;
    }

    public String getCity() {
        return _city;
    }

    public void setCity(final String city) {
        _city = city;
    }

    public String getState() {
        return _state;
    }

    public void setState(final String state) {
        _state = state;
    }

    public String getZipcode() {
        return _zipcode;
    }

    public void setZipcode(final String zipcode) {
        _zipcode = zipcode;
    }

    public String getCountry() {
        return _country;
    }

    public void setCountry(final String country) {
        _country = country;
    }

    public String getCreditCardNumber() {
        return _creditCardNumber;
    }

    public void setCreditCardNumber(final String creditCardNumber) {
        _creditCardNumber = creditCardNumber;
    }

    public String getCreditCardType() {
        return _creditCardType;
    }

    public void setCreditCardType(final String creditCardType) {
        _creditCardType = creditCardType;
    }

    public String getCreditCardExpiryDate() {
        return _creditCardExpiryDate;
    }

    public void setCreditCardExpiryDate(final String creditCardExpiryDate) {
        _creditCardExpiryDate = creditCardExpiryDate;
    }

    public Customer getCustomer() {
        return _customer;
    }

    public void setCustomer(final Customer customer) {
        _customer = customer;
    }

    public Collection getOrderLines() {
        return _orderLines;
    }

    public void setOrderLines(final Collection orderLines) {
        _orderLines = orderLines;
    }

    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("\nOrder{");
        buf.append("\n\tId=").append(_id);
        buf.append("\n\tOrder Date=").append(_orderDate);
        buf.append("\n\tFirstname=").append(_firstname);
        buf.append("\n\tLastname=").append(_lastname);
        buf.append("\n\tStreet1=").append(_street1);
        buf.append("\n\tStreet2=").append(_street2);
        buf.append("\n\tCity=").append(_city);
        buf.append("\n\tState=").append(_state);
        buf.append("\n\tZipcode=").append(_zipcode);
        buf.append("\n\tCountry=").append(_country);
        buf.append("\n\tCredit Card Number=").append(_creditCardNumber);
        buf.append("\n\tCredit Card Type=").append(_creditCardType);
        buf.append("\n\tCredit Card Expiry Date=").append(_creditCardExpiryDate);
        buf.append("\n\tOrder Lines=").append(_orderLines);
        buf.append("\n\tCustomer Id=").append(_customer.getId());
        buf.append("\n}");
        return buf.toString();
    }
}
