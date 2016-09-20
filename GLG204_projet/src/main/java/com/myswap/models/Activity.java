package com.myswap.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * This class represents a Activity of a User of the MySwap company.
 * This is login informations for a user.
 */
@Entity
public class Activity {

    @Id
	@Column(name = "id_user")
	private Long id_user;
	public void setId(Long i) {id_user = i;}
	public Long getId() {return id_user;}
	
	/**
	 * derniere activite du user.
	 */
	@Column(name = "date")
    private Date dateDerniereActivite;
    public void setDateDerniereActivite(Date n) {dateDerniereActivite = n;}
    public Date getDateDerniereActivite() {return dateDerniereActivite;}
	
	/**
	 * Token du user.
	 */
	@Column
    private String token;
    public void setToken(String n) {token = n;}
    public String getToken() {return token;}
	
	/**
	 * user auquel est rattaché l'account.
	 */
    @OneToOne (cascade=CascadeType.PERSIST)
	@JoinColumn (name="id_user")
    @JsonManagedReference
	private User user;
	public void setUser(User u) {user = u;}
	public User getUser() {return user;}
	
	public  Activity() {} 
}