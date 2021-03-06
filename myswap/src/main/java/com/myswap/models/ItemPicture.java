package com.myswap.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@PrimaryKeyJoinColumn(name="id_picture")
public class ItemPicture extends Picture {

/**
	 * pour la sauvegarde en cascade, on utilise JPA (javax.peristance) et non pas hibernate
	 * 
*/
    @ManyToOne (cascade=CascadeType.PERSIST)
	@JoinColumn (name="id_swap_object")
    @JsonBackReference
	private SwapObject itemRepresented;
	public void setItemRepresented(SwapObject u) {itemRepresented = u;}
	public SwapObject getItemRepresented() {return itemRepresented;}	

}