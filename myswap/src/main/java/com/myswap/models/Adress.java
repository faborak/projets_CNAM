package com.myswap.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Adress {

    @Id
	@Column(name = "id_user")
	private Long id_user;
	public void setId(Long i) {id_user = i;}
	public Long getId() {return id_user;}
	
	/**
	 * Street du user.
	 */
	@Column
    private String street;
    public void setStreet(String n) {street = n;}
    public String getStreet() {return street;}
	
	/**
	 * State du user.
	 */
	@Column
    private String state;
    public void setState(String n) {state = n;}
    public String getState() {return state;}
	
	/**
	 * Zipcode du user.
	 */
	@Column
    private String zipcode;
    public void setZipcode(String n) {zipcode = n;}
    public String getZipcode() {return zipcode;}
	
	/**
	 * City du user.
	 */
	@Column
    private String city;
    public void setCity(String n) {city = n;}
    public String getCity() {return city;}
	
	/**
	 * user auquel est rattachée l'adresse.
	 */
    @OneToOne (cascade=CascadeType.PERSIST)
	@JoinColumn (name="id_user")
	private User user;
	public void setUser(User u) {user = u;}
	public User getUser() {return user;}
	
	public  Adress() {} 
}