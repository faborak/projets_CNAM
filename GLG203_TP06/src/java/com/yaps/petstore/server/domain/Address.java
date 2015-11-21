package com.yaps.petstore.server.domain;

import java.util.Date;

import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.server.domain.customer.Customer;

/**
 * This class encapsulates all the data for an adress.
 * 
 * @see com.yaps.petstore.server.domain.customer.Customer
 * @see com.yaps.petstore.server.domain.order.Order
 */
public class Address extends DomainObject {

    // ======================================
    // =             Attributes             =
    // ======================================
	String _Street1;
	String _Street2;
	String _City;
	String _State;
	String _Zipcode;
	String _Country;
	
    // ======================================
    // =            Constructors            =
    // ======================================
    public Address() {
    }
    
    public Address(final String id) {
        setId(id);
    }
    
    public Address(final String id, final String street1, final String street2, final String city, final String zipcode, final String country) {
        setId(id);
        setStreet1(street1);
        setStreet2(street2);
        setCity(city);
        setZipcode(zipcode);
        setCountry(country);
    }

    // ======================================
    // =         Getters and Setters        =
    // ======================================
	public String getStreet1() {
		return _Street1;
	}

	public void setStreet1(final String street1) {
		_Street1 = street1;
	}

	public String getStreet2() {
		return _Street2;
	}

	public void setStreet2(final String street2) {
		_Street2 = street2;
	}

	public String getCity() {
		return _City;
	}

	public void setCity(final String city) {
		_City = city;
	}

	public String getState() {
		return _State;
	}

	public void setState(final String state) {
		_State = state;
	}

	public String getZipcode() {
		return _Zipcode;
	}

	public void setZipcode(final String zipcode) {
		_Zipcode = zipcode;
	}

	public String getCountry() {
		return _Country;
	}

	public void setCountry(final String country) {
		_Country = country;
	}

	public void checkData() throws CheckException {
        if (getStreet1() == null || "".equals(getStreet1()))
            throw new CheckException("Invalid adress street 1");
        if (getStreet2() == null || "".equals(getStreet2()))
            throw new CheckException("Invalid adress street 2");
        if (getCity() == null || "".equals(getCity()))
            throw new CheckException("Invalid adress city");
        if (getState() == null || "".equals(getState()))
            throw new CheckException("Invalid adress state");
	}

}
