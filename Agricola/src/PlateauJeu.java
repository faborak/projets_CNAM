import java.awt.*;

/*******************************************************************
/** Role du fichier   : dessine le plateau mathematiquement
/** Auteur            : Fabrice Lecomte        					
/** Date de creation  : 24/09/2014  
/**					  :	
/**     Modifications :        					
/*********************************************************************/

public class PlateauJeu extends Panel {

private static final long serialVersionUID = 1L;
private Color vertclair = new Color( 0, 160, 75 );
private Color ambre = new Color( 240, 195, 0 );
private int nbjoueurs = 1;
private int Pointx;
private int Pointy;
private int Pointxtext;
private int Pointytext;
int i;
int j;
Agricola jeu;
PlateauJeuD PJeD;


public String texte[] = new String[31];

boolean PlateauPion[][];
private Color CouleurJoueur = new Color( 0, 0, 0 );

public PlateauJeu(boolean pl[][], String[] s){
	PlateauPion = pl;
	texte =s;
}

public void dessine(Graphics g) {
Font maFonte = new Font("Courier", Font.BOLD, 12);
g.setFont(maFonte);

if (nbjoueurs == 1) {
	int k = 0;
	for ( i = 0; i<10; i++) {
		for ( j = 0; j<3; j++) {
			k = k + 1; 
			Pointx = i*55;
			if (Pointx == 0) { Pointx = 1;}
			Pointy = j*115;
			if (Pointy == 0) { Pointy = 1;}
			Pointxtext = Pointx + 10;
			Pointytext = Pointy + 20;
			new Rectangle(Pointx, Pointy, 50, 110, g, vertclair);
			
			g.setColor(Color.black);
			for (int l = 0; l < texte[k].length(); l++){
				g.drawString(texte[k].charAt(l)+"", Pointxtext, Pointytext) ; 
				Pointytext = Pointytext+8;
			} 
		} 
	} // fin des deux boucles for
}  // fin du if
else
{
System.exit(0);
 System.out.println("le jeu a plusieurs joueurs se fera dans une version ultérieure !");
}

//Boucle pour les rectangles de recoltes
for (int i = 4; i<10; i++) {
	Pointx = i*55;
	Pointy = 330;
	Pointxtext = Pointx + 5;
	Pointytext = Pointy + 10;
	
	Font FonteRecoltes = new Font("Courier", Font.BOLD, 10);
	g.setFont(FonteRecoltes);
	new Rectangle(Pointx, Pointy, 50, 20, g, ambre);	
	g.setColor(Color.black);
	g.drawString("Recoltes", Pointxtext, Pointytext) ; 
}

//Deuxième partie de dessine : les pions déjà joués pour ce tour sont a afficher
// affiche un pion sur le plateau en fonction de plateaupion
for (i=1; i < 11; i++) {
 for(j=1;j<4;j++){
   if (PlateauPion[i][j] == true){
	 int pointxcercle = ((i-1)*55)+20; 
	 int pointycercle = ((j-1)*115)+40; 
	 new Cercle(pointxcercle, pointycercle, 30, g, CouleurJoueur);
   	}
  }
}

}

} 