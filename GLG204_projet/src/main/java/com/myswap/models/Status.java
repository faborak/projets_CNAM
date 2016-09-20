package com.myswap.models;

import javax.persistence.*;

/**
 * Status of a Deal.
 */
@Entity
public class Status {

	@Id
	private String code;
    public void setCode(String e) {code = e;}
    public String getCode() {return code;}
}