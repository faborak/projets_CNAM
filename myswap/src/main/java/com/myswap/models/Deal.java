package com.myswap.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

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
	 * Id du Deal. Clé primaire dans la table Deal. 
	 */
	@Id
	@GeneratedValue
	@Column(name = "id_deal")
	private Long idDeal;
	public void setIdDeal(Long i) {idDeal = i;}
	public Long getIdDeal() {return idDeal;}
	
	/**
	 * Statut du Deal.
	 */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn (name="status")
	private Status status;
	public void setStatus(Status n) {status = n;}
	public Status getStatus() {return status;}
		
	/** Propriété du Deal.
	 * 
	 */
	@Column(name = "date_creation")
	private Date dateCreation;
	public void setDateCreation(Date d) {dateCreation = d;}
	public Date getDateCreation() {return dateCreation;}
	
	/** Propriété du Deal.
	 * 
	 */
	@Column(name = "date_modification")
	private Date dateModification;
	public void setDateModification(Date d) {dateModification = d;}
	public Date getDateModification() {return dateModification;}
	
	/** 
	 * Balance du Deal.
	 */
	@Transient
	private Float balance;
	public void setBalance(Float m) {balance = m;}
	public Float getBalance() {return balance;}
	public void calculBalance() {
		balance = new Float(0.0);
		for (SwapObject swapObject : swapObjects) {
			if (swapObject.getOwner().getId() == initiator.getId() /* && swapObject instanceOf Item)*/){
				Item item = new Item();
				item = (Item) swapObject;
				balance = balance + item.getCost();
			} else if (swapObject.getOwner().getId() == proposed.getId() /* && swapObject.instanceOf(Item) */){
				Item item = new Item();
				item = (Item) swapObject;
				balance = balance - item.getCost();
			} else {
			System.out.println("Exception a definir");
				//throws new BalanceException("Problème de calcul de balance");
		
			}
		}	
	}

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
	 * user proposé pour le Deal.
	 */
    @ManyToOne (cascade=CascadeType.PERSIST)
	@JoinColumn (name="id_second_user")
    @JsonManagedReference
	private User proposed;
	public void setProposed(User u) {proposed = u;}
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	public User getProposed() {return proposed;}
	
	/**
	 *  Lien avec la table deals : pas de propriété portée par la table DealSwap,
	 *  donc pas d'objet Java correspondant à la table
	 *  la responsabilité est portée par SwapObject, qui porte l'inverseJoinColomn
	 */
	@ManyToMany(mappedBy = "deals")
	@JsonBackReference
	private Set<SwapObject> swapObjects;
	public void addSwapObjects(SwapObject so) {so.addDeal(this); swapObjects.add(so);}
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	public Set<SwapObject> getSwapObjects() {return swapObjects;}
	
	/**
	 * Le Swap en cours, s'il existe.   
	 */ 
	 @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	 @JsonBackReference
	 private Account account;
	 public void setAccount(Account a) {account = a;}
	 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	 public Account getAccount() {return this.account;}
	
	public Deal() {
	}
}