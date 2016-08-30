package com.myswap.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@JsonIgnoreProperties ({"hibernateLazyInitializer", "handler", "fieldHandler"}) 
public class SwapObject {

	/**
	 * Id du SwapObject. Cl� primaire dans la table Swapobject. 
	 */
	@Id
	@GeneratedValue
	@Column(name = "id_swap_object")
	private Long IdSwapObjet;
	public void setIdSwapObjet(Long i) {IdSwapObjet = i;}
	public Long getIdSwapObjet() {return IdSwapObjet;}
	
	/**
	 * Nom du SwapObject. 
	 */
	@Column
	private String name;
	public void setName(String n) {name = n;}
	public String getName() {return name;}
	
	/**
	 * Date de creation du SwapObject. 
	 */
	@Column(name = "date_creation")
	private Date dateCreation;
	public void setDateCreation(Date d) {dateCreation = d;}
	public Date getDateCreation() {return dateCreation;}
	
	/**
	 * Date de modification du SwapObject. 
	 */
	@Column(name = "date_modification")
	private Date dateModification;
	public void setDateModification(Date d) {dateModification = d;}
	public Date getDateModification() {return dateModification;}
	
	/**
	 * Description du SwapObject. 
	 */
	@Column
	private String description;
	public void setDescription(String n) {description = n;}
	public String getDescription() {return description;}
	
	/**
	 * 
	 */
    @ManyToOne (cascade=CascadeType.PERSIST)
	@JoinColumn (name="id_user")
    @JsonManagedReference
	private User owner;
	public void setOwner(User u) {owner = u;}
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	public User getOwner() {return owner;}
	
	/**
	 * SwapObject porte la responsabilit� de la liaison avec les deals
	 * 
	 */
	@ManyToMany()
	@JoinTable(name = "Dealswap", joinColumns = @JoinColumn(name = "id_swap_object"),
              inverseJoinColumns = @JoinColumn(name = "id_deal"))
	@JsonBackReference
	private Set<Deal> deals = new HashSet<Deal>();
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	public Set<Deal> getDeals() {return deals;}
	public void addDeal(Deal d) {deals.add(d);}
	
	/**
	 * Les Pictures de l'item.
	 * La responsabilit� du mappage est confi�e � ItemPicture, via l'annotation mappedBy.  
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy="itemRepresented")
	@JsonManagedReference
	private Set<ItemPicture> itemPictures = new HashSet<ItemPicture>();
	public void addItemPictures(ItemPicture f) {f.setItemRepresented(this); itemPictures.add(f);}
//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	public Set<ItemPicture> getItemPictures () {return itemPictures ;}
	
	public  SwapObject() {} 
}	
