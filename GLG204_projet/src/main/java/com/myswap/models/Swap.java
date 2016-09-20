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
 * An accepted deal begins a Swap for the MySwap Compagny.
 * A Swap has dates for operations to keep traces.
 * This object may evolve for future needs of operations.
 */
@Entity
public class Swap {

	@Id
	@Column(name = "id_deal")
	private Long id_deal;
	public void setId(Long i) {id_deal = i;}
	public Long getId() {return id_deal;}
		
	/** 
	 * Reception Date from mySwap for the objects of initiator.
	 */
	@Column(name = "date_reception_initiator")
	private Date dateReceptionInitiator;
	public void setDateReceptionInitiator(Date d) {dateReceptionInitiator = d;}
	public Date getDateReceptionInitiator() {return dateReceptionInitiator;}
	
	/** 
	 * Reception Date from mySwap for the objects of proposed.
	 */
	@Column(name = "date_reception_proposed")
	private Date dateReceptionProposed;
	public void setDateReceptionProposed(Date d) {dateReceptionProposed = d;}
	public Date getDateReceptionProposed() {return dateReceptionProposed;}
	
	/** 
	 * Send Date for mySwap for the objects of initiator.
	 */
	@Column(name = "date_envoi_initiator")
	private Date dateEnvoiInitiator;
	public void setDateEnvoiInitiator(Date d) {dateEnvoiInitiator = d;}
	public Date getDateEnvoiInitiator() {return dateEnvoiInitiator;}
	
	/** 
	 * Send Date for mySwap for the objects of proposed.
	 */
	@Column(name = "date_envoi_proposed")
	private Date dateEnvoiProposed;
	public void setDateEnvoiProposed(Date d) {dateEnvoiProposed = d;}
	public Date getDateEnvoiProposed() {return dateEnvoiProposed;}
	

	/** 
	 * Date where objects have been receipted by initiator.
	 */
	@Column(name = "date_final_initiator")
	private Date dateFinalInitiator;
	public void setDateFinalInitiator(Date d) {dateFinalInitiator = d;}
	public Date getDateFinalInitiator() {return dateFinalInitiator;}
	
	/** 
	 * Date where objects have been receipted by proposed.
	 */
	@Column(name = "date_final_proposed")
	private Date dateFinalProposed;
	public void setDateFinalProposed(Date d) {dateFinalProposed = d;}
	public Date getDateFinalProposed() {return dateFinalProposed;}
	
	/**
	 * Deal wich the swap is linked.
	 */
    @OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn (name="id_deal")
    @JsonManagedReference
	private Deal deal;
	public void setDeal(Deal d) {deal = d;}
	public Deal getDeal() {return deal;}
	
	public Swap() {}
}