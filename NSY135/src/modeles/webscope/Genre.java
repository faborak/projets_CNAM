package modeles.webscope;

import javax.persistence.*;

@Entity
public class Genre {

	@Id
	private String code;
    public void setCode(String e) {code = e;}
    public String getCode() {return code;}
}
