package com.myswap.models;

import java.io.File;
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
import javax.persistence.Transient;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class SwapObject {

	/**
	 * Id du SwapObject. Clé primaire dans la table Swapobject. 
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
	*  Photos de l'objet à troquer.
	*/
	@Transient
	private Set<File> pic = new HashSet<File>();
    public void addPic(File f) {pic.add(f);}
    public Set<File> getPic() {return pic;}
	
	/**
	 * pour la sauvegarde en cascade, on utilise JPA (javax.peristance) et non pas hibernate
	 * Toutes les cascades sont sous la responsabilité de l'objet équipe
	 * 
	 */
    @ManyToOne (cascade=CascadeType.PERSIST)
	@JoinColumn (name="id_user")
	private User owner;
	public void setOwner(User u) {owner = u;}
	public User getOwner() {return owner;}
	
	/**
	 * SwapObject porte la responsabilité de la liaison avec les deals
	 * 
	 */
	@ManyToMany()
	@JoinTable(name = "Dealswap", joinColumns = @JoinColumn(name = "id_swap_object"),
              inverseJoinColumns = @JoinColumn(name = "id_deal"))
	private Set<Deal> deals = new HashSet<Deal>();
	public Set<Deal> getDeals() {return deals;}
	public void addDeal(Deal d) {deals.add(d);}
}	
