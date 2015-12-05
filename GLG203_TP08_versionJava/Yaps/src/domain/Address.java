package com.yaps.petstore.server.domain;

import com.yaps.petstore.common.exception.CheckException;


/**
 * This class encapsulates all the data for an address.
 *
 * @see com.yaps.petstore.server.domain.customer.Customer
 * @see com.yaps.petstore.server.domain.order.Order
 */
public final class Address extends DomainObject {

    // ======================================
    // =             Attributes             =
    // ======================================
    private String _street1;
    private String _street2;
    private String _city;
    private String _state;
    private String _zipcode;
    private String _country;

    // ======================================
    // =           Business methods         =
    // ======================================
    public void checkData() throws CheckException {
        if (_city == null || "".equals(_city))
            throw new CheckException("Invalid city");
        if (_country == null || "".equals(_country))
            throw new CheckException("Invalid country");
        if (_street1 == null || "".equals(_street1))
            throw new CheckException("Invalid street");
        if (_zipcode == null || "".equals(_zipcode))
            throw new CheckException("Invalid zipcode");
    }

    // ======================================
    // =         Getters and Setters        =
    // ======================================
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
}
