package modeles.webscope;

import javax.persistence.*;

@Entity
public class Role {

	 @Id
	 RoleId pk;
	 public RoleId getPk() {return pk;}
	 public void setPk(RoleId pk) {this.pk = pk;}
	
	 @Column(name="nom_role")
	 private String nom;
	 public void setNom(String n) {nom= n;}
	 public String getNom() {return nom;}
	     
	 @OneToOne(fetch=FetchType.LAZY)
	 public Film getFilm() {return getPk().getFilm();}
	 public void setFilm(Film film) {getPk().setFilm(film);}
	
	 @OneToOne(fetch=FetchType.LAZY) // Ajout de l'annotion OneToOne, fonctionne sans
	 public Artiste getActeur() {return getPk().getActeur();}
	 public void setActeur(Artiste acteur) {getPk().setActeur(acteur);}
}