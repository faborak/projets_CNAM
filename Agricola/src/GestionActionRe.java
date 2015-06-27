import java.awt.event.*;

/*******************************************************************
/** Role du fichier   : Gestion des clic sur le plateau de joueur
/** Auteur            : Fabrice Lecomte        					
/** Date de creation  : 05/05/2014  
/**     Commentaire   : La gestion se fera en fonction des coordonnees 
/** 					x et y du point clique. Une meilleure gestion 
/**						aurait implique un Listener par rectangle.
/**     Modifications :        					
/*********************************************************************/


public class GestionActionRe implements MouseListener {
// Systeme de reception du clic platau joueur : tableau de boolean a false
// si clic, passe a true
private PlateauRessourcesD PReD;
public boolean Plateau[] = new boolean[11];

public GestionActionRe(PlateauRessourcesD PReD) {
this.PReD = PReD; 
}

public void mouseClicked(MouseEvent e) {
    int x,y;
    x = e.getX();
    y = e.getY();
	System.out.println("Coordonnees x :"+x+" et y : "+y);
	
	// initialise le tableau a chaque clic
	for (int i = 1; i < 11; i++) {
		Plateau[i] = false ;
	}
	
	
	//un switch / case permettrait de gérer les clic "à cote" dans Aide
	// 1ère colonne
		if (x>10 && x<260 && y>10 && y<55)	{Plateau[1] = true ;}
		if (x>10 && x<260 && y>60 && y<105)	{Plateau[2] = true ;}
		if (x>10 && x<260 && y>110 && y<165){Plateau[3] = true ;}
		if (x>10 && x<260 && y>160 && y<205){Plateau[4] = true ;}
		if (x>10 && x<260 && y>210 && y<265){Plateau[5] = true ;}
		if (x>10 && x<260 && y>260 && y<305){Plateau[6] = true ;}
		if (x>10 && x<260 && y>310 && y<365){Plateau[7] = true ;}
		if (x>10 && x<260 && y>360 && y<405){Plateau[8] = true ;}
		if (x>10 && x<260 && y>410 && y<465){Plateau[9] = true ;}
		if (x>10 && x<260 && y>460 && y<505){Plateau[10]= true ;}
		
		PReD.RecevoirClicPRe(Plateau);
		
}



public void mouseEntered(MouseEvent arg0) {}
public void mouseExited(MouseEvent arg0) {}
public void mousePressed(MouseEvent arg0) {}
public void mouseReleased(MouseEvent arg0) {}



}