package com.myswap.models;

import javax.persistence.*;

@Entity
public class Category {

	@Id
	private String code;
    public void setCode(String e) {code = e;}
    public String getCode() {return code;}
}