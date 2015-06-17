package modeles.webscope;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="id_video")
public class FilmV extends Video {

	@ManyToOne
	@JoinColumn (name="id_realisateur")
	private Artiste realisateur;
	public void setRealisateur(Artiste a) {realisateur = a;}
	public Artiste getRealisateur() {return realisateur;}
	
	@ManyToOne
	@JoinColumn(name = "pays")
	Pays pays;
	public void setPays(Pays p) {pays = p;}
	public Pays getPays() {return pays;}
		
	@ManyToOne
	@JoinColumn (name="genre")
	private Genre genre;
	public void setGenre(Genre g) {genre = g;}
	public Genre getGenre() {return genre;} 

}