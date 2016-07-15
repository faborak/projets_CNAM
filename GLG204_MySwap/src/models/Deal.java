package models;

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

@Entity
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

	/**
	 * user Initiateur du Deal.
	 */
    @ManyToOne (fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
	@JoinColumn (name="id_first_user")
	private User initiator;
	public void setInitiator(User u) {initiator = u;}
	public User getInitiator() {return initiator;}
	
	/**
	 * user propos� pour le Deal.
	 */
    @ManyToOne (fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
	@JoinColumn (name="id_second_user")
	private User proposed;
	public void setProposed(User u) {proposed = u;}
	public User getProposed() {return proposed;}
	
	/**
	 *  Lien avec la table deals : pas de propri�t� port�e par la table DealSwap,
	 *  donc pas d'objet Java correspondant � la table
	 *  la responsabilit� est port�e par SwapObject, qui porte l'inverseJoinColomn
	 */
	@ManyToMany(mappedBy = "deals")
	Set<SwapObject> swapObjects;
	public void addSwapObjects(SwapObject so) {so.addDeal(this); swapObjects.add(so);}
	public Set<SwapObject> getSwapObjects() {return swapObjects;}
	
	public Deal() {
	}
}