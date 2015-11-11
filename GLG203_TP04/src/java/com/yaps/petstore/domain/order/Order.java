package com.yaps.petstore.domain.order;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import com.yaps.petstore.domain.DomainObject;
import com.yaps.petstore.domain.customer.Customer;
import com.yaps.petstore.domain.orderline.OrderLine;
import com.yaps.petstore.exception.CheckException;

/**
 * This class represents a order for the YAPS company.
 */
public class Order extends DomainObject implements Serializable {

	// ======================================
	// = Attributes =
	// ======================================
	private Date _date;
	private String _firstname;
	private String _lastname;
	private String _street1;
	private String _street2;
	private String _city;
	private String _state;
	private String _zipcode;
	private String _country;
	private String _creditCardExpiryDate;
	private String _creditCardNumber;
	private String _creditCardType;
	private Customer _customer;
	public Collection<OrderLine> _orderLines;

	// ======================================
	// = Constructors =
	// ======================================
	public Order() {
	}

	public Order(String id) {
		this._id = id;
	}
	
	public Order(String id, Date date, String firstname, String lastname,
			String street1, String city, String zipcode, String country,
			Object customer) {
		this._id = id;
		this._date = date;
		this._firstname = firstname;
		this._lastname = lastname;
		this._street1 = street1;
		this._city = city;
		this._zipcode = zipcode;
		this._country = country;
		this._customer = (Customer) customer;
	}

	public Order(String id, String firstname, String lastname, String street1,
			String city, String country, Object customer) {
		this._id = id;
		this._firstname = firstname;
		this._lastname = lastname;
		this._street1 = street1;
		this._city = city;
		this._country = country;
		this._customer = (Customer) customer;
	}

	public Order(String id, Date orderdate, String firstname, String lastname,
			String street1, String street2, String city,
			String state, String zipcode, String country,
			String creditcardnumber, String creditcardtype,
			String creditcardexpiredate, Object customer) {
		this._id = id;
		this._firstname = firstname;
		this._lastname = lastname;
		this._street1 = street1;
		this._street2 = street2;
		this._city = city;
		this._state = state;
		this._zipcode = zipcode;
		this._country = country;
		this._creditCardNumber = creditcardnumber;
		this._creditCardExpiryDate =creditcardexpiredate;
		this._creditCardType=creditcardtype;
		this._customer = (Customer) customer;
	}

	// ======================================
	// = Business methods =
	// ======================================
	/**
	 * This method checks the integrity of the object data.
	 * 
	 * @throws CheckException
	 *             if data is invalid
	 */
	public void checkData() throws CheckException {
		if (_firstname == null || "".equals(_firstname))
			throw new CheckException("Invalid order first name");
		if (_lastname == null || "".equals(_lastname))
			throw new CheckException("Invalid order last name");
		if (_street1 == null || "".equals(_street1))
			throw new CheckException("Invalid order street1");
		if (_city == null || "".equals(_city))
			throw new CheckException("Invalid order city");
		if (_zipcode == null || "".equals(_zipcode))
			throw new CheckException("Invalid order zipcode");
		if (_customer == null)
			throw new CheckException("Invalid customer");
	}

    // ======================================
    // =         Getters and Setters        =
    // ======================================
	public String getFirstname() {
		return _firstname;
	}

	public void setFirstname(String firstname) {
		this._firstname = firstname;
	}

	public String getLastname() {
		return _lastname;
	}

	public void setLastname(String lastname) {
		this._lastname = lastname;
	}

	public String getStreet1() {
		return _street1;
	}

	public void setStreet1(String street1) {
		this._street1 = street1;
	}

	public String getStreet2() {
		return _street2;
	}

	public void setStreet2(String street2) {
		this._street2 = street2;
	}

	public String getCity() {
		return _city;
	}

	public void setCity(String city) {
		this._city = city;
	}

	public String getState() {
		return _state;
	}

	public void setState(String state) {
		this._state = state;
	}

	public String getZipcode() {
		return _zipcode;
	}

	public void setZipcode(String zipcode) {
		this._zipcode = zipcode;
	}

	public String getCountry() {
		return _country;
	}

	public void setCountry(String country) {
		this._country = country;
	}

	public String getCreditCardNumber() {
		return _creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this._creditCardNumber = creditCardNumber;
	}

	public String getCreditCardType() {
		return _creditCardType;
	}

	public void setCreditCardType(String creditCardType) {
		this._creditCardType = creditCardType;
	}

	public String getCreditCardExpiryDate() {
		return _creditCardExpiryDate;
	}

	public void setCreditCardExpiryDate(String creditCardExpiryDate) {
		this._creditCardExpiryDate = creditCardExpiryDate;
	}
	
	public void setCustomer(Customer customer) {
		this._customer = customer;
	}

	public Customer getCustomer() {
		return _customer;
	}

	public Collection<OrderLine> getOrderLines() {
		return _orderLines;
	}

	public void setOrderLines(Collection<OrderLine> orderLines) {
		this._orderLines = orderLines;
	}

	public Date getOrderDate() {
		return _date;
	}

	public String toString() {
		final StringBuffer buf = new StringBuffer();
		buf.append("\n\tCustomer {");
		buf.append("\n\t\tId=").append(_id);
		buf.append("\n\t\tFirst Name=").append(_firstname);
		buf.append("\n\t\tLast Name=").append(_lastname);
		buf.append("\n\t\tStreet 1=").append(_street1);
		buf.append("\n\t\tStreet 2=").append(_street2);
		buf.append("\n\t\tCity=").append(_city);
		buf.append("\n\t\tState=").append(_state);
		buf.append("\n\t\tZipcode=").append(_zipcode);
		buf.append("\n\t\tCreditcardnumber=").append(_creditCardNumber);
		buf.append("\n\t\tCreditCardType=").append(_creditCardType);
		buf.append("\n\t\tCreditCardExpiryDate=").append(_creditCardExpiryDate);
		buf.append("\n\t}");
		return buf.toString();
	}

}
