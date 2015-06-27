import java.awt.*;

/*******************************************************************
/** Role du fichier   : Affiche les messages d'aide
/** Auteur            : Fabrice Lecomte        					
/** Date de creation  : 09/06/2014  
/**     Commentaire   : Ecriture sur le panel, sans label
/** 					qui aurai impliqué d'en definnir plusieurs
/**     Modifications :        					
/*********************************************************************/

public class Aide  {

private String TexteAide[] = new String[2];
private final static Color VertSapin = new Color(9,82,40);

public Aide(String t[]) {
	TexteAide = t;
}

public void dessine(Graphics g){
	
	Font FonteTitre = new Font("Helvetica", Font.BOLD, 18);
	Font FonteAide = new Font("Dialog", Font.PLAIN, 14);   
	g.setFont(FonteTitre);
	g.setColor(VertSapin);//g.setColor(Color.black);
	g.setFont(FonteTitre);
	g.drawString("Aide", 400, 20) ; 
	g.setFont(FonteAide);
	// essai de centrage du texte
	g.drawString(TexteAide[0], (150-TexteAide[0].length()), 45) ; 
	g.drawString(TexteAide[1], (150-TexteAide[1].length()), 60) ; 
	g.drawString(TexteAide[2], (150-TexteAide[2].length()), 75) ; 
	}
}