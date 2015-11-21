package com.yaps.petstore.common.dto;

public class AddressDTO  implements DataTransfertObject {

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
    public AddressDTO() {
    }
	
    // ======================================
    // =         Getters and Setters        =
    // ======================================
	public String getStreet1() {
		return _Street1;
	}

	public void setStreet1(String street1) {
		_Street1 = street1;
	}

	public String getStreet2() {
		return _Street2;
	}

	public void setStreet2(String street2) {
		_Street2 = street2;
	}

	public String getCity() {
		return _City;
	}

	public void setCity(String city) {
		_City = city;
	}

	public String getState() {
		return _State;
	}

	public void setState(String state) {
		_State = state;
	}

	public String getZipcode() {
		return _Zipcode;
	}

	public void setZipcode(String zipcode) {
		_Zipcode = zipcode;
	}

	public String getCountry() {
		return _Country;
	}

	public void setCountry(String country) {
		_Country = country;
	}
	
    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("CustomerDTO{");
        buf.append(",street1=").append(getStreet1());
        buf.append(",street2=").append(getStreet2());
        buf.append(",city=").append(getCity());
        buf.append(",state=").append(getState());
        buf.append(",zipcode=").append(getZipcode());
        buf.append(",country=").append(getCountry());
        buf.append('}');
        return buf.toString();
    }
	
}
