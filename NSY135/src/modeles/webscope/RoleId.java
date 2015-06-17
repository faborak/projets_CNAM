package modeles.webscope;

import javax.persistence.*;

@Embeddable
public class RoleId implements java.io.Serializable {

     /**
      *
      */
     private static final long serialVersionUID = 1L;

     @ManyToOne(fetch=FetchType.LAZY)
     @JoinColumn(name = "id_acteur")
     private Artiste acteur;
     public Artiste getActeur() {return acteur;}
     public void setActeur(Artiste a) {this.acteur = a;}

     @ManyToOne(fetch=FetchType.LAZY)
     @JoinColumn(name = "id_film")
     private Film film;
     public Film getFilm() {return film;}
     public void setFilm(Film f) {this.film = f;}
     
}