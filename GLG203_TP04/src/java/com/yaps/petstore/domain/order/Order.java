package com.yaps.petstore.domain.order;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import com.yaps.petstore.domain.DomainObject;
import com.yaps.petstore.domain.customer.Customer;
import com.yaps.petstore.domain.orderline.OrderLine;
import com.yaps.petstore.exception.CheckException;

public class Order extends DomainObject implements Serializable {

	// ======================================
    // =             Attributes             =
    // ======================================
    private String id;
    private Date date;
	private String firstname;
    private String lastname;
    private String telephone;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String zipcode;
    private String country;
    private String CreditCardExpiryDate;
    private String CreditCardNumber;
    private String CreditCardType;
    private Customer customer;
    public Collection<OrderLine> orderLines;
    
    // ======================================
    // =            Constructors            =
    // ======================================
	public Order() {
	}
	
	public Order(String id, Date date, String firstname, String lastname, String street1, String city, String zip, String country,
			 Object customer) {
      this.id = id;
      this.date = date;
      this.firstname = firstname;
      this.lastname = lastname;
      this.street1 = street1;
      this.city = city;
      this.zipcode = zip;
      this.country = country;
      this.customer = (Customer) customer;
	}


	public Order(String id, String lastname, String street1, String city, String zip, String country,
			Object customer) {
	      this.id = id;
	      this.lastname = lastname;
	      this.street1 = street1;
	      this.city = city;
	      this.zipcode = zip;
	      this.country = country;
	      this.customer = (Customer) customer;
	}

	// ======================================
    // =           Business methods         =
    // ======================================
    /**
     * This method checks the integrity of the object data.
     *
     * @throws CheckException if data is invalid
     */
	public void checkData() throws CheckException{
		if (firstname == null || "".equals(firstname))
            throw new CheckException("Invalid order first name");
        if (lastname == null || "".equals(lastname))
            throw new CheckException("Invalid order last name");
        if (street1 == null || "".equals(street1))
            throw new CheckException("Invalid order street1");
        if (city == null || "".equals(city))
            throw new CheckException("Invalid order city");
        if (zipcode == null || "".equals(zipcode))
            throw new CheckException("Invalid order zipcode");
        if (customer == null)
            throw new CheckException("Invalid customer");
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCreditCardExpiryDate() {
		return CreditCardExpiryDate;
	}

	public void setCreditCardExpiryDate(String creditCardExpiryDate) {
		CreditCardExpiryDate = creditCardExpiryDate;
	}

	public String getCreditCardNumber() {
		return CreditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		CreditCardNumber = creditCardNumber;
	}

	public String getCreditCardType() {
		return CreditCardType;
	}

	public void setCreditCardType(String creditCardType) {
		CreditCardType = creditCardType;
	}

	public String getId() {
		return id;
	}

	public void setCustomer(Customer customer) {
	  this.customer = customer;	
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public Collection<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(Collection orderLines) {
       this.orderLines = orderLines;		
	}

	public Date getOrderDate() {
		return date;
	}

}
