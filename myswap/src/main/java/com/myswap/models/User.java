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

@Entity
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
	 * Les objets de l'utilisateur.
	 * La responsabilité du mappage est confié à swapObject, via l'annotation mappedBy.  
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy="owner")
	private Set<SwapObject> wholeOfItems = new HashSet<SwapObject>();
	public void addWholeOfItems(SwapObject f) {f.setOwner(this); wholeOfItems.add(f);}
	public Set<SwapObject> getWholeOfItems() {return wholeOfItems;}
	

	/**
	 * Les commentaire écrits par l'utilisateur.
	 * La responsabilité du mappage est confié à comment, via l'annotation mappedBy.  
	 */
	@OneToMany(mappedBy="noting")
	private Set<Comment> commentsWrited = new HashSet<Comment>();
	public void addCommentsWrited(Comment f) {f.setNoting(this); commentsWrited.add(f);}
	public Set<Comment> getCommentsWriteds() {return commentsWrited;}
	
	/**
	 * Les commentaire qui concernent l'utilisateur.
	 * La responsabilité du mappage est confié à comment, via l'annotation mappedBy.  
	 */
	@OneToMany(mappedBy="noted")
	private Set<Comment> commentsOnUser = new HashSet<Comment>();
	public void addCommentsOnUser(Comment f) {f.setNoted(this); commentsOnUser.add(f);}
	public Set<Comment> getCommentsOnUser() {return commentsOnUser;}
	
	/**
	 * Les Deals initiés par l'utilisateur.
	 * La responsabilité du mappage est confiée à Deal, via l'annotation mappedBy.  
	 */
	@OneToMany(mappedBy="initiator")
	private Set<Deal> dealsInitator = new HashSet<Deal>();
	public void addDealsInitator(Deal f) {f.setInitiator(this); dealsInitator.add(f);}
	public Set<Deal> getDealsInitator() {return dealsInitator;}
	
	/**
	 * Les Deals proposés à l'utilisateur.
	 * La responsabilité du mappage est confiée à Deal, via l'annotation mappedBy.  
	 */
	@OneToMany(mappedBy="proposed")
	private Set<Deal> dealsProposed = new HashSet<Deal>();
	public void addDealsProposed(Deal f) {f.setProposed(this); dealsProposed.add(f);}
	public Set<Deal> getDealsProposed() {return dealsProposed;}
	  
	 /**
	 * Le compte de l'utilisateur.   
	 */ 
	 @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	 private Account account;
	 public void setAccount(Account a) {account = a;}
	 public Account getAccount() {return this.account;}
      
	  /**
	 * L'adresse de l'utilisateur.   
	 */  
	 @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	 private Adress adress;
	 public void setAdress(Adress a) {adress = a;}
	 public Adress getAdress() {return this.adress;} 
	  
	 public  User() {} 
}