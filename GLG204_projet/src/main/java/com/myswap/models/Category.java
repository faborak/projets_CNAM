package com.myswap.models;

import javax.persistence.*;

/**
 * 
 * @author myswap
 * Category of Items in the MySwap Site.
 *
 */
@Entity
public class Category {

	@Id
	private String code;
    public void setCode(String e) {code = e;}
    public String getCode() {return code;}
}