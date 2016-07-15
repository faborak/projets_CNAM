package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;


@Entity
@PrimaryKeyJoinColumn(name="id_swap_object")
public class Item extends SwapObject {

	/**
	 * Coorespond à la colonne du même nom en base.
	 * 
	 */
	@Column
	private Float cost;
	public void setCost(Float c) {cost = c;}
	public Float getCost() {return cost;}
	

}
