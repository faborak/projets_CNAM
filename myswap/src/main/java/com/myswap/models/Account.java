package com.myswap.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	 * Email verifi� par le user ou non.
	 */
	@Column
    private boolean emailchecked;
    public void setEmailChecked(boolean ec) {emailchecked = ec;}
    public boolean getEmailChecked() {return emailchecked;}
    
	/**
	 * Telephone verifi� par le user ou non.
	 */
	@Column
    private boolean phonechecked;
    public void setPhoneChecked(boolean pc) {phonechecked = pc;}
    public boolean getPhoneChecked() {return phonechecked;}
	
	/**
	 * user auquel est rattach� l'account.
	 */
    @OneToOne (cascade=CascadeType.PERSIST)
	@JoinColumn (name="id_user")
    @JsonBackReference
	private User user;
	public void setUser(User u) {user = u;}
	public User getUser() {return user;}
	
	public  Account() {} 
}