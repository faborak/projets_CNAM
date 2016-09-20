package com.myswap.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * This class represents Infos of a user of the MySwap company.
 * It's complentary informations, for profile view only.
 */
@Entity
public class Infos {

    @Id
	@Column(name = "id_user")
	private Long id_user;
	public void setId(Long i) {id_user = i;}
	public Long getId() {return id_user;}
	
	/**
	* School du user.
	*/
	@Column(name = "school")
    private String school;
    public void setSchool(String n) {school = n;}
    public String getSchool() {return school;}
	
	/**
	* Job du user.
	*/
	@Column(name = "job")
    private String job;
    public void setJob(String n) {job = n;}
    public String getJob() {return job;}
	
	/**
	* About du user.
	*/
	@Column(name = "about")
    private String about;
    public void setAbout(String n) {about = n;}
    public String getAbout() {return about;}
	
	/**
	* user auquel sont rattachées les infos.
	*/
    @OneToOne (cascade=CascadeType.PERSIST)
	@JoinColumn (name="id_user")
    @JsonBackReference
	private User user;
	public void setUser(User u) {user = u;}
	public User getUser() {return user;}
	
	public  Infos() {} 
}