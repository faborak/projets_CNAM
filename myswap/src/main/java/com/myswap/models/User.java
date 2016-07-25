package com.myswap.models;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIgnoreProperties ({"hibernateLazyInitializer", "handler", "fieldHandler"}) 
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id_user")
public class User {

    @Id
	@GeneratedValue
	@Column(name = "id_user")
	private Long id_user;
	public void setId(Long i) {id_user = i;}
	public Long getId() {return id_user;}
	  
	@Column
	private String name;
	public void setName(String n) {name = n;}
	public String getName() {return name;}

    @Column
    private String lastname;
    public void setLastname(String p) {lastname = p;}
    public String getLastname() {return lastname;}
	  
	/**
	*  Photo de profil de l'utilisateur.
	*/
	@Transient
	private File pic;
    public void setPic(File f) {pic = f;}
    public File getPic() {return pic;}
	  
	/**
	 * Les commentaire écrits par l'utilisateur.
	 * La responsabilité du mappage est confié à comment, via l'annotation mappedBy.  
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy="noting")
	@JsonBackReference
	private Set<Comment> commentsWrited = new HashSet<Comment>();
	public void addCommentsWrited(Comment f) {f.setNoting(this); commentsWrited.add(f);}
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//	@JsonIgnore
	public Set<Comment> getCommentsWriteds() {return commentsWrited;}
	
	/**
	 * Les commentaire qui concernent l'utilisateur.
	 * La responsabilité du mappage est confié à comment, via l'annotation mappedBy.  
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy="noted")
	@JsonBackReference
	private Set<Comment> commentsOnUser = new HashSet<Comment>();
	public void addCommentsOnUser(Comment f) {f.setNoted(this); commentsOnUser.add(f);}
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//	@JsonIgnore
	public Set<Comment> getCommentsOnUser() {return commentsOnUser;}
	
	/**
	 * Les objets de l'utilisateur.
	 * La responsabilité du mappage est confié à swapObject, via l'annotation mappedBy.  
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy="owner")
	@JsonBackReference
	private Set<SwapObject> wholeOfItems = new HashSet<SwapObject>();
	public void addWholeOfItems(SwapObject f) {f.setOwner(this); wholeOfItems.add(f);}
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//	@JsonIgnore
	public Set<SwapObject> getWholeOfItems() {return wholeOfItems;}
	
	/**
	 * Les Deals initiés par l'utilisateur.
	 * La responsabilité du mappage est confiée à Deal, via l'annotation mappedBy.  
	 */
	@OneToMany(fetch = FetchType.EAGER,mappedBy="initiator")
	@JsonBackReference
	private Set<Deal> dealsInitator = new HashSet<Deal>();
	public void addDealsInitator(Deal f) {f.setInitiator(this); dealsInitator.add(f);}
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//	@JsonIgnore
	public Set<Deal> getDealsInitator() {return dealsInitator;}
	
	/**
	 * Les Deals proposés à l'utilisateur.
	 * La responsabilité du mappage est confiée à Deal, via l'annotation mappedBy.  
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy="proposed")
	@JsonBackReference
	private Set<Deal> dealsProposed = new HashSet<Deal>();
	public void addDealsProposed(Deal f) {f.setProposed(this); dealsProposed.add(f);}
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//	@JsonIgnore
	public Set<Deal> getDealsProposed() {return dealsProposed;}
	  
	 /**
	 * Le compte de l'utilisateur.   
	 */ 
	 @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	 @JsonBackReference
	 private Account account;
	 public void setAccount(Account a) {account = a;}
	 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//	 @JsonIgnore
	 public Account getAccount() {return this.account;}
      
	  /**
	 * L'adresse de l'utilisateur.   
	 */  
	 @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	 @JsonBackReference
	 private Adress adress;
	 public void setAdress(Adress a) {adress = a;}
	 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//	 @JsonIgnore
	 public Adress getAdress() {return this.adress;} 
	  
	 public  User() {} 
}