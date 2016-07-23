package com.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Comment {

	/**
	 * Id du Comment. Clé primaire dans la table Comment. 
	 */
	@Id
	@GeneratedValue
	@Column(name = "id_comment")
	private Long IdComment;
	public void setIdComment(Long i) {IdComment = i;}
	public Long getIdComment() {return IdComment;}
	
	/**
	 * Libellé du commentaire. 
	 */
	@Column
	String label;
	public void setLabel(String n) {label = n;}
	public String getLabel() {return label;}
	
	/**
	 * Libellé du commentaire. 
	 */
	@Column
	Integer mark;
	public void setMark(Integer m) {mark = m;}
	public Integer getMark() {return mark;}
	
	/**
	 * user noté par le commentaire.
	 */
    @ManyToOne (cascade=CascadeType.PERSIST)
	@JoinColumn (name="id_noted_user")
	private User noted;
	public void setNoted(User g) {noted = g;}
	public User getNoted() {return noted;}
	
	/**
	 * user notant le commentaire.
	 */
    @ManyToOne (cascade=CascadeType.PERSIST)
	@JoinColumn (name="id_noting_user")
	private User noting;
	public void setNoting(User g) {noting = g;}
	public User getNoting() {return noting;}
}	
