import java.awt.*;

/*******************************************************************
/** Role du fichier   : Affiche les messages d'aide
/** Auteur            : Fabrice Lecomte        					
/** Date de creation  : 09/06/2014  
/**     Modifications :        					
/*********************************************************************/

public class AideD extends Canvas {

// Références croisées 
Agricola jeu;
Aide Ai;
	
private static final long serialVersionUID = 1L;
// initialisation de l'aide
public String TexteAide[] = {""
		,"Le plateau du bas représente votre ferme, avec deux maisons en bois, deux habitants, et des terrains inocupés."
		,"Le plateau du milieu représente l'ensemble des actions à jouer, essayer de poser un habitant dessus !"}	;
private int init = 0;
private int k = 1;

public AideD(Agricola j, String t) {
	jeu = j;	
	TexteAide[0] = t;
	setBackground(Color.white);
	Ai = new Aide(TexteAide);	
}

public void paint (Graphics g) {
Ai.dessine(g);
}

public void nouveau (String t) {
	// apres deux texte d'aide, fait ddefiler les textes vers le haut
	// cette boucle est rendue inutile par l'initialisation fait plus haut,
	// mais en cas de modification elle pourrait servir a nouveau
	switch(init){
	case 0 :
		TexteAide[1] = k+"- "+t;
		init = 1;
		k++;
	break;
	case 1 :
		TexteAide[2] = k+"- "+t;
		init = 2;
		k++;
	break;
	case 2 :
		TexteAide[0] = TexteAide[1];
		TexteAide[1] = TexteAide[2];
		TexteAide[2] = k+"- "+t;
		k++;
	break;
	}
	repaint();
}

}