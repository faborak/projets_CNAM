package com.yaps.petstore.common.dto;

import java.util.Collection;
import java.util.Date;

import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.server.domain.customer.Customer;
import com.yaps.petstore.server.domain.orderline.OrderLine;



/**
 * This class follows the Data Transfert Object design pattern and for that implements the
 * markup interface DataTransfertObject. It is a client view of an Order. This class only
 * transfers data from a distant service to a client.
 */
public class OrderDTO implements DataTransfertObject {

	// ======================================
	// = Attributes =
	// ======================================
	private String _id;
	private Date _date;
	private String _firstname;
	private String _lastname;
	private String _street1;
	private String _street2;
	private String _city;
	private String _state;
	private String _zip;
	private String _country;
	private String _creditCardExpiryDate;
	private String _creditCardNumber;
	private String _creditCardType;
	private CustomerDTO _customer;
	public Collection<OrderLineDTO> _orderLines;

	// ======================================
	// = Constructors =
	// ======================================
	public OrderDTO() {
	}

//	public OrderDTO(String id) {
//		this._id = id;
//	}

//	public OrderDTO(String id, Date date, String firstname, String lastname,
//			String street1, String city, String zipcode, String country,
//			CustomerDTO customer) {
//		this._id = id;
//		this._date = date;
//		this._firstname = firstname;
//		this._lastname = lastname;
//		this._street1 = street1;
//		this._city = city;
//		this._zipcode = zipcode;
//		this._country = country;
//		this._customer = (CustomerDTO) customer;
//	}

//	public OrderDTO(String id, String firstname, String lastname, String street1,
//			String city, String country, CustomerDTO customer) {
//		this._id = id;
//		this._firstname = firstname;
//		this._lastname = lastname;
//		this._street1 = street1;
//		this._city = city;
//		this._country = country;
//		this._customer = (CustomerDTO) customer;
//	}

//	public OrderDTO(String id, Date orderdate, String firstname, String lastname,
//			String street1, String street2, String city, String state,
//			String zipcode, String country, String creditcardnumber,
//			String creditcardtype, String creditcardexpiredate, CustomerDTO customer) {
//		this._id = id;
//		this._date = orderdate;
//		this._firstname = firstname;
//		this._lastname = lastname;
//		this._street1 = street1;
//		this._street2 = street2;
//		this._city = city;
//		this._state = state;
//		this._zipcode = zipcode;
//		this._country = country;
//		this._creditCardNumber = creditcardnumber;
//		this._creditCardExpiryDate = creditcardexpiredate;
//		this._creditCardType = creditcardtype;
//		this._customer = (CustomerDTO) customer;
//	}

	public OrderDTO(String firstname, String lastname, String street1,
			String city, String zip, String country) {
		this._firstname = firstname;
		this._lastname = lastname;
		this._street1 = street1;
		this._city = city;
		this._zip = zip;
		this._country = country;
	}
	
	// ======================================
	// = 
	// ======================================
	public void setCustomerId(String id) {
		this._customer.setId(id);
	}
	
	public String getCustomerId() {
		return this._customer.getId();
	}
	
	// ======================================
	// = Getters and Setters =
	// ======================================
    public void setId(final  String id){
    	this._id = id;
    }
    
	public String getId() {
		return _id;
	}
	
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
		return _zip;
	}

	public void setZipcode(String zip) {
		this._zip = zip;
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

	public void setCustomer(CustomerDTO customer) {
		this._customer = customer;
	}

	public CustomerDTO getCustomer() {
		return _customer;
	}

	public Collection<OrderLineDTO> getOrderLines() {
		return _orderLines;
	}

	public void setOrderLines(Collection<OrderLineDTO> orderLines) {
		this._orderLines = orderLines;
	}

	public void setOrderDate(Date orderDate) {
		this._date = orderDate;
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
		buf.append("\n\t\tZip=").append(_zip);
		buf.append("\n\t\tCreditcardnumber=").append(_creditCardNumber);
		buf.append("\n\t\tCreditCardType=").append(_creditCardType);
		buf.append("\n\t\tCreditCardExpiryDate=").append(_creditCardExpiryDate);
		buf.append("\n\t}");
		return buf.toString();
	}



}
