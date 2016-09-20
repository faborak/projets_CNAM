package com.myswap.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * 
 * @author myswap
 *  Picture representing a user.
 */
@Entity
@PrimaryKeyJoinColumn(name="id_picture")
public class UserPicture extends Picture {
	

	/**
	 * pour la sauvegarde en cascade, on utilise JPA (javax.peristance) et non pas hibernate
	 * 
	 */
    @ManyToOne (cascade=CascadeType.PERSIST)
	@JoinColumn (name="id_user")
    @JsonBackReference
	private User owner;
	public void setOwner(User u) {owner = u;}
	public User getOwner() {return owner;}	


}