package com.myswap.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIgnoreProperties ({"hibernateLazyInitializer", "handler", "fieldHandler"}) 
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id_deal")
public class Deal {

	/**
	 * Id du Deal. Cl� primaire dans la table Deal. 
	 */
	@Id
	@GeneratedValue
	@Column(name = "id_deal")
	private Long idDeal;
	public void setIdDeal(Long i) {idDeal = i;}
	public Long getIdDeal() {return idDeal;}
	
	/**
	 * Propri�t� du Deal.
	 * TODO : définir les status possibles du deal 
	 */
	@Column
	private Integer status;
	public void setStatus(Integer n) {status = n;}
	public Integer getStatus() {return status;}
	
	/** Propri�t� du Deal.
	 * 
	 */
	@Column(name = "date_creation")
	private Date dateCreation;
	public void setDateCreation(Date d) {dateCreation = d;}
	public Date getDateCreation() {return dateCreation;}
	
	/** Propri�t� du Deal.
	 * 
	 */
	@Column(name = "date_modification")
	private Date dateModification;
	public void setDateModification(Date d) {dateModification = d;}
	public Date getDateModification() {return dateModification;}

	/**
	 * user Initiateur du Deal.
	 */
    @ManyToOne (cascade=CascadeType.PERSIST)
	@JoinColumn (name="id_first_user")
    @JsonManagedReference
	private User initiator;
	public void setInitiator(User u) {initiator = u;}
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	public User getInitiator() {return initiator;}
	
	/**
	 * user propos� pour le Deal.
	 */
    @ManyToOne (cascade=CascadeType.PERSIST)
	@JoinColumn (name="id_second_user")
    @JsonManagedReference
	private User proposed;
	public void setProposed(User u) {proposed = u;}
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	public User getProposed() {return proposed;}
	
	/**
	 *  Lien avec la table deals : pas de propri�t� port�e par la table DealSwap,
	 *  donc pas d'objet Java correspondant � la table
	 *  la responsabilit� est port�e par SwapObject, qui porte l'inverseJoinColomn
	 */
	@ManyToMany(mappedBy = "deals")
	@JsonBackReference
	private Set<SwapObject> swapObjects;
	public void addSwapObjects(SwapObject so) {so.addDeal(this); swapObjects.add(so);}
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	public Set<SwapObject> getSwapObjects() {return swapObjects;}
	
	public Deal() {
	}
}