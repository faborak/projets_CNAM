package modeles.webscope;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Film {
	@Id
	@GeneratedValue
	private Integer id;
	public void setId(Integer i) {id = i;}
	public Integer getId() {return id;}

	@Column
	String titre;
	public void setTitre(String t) {titre = t;}
	public String getTitre() {return titre;}
	
	@Column
	Integer annee;
	public void setAnnee(Integer a) {annee = a;}
	public Integer getAnnee() {return annee;}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "code_pays")
	Pays pays;
	public void setPays(Pays p) {pays = p;}
	public Pays getPays() {return pays;}
	
//	@ManyToOne(fetch=FetchType.LAZY)
	// pour la sauvegarde en cascade, on utilise JPA (javax.peristance) et non pas hibernate
	@ManyToOne (fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
	@JoinColumn (name="id_realisateur")
	private Artiste realisateur;
	public void setRealisateur(Artiste a) {realisateur = a;}
	public Artiste getRealisateur() {return realisateur;}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn (name="genre")
	private Genre genre;
	public void setGenre(Genre g) {genre = g;}
	public Genre getGenre() {return genre;} 
	
	@ManyToMany()
	@JoinTable(name = "Role", joinColumns = @JoinColumn(name = "id_film"),
	              inverseJoinColumns = @JoinColumn(name = "id_acteur"))
	public Set<Artiste> acteurs = new HashSet<Artiste>();
	public Set<Artiste> getActeurs() {return acteurs;}
	
	@OneToMany(mappedBy = "pk.film")
//	@BatchSize(size=50) //pour le chargement par lot
	private Set<Role> roles = new HashSet<Role>();
	public Set<Role> getRoles() {return this.roles;}
//  Modificaiton de setRoles
	public void addRole(Role r) {r.setFilm(this); roles.add(r);}
	  
}