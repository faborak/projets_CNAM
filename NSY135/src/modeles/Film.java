package modeles;

/**
 * Encapsulation de la représentation d’un film
 *
 */
public class Film {
	private String titre;
	private Integer annee;
	private Integer id_realisateur;

	public Film() {
	}

	public void setTitre(String leTitre) {
		titre = leTitre;
	}

	public void setAnnee(Integer lAnnee) {
		annee = lAnnee;
	}

	public String getTitre() {
		return titre;
	}

	public Integer getAnnee() {
		return annee;
	}

	public Integer getId_realisateur() {
		return id_realisateur;
	}

	public void setId_realisateur(Integer id_realisateur) {
		this.id_realisateur = id_realisateur;
	}


}