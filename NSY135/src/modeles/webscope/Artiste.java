package modeles.webscope;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Artiste {

	@Id
	@GeneratedValue
	private Integer id;
	public void setId(Integer i) {id = i;}
	public Integer getId() {return id;}
	
	@Column
	String nom;
	public void setNom(String t) {nom = t;}
	public String getNom() {return nom;}

	@Column
	String prenom;
	public void setPrenom(String p) {prenom = p;}
	public String getPrenom() {return prenom;}
	
	@Column
	Integer annee_naissance;
	public void setAnnee_naissance(Integer a) {annee_naissance = a;}
	public Integer getAnnee_naissance() {return annee_naissance;}
	
	@OneToMany(mappedBy="realisateur")
//	@JoinColumn(name="id_realisateur")
	private Set<Film> filmsRealises = new HashSet<Film>();
	public void addFilmsRealise(Film f) {f.setRealisateur(this); filmsRealises.add(f);}
	public Set<Film> getFilmsRealises() {return filmsRealises;}
	
	@ManyToMany(mappedBy = "acteurs")
	public Set<Film> filmo;
	public Set<Film> getFilmo() {return filmo;}
	
	@OneToMany(mappedBy = "pk.acteur")
	private Set<Role> roles = new HashSet<Role>();
	public Set<Role> getRoles() {return this.roles;}
//	public void setRoles(Set<Role> r) {this.roles = r;}
	public void addRole(Role r) {r.setActeur(this); roles.add(r);}
}
