package modeles.webscope;

import javax.persistence.*;

@Entity
public class Pays {
	@Id
	String code;
	public void setCode(String c) {code = c;}
	public String getCode() {return code;}

	@Column
	String nom;
	public void setNom(String n) {nom = n;}
	public String getNom() {return nom;}

	@Column
	String langue;
	public void setLangue(String l) {langue = l;}
	public String getLangue() {return langue;}

}