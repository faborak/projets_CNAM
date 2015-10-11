package com.yaps.petstore;

import java.util.ArrayList;
import java.util.List;

public class Customer {

	private String _id;
	private String _firstname;
	private String _lastname;
	private String _telephone;
	private String _street1;
	private String _street2;
	private String _city;
	private String _state;
	private String _zipcode;
	private String _country;
	private String _mail;

	// public static List<Customer> ListCustomers = new ArrayList<Customer>();

	public String getId() {
		return _id;
	}

	public void setId(String id) {
		_id = id;
	}

	public String getFirstname() {
		return _firstname;
	}

	public void setFirstname(String firstname) {
		_firstname = firstname;
	}

	public String getLastname() {
		return _lastname;
	}

	public void setLastname(String lastname) {
		_lastname = lastname;
	}

	public String getTelephone() {
		return _telephone;
	}

	public void setTelephone(String telephone) {
		_telephone = telephone;
	}

	public String getStreet1() {
		return _street1;
	}

	public void setStreet1(String street1) {
		_street1 = street1;
	}

	public String getStreet2() {
		return _street2;
	}

	public void setStreet2(String street2) {
		_street2 = street2;
	}

	public String getCity() {
		return _city;
	}

	public void setCity(String city) {
		_city = city;
	}

	public String getState() {
		return _state;
	}

	public void setState(String state) {
		_state = state;
	}

	public String getZipcode() {
		return _zipcode;
	}

	public void setZipcode(String zipcode) {
		_zipcode = zipcode;
	}

	public String getCountry() {
		return _country;
	}

	public void setCountry(String country) {
		_country = country;
	}

	public String getMail() {
		return _mail;
	}

	public void setMail(String mail) {
		_mail = mail;
	}

	/**
	 * 
	 * @param id
	 * @param firstname
	 * @param lastname
	 */
	public Customer(String id, String firstname, String lastname) {
		this.setId(id);
		this.setFirstname(firstname);
		this.setLastname(lastname);
	}

	/**
	 * Constructeur par défaut
	 */
	public Customer(){}
	
	/**
	 * 
	 * @param id
	 * @param firstname
	 * @param lastname
	 * @param telephone
	 * @param street1
	 * @param street2
	 * @param city
	 * @param state
	 * @param zipcode
	 * @param country
	 * @param mail
	 */
	public Customer(String id, String firstname, String lastname, String telephone, String street1, String street2,
			String city, String state, String zipcode, String country, String mail) {
		super();
		_id = id;
		_firstname = firstname;
		_lastname = lastname;
		_telephone = telephone;
		_street1 = street1;
		_street2 = street2;
		_city = city;
		_state = state;
		_zipcode = zipcode;
		_country = country;
		_mail = mail;
	}

	public Customer(String id) {
		super();
		_id = id;
	}

	public boolean checkId(String id) {
		if (this._id == id) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkData() throws CustomerCheckException{
		return _id != null && !_id.equals("") && _firstname != null && !_firstname.equals("") && _lastname != null
				&& !_lastname.equals("");
	}

	public String getCheckDataError() {
		String message = null;
		if (_id == null || _id.equals("")) {
			message = "Invalid id";
		} else if (_firstname == null || _firstname.equals("")) {
			message = "Invalid first name";
		} else if (_lastname == null || _lastname.equals("")) {
			message = "Invalid last name";
		}
		return message;
	}

	public boolean checkMail() {
		boolean mailok = false;
		for (int i = 0; i < this.getMail().length(); i++) {
			if (this.getMail().charAt(i) == '@') {
				mailok = true;
			}
		}
		if (this.getMail().length() < 7 || this.getMail().length() > 38) {
			mailok = false;
		}
		if (this.getMail().subSequence(this.getMail().length() - 3, this.getMail().length()).equals(".us")) {
			mailok = false;
		}
		if (this.getMail().substring(this.getMail().length() - 4, this.getMail().length()).equals(".com")) {
			mailok = false;
		}
		return mailok;
	}

	// public static Customer find(String id) {
	// Customer test = null;
	// for (int i = 0; i < ListCustomers.size() ; i++) {
	// test = ListCustomers.get(i);
	// if (test.getId().equals(id)) {
	// return test;
	// }
	// }
	// return null;
	// }
	//
	// public static boolean insert(Customer customer) {
	// if (find(customer.getId()) == null) {
	// ListCustomers.add(customer);
	// return true;
	// } else {
	// return false;
	// }
	// }
	//
	// public static boolean remove(String sid) {
	// Customer test = null;
	// for (int i = 0; i < ListCustomers.size() ; i++) {
	// test = ListCustomers.get(i);
	// if (test.getId().equals(sid)) {
	// ListCustomers.remove(i);
	// return true;
	// }
	// }
	// return false;
	// }

}
