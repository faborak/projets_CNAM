package modeles.webscope;

import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Video {

  @Id
  @GeneratedValue
  @Column(name = "id_video")
  private Long idVideo;

  @Column
  private String titre;
      public void setTitre(String t) {titre = t;}
      public String getTitre() {return titre;}

  @Column
  private Integer annee;
      public void setAnnee(Integer a) {annee = a;}
      public Integer getAnnee() {return annee;}

  public Video() {  }
}