package com.myswap.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id_picture")
@JsonAutoDetect
public abstract class Picture implements Serializable{
	/**
	 * Id de la picture. Clé primaire dans la table Picture. 
	 */
	@Id
	@GeneratedValue
	@Column(name = "id_picture")
	private Long idPicture;
	public void setIdPicture(Long i) {idPicture = i;}
	public Long getIdPicture() {return idPicture;}

	/**
	 * Nom de la photo. 
	 */
	@Column
	String name;
	public void setName(String n) {name= n;}
	public String getName() {return name;}


	/**
	 * lien de la photo. 
	 */
	@Column
	String path;
	public void setPath(String n) {path= n;}
	public String getPath() {return path;}


}