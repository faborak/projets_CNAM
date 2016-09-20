package com.myswap.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * 
 * @author myswap
 * User from the myswap website. It's linked to an Account, an Adress, an Activity
 * and some Infos wich together describes an user.
 *
 */
@Entity
@JsonIgnoreProperties ({"hibernateLazyInitializer", "handler", "fieldHandler"}) 
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
    
    @Column
    @JsonIgnore
    private String password;
    public void setPassword(String p) {password = p;}
    public String getPassword() {return password;}
	   
	/**
	 * Objects from user.
	 * responsability of linkage is let to SwapObject, mappedBy.  
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy="owner")
	@JsonBackReference
	private Set<SwapObject> wholeOfItems = new HashSet<SwapObject>();
	public void addWholeOfItems(SwapObject f) {f.setOwner(this); wholeOfItems.add(f);}
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	public Set<SwapObject> getWholeOfItems() {return wholeOfItems;}
    
	/**
	 * Comments written by the user.
	 * responsability of linkage is let to Comment, via mappedBy.  
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy="noting")
	@JsonBackReference
	private Set<Comment> commentsWrited = new HashSet<Comment>();
	public void addCommentsWrited(Comment f) {f.setNoting(this); commentsWrited.add(f);}
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	public Set<Comment> getCommentsWrited() {return commentsWrited;}
	
	/**
	 * Comments about the user.
	 * responsability of linkage is let to Comment, via mappedBy.  
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy="noted")
	@JsonBackReference
	private Set<Comment> commentsOnUser = new HashSet<Comment>();
	public void addCommentsOnUser(Comment f) {f.setNoted(this); commentsOnUser.add(f);}
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	public Set<Comment> getCommentsOnUser() {return commentsOnUser;}
	
	/**
	 * Deals initiated by the user.
	 * responsability of linkage is let to Deal, via mappedBy.  
	 */
	@OneToMany(fetch = FetchType.LAZY,mappedBy="initiator")
	@JsonBackReference
	private Set<Deal> dealsInitator = new HashSet<Deal>();
	public void addDealsInitator(Deal f) {f.setInitiator(this); dealsInitator.add(f);}
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	public Set<Deal> getDealsInitator() {return dealsInitator;}
	
	/**
	 * Deals proposed to the user.
	 * responsability of linkage is let to Deal, via mappedBy.  
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy="proposed")
	@JsonBackReference
	private Set<Deal> dealsProposed = new HashSet<Deal>();
	public void addDealsProposed(Deal f) {f.setProposed(this); dealsProposed.add(f);}
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	public Set<Deal> getDealsProposed() {return dealsProposed;}
	  
	 @OneToOne(mappedBy = "user")
	 @JsonManagedReference
	 private Account account;
	 public void setAccount(Account a) {account = a;}
	 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	 public Account getAccount() {return this.account;}
      
	 @OneToOne(mappedBy = "user")
	 @JsonManagedReference
	 private Adress adress;
	 public void setAdress(Adress a) {adress = a;}
	 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	 public Adress getAdress() {return this.adress;} 
	  
	 @OneToOne(mappedBy = "user")
	 @JsonBackReference
	 private Activity activity;
	 public void setActivity(Activity a) {activity = a;}
	 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	 public Activity getActivity() {return this.activity;}
	 
	 @OneToOne(mappedBy = "user")
	 @JsonManagedReference
	 private Infos infos;
	 public void setInfo(Infos a) {infos = a;}
	 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	 public Infos getInfos() {return this.infos;}
		
	 @OneToMany(fetch = FetchType.EAGER, mappedBy="owner")
	 @JsonManagedReference
	 private Set<UserPicture> userPictures = new HashSet<UserPicture>();
	 public void addUserPictures(UserPicture f) {f.setOwner(this); userPictures.add(f);}
 	 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	 public Set<UserPicture> getUserPictures () {return userPictures ;}
		
	 public  User() {} 
	 
}