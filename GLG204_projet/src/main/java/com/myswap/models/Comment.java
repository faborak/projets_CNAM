package com.myswap.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * This class represents a Comment from a user to another user of the MySwap company.
 * There's a noted and a noting user in a comment.
 */
@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id_comment")
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
	 * Note. 
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
    @JsonManagedReference
	private User noted;
	public void setNoted(User g) {noted = g;}
//	 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//	 @JsonIgnore
	public User getNoted() {return noted;}
	
	/**
	 * user notant le commentaire.
	 */
    @ManyToOne (cascade=CascadeType.PERSIST)
	@JoinColumn (name="id_noting_user")
    @JsonManagedReference
	private User noting;
	public void setNoting(User g) {noting = g;}
//	 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//	 @JsonIgnore
	public User getNoting() {return noting;}
}	
