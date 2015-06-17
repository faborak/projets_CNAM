package modeles.webscope;

import javax.persistence.*;

@Entity
public class Region {

	
	@Id
	private String region;
    public void setCode(String e) {region = e;}
}
