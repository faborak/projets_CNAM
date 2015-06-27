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

public class GestionActionJo implements MouseListener {
// Systeme de reception du clic platau joueur : tableau de boolean a false
// si clic, passe a true
private PlateauJoueurD PJoD;
public boolean Plateau[][] = new boolean[6][4];

public GestionActionJo(PlateauJoueurD PJoD) {
this.PJoD = PJoD; // PJeD variable d'instance de la classe PlateauJeuD
}

public void mouseClicked(MouseEvent e) {
    int x,y;
    x = e.getX();
    y = e.getY();
	System.out.println("Coordonnees x :"+x+" et y : "+y);
	
	// initialise le tableau a chaque clic
	for (int i = 1; i < 6; i++) {
		for (int j = 1; j < 4; j++) {
			Plateau[i][j] = false ;
	}}
	
	
//un switch/case permettrait de gérer les clics en dehors des zones acceptees
	// 1ère colonne
		if (x>0 && x<50 && y>0 && y<50)			{Plateau[1][1] = true ;}
		if (x>0 && x<50 && y>55 && y<105)		{Plateau[1][2] = true ;}	
		if (x>0 && x<50 && y>110 && y<160)		{Plateau[1][3] = true ;}
		
		// 2ème colonne
		if (x>55 && x<105 && y>0 && y<50)		{Plateau[2][1] = true ;}
		if (x>55 && x<105 && y>55 && y<105)		{Plateau[2][2] = true ;}
		if (x>55 && x<105 && y>110 && y<160)	{Plateau[2][3] = true ;}
		
		// 3ème colonne
		if (x>110 && x<160 && y>0 && y<50)		{Plateau[3][1] = true ;}          
		if (x>110 && x<160 && y>55 && y<105)	{Plateau[3][2] = true ;}          
		if (x>110 && x<160 && y>110 && y<160)	{Plateau[3][3] = true ;}
		
		// 4ème colonne
		if (x>165 && x<215 && y>0 && y<50)		{Plateau[4][1] = true ;}          
		if (x>165 && x<215 && y>55 && y<105)	{Plateau[4][2] = true ;}          
		if (x>165 && x<215 && y>110 && y<160)	{Plateau[4][3] = true ;}
		
		// 5ème colonne
		if (x>220 && x<270 && y>0 && y<50)		{Plateau[5][1] = true ;}             
		if (x>220 && x<270 && y>55 && y<105)	{Plateau[5][2] = true ;}             
		if (x>220 && x<270 && y>110 && y<160)	{Plateau[5][3] = true ;}	
		
		PJoD.RecevoirClicPJo(Plateau);
		
}

public void mouseEntered(MouseEvent arg0) {}
public void mouseExited(MouseEvent arg0) {}
public void mousePressed(MouseEvent arg0) {}
public void mouseReleased(MouseEvent arg0) {}



}