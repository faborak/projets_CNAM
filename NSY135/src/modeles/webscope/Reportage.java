package modeles.webscope;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name="id_video")
public class Reportage extends Video {
	
      @Column
      String lieu;
      public void setLieu(String l) {lieu = l;}
      public String getLieu() {return lieu;}
      
}