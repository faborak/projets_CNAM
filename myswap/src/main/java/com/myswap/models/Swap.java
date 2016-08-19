package com.myswap.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIgnoreProperties ({"hibernateLazyInitializer", "handler", "fieldHandler"}) 
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id_deal")
public class Swap {

	/**
	 * Id du Swap. Clé primaire dans la table Deal. 
	 */
	@Id
	@GeneratedValue
	@Column(name = "id_deal")
	private Long idDeal;
	public void setIdDeal(Long i) {idDeal = i;}
	public Long getIdDeal() {return idDeal;}
		
	/** Propriété du Swap.
	 * 
	 */
	@Column(name = "date_reception_initiator")
	private Date dateReceptionInitiator;
	public void setDateReceptionInitiator(Date d) {dateReceptionInitiator = d;}
	public Date getDateReceptionInitiator() {return dateReceptionInitiator;}
	
	/** Propriété du Swap.
	 * 
	 */
	@Column(name = "date_reception_proposed")
	private Date dateReceptionProposed;
	public void setDateReceptionProposed(Date d) {dateReceptionProposed = d;}
	public Date getDateReceptionProposed() {return dateReceptionProposed;}
	
	/** 
	 * Propriété du Swap.
	 */
	@Column(name = "date_envoi_initiator")
	private Date dateEnvoiInitiator;
	public void setDateEnvoiInitiator(Date d) {dateEnvoiInitiator = d;}
	public Date getDateEnvoiInitiator() {return dateEnvoiInitiator;}
	
	/** 
	 * Propriété du Swap.
	 */
	@Column(name = "date_envoi_proposed")
	private Date dateEnvoiProposed;
	public void setDateEnvoiProposed(Date d) {dateEnvoiProposed = d;}
	public Date getDateEnvoiProposed() {return dateEnvoiProposed;}
	

	/** 
	 * SwapObjects have been receipted by Initiator.
	 */
	@Column(name = "date_final_initiator")
	private Date dateFinalInitiator;
	public void setDateFinalInitiator(Date d) {dateFinalInitiator = d;}
	public Date getDateFinalInitiator() {return dateFinalInitiator;}
	
	/** 
	 * SwapObjects have been receipted by proposed.
	 */
	@Column(name = "date_final_proposed")
	private Date dateFinalProposed;
	public void setDateFinalProposed(Date d) {dateFinalProposed = d;}
	public Date getDateFinalProposed() {return dateFinalProposed;}
	
	/**
	 * Deal auquel est rattaché l'account.
	 */
    @OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn (name="id_deal")
    @JsonManagedReference
	private Deal deal;
	public void setDeal(Deal u) {deal = u;}
	public Deal getDeal() {return deal;}
	
	public Swap() {
	}
}