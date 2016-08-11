package com.myswap.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class UserPicture extends Picture {

/**
	 * pour la sauvegarde en cascade, on utilise JPA (javax.peristance) et non pas hibernate
	 * Toutes les cascades sont sous la responsabilitÃ© de l'objet Ã©quipe
	 * 
*/
    @ManyToOne (cascade=CascadeType.PERSIST)
	@JoinColumn (name="id_user")
    @JsonManagedReference
	private User owner;
	public void setOwner(User u) {owner = u;}
	public User getOwner() {return owner;}	


}