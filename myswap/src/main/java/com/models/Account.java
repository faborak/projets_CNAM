package com.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Account {

    @Id
	@Column(name = "id_user")
	private Long id_user;
	public void setId(Long i) {id_user = i;}
	public Long getId() {return id_user;}
	
	/**
	 * PhoneNumber du user.
	 */
	@Column(name = "phone_number")
    private String phoneNumber;
    public void setPhoneNumber(String n) {phoneNumber = n;}
    public String getPhoneNumber() {return phoneNumber;}
	
	/**
	 * Email du user.
	 */
	@Column(name = "email")
    private String email;
    public void setEmail(String n) {email = n;}
    public String getEmail() {return email;}
	
	/**
	 * user auquel est rattaché l'account.
	 */
    @OneToOne (cascade=CascadeType.PERSIST)
	@JoinColumn (name="id_user")
	private User user;
	public void setUser(User u) {user = u;}
	public User getUser() {return user;}
	
	public  Account() {} 
}