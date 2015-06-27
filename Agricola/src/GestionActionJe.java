import java.awt.event.*;

/*******************************************************************
/** Role du fichier   : Gestion des clic sur le plateau de jeu
/** Auteur            : Fabrice Lecomte        					
/** Date de creation  : 05/05/2014  
/**     Commentaire   : La gestion se fera en fonction des coordonnees 
/** 					x et y du point clique. Une meilleure gestion 
/**						aurait implique un Listener par rectangle
/**     Modifications :        					
/*********************************************************************/

public class GestionActionJe implements MouseListener {
private PlateauJeuD PJeD;
private Evenement Event;

// References croisees
public GestionActionJe(PlateauJeuD PJeD, Evenement Event) {
this.PJeD = PJeD; 
this.Event = Event;
}

public void mouseClicked(MouseEvent e) {
// ensemble des coordonnes correspondant a une action
    int x,y;
    x = e.getX();
    y = e.getY();
	System.out.println("Coordonnees x :"+x+" et y : "+y);

// 1ère colonne
	if (x>0 && x<50 && y>115 && y<225){PJeD.ConstructionPiece(1, 2);}
	if (x>0 && x<50 && y>230 && y<340){PJeD.premierjoueur(1, 3);}

// 2ème colonne
	if (x>55 && x<105 && y>0 && y<110)  {PJeD.Cereale_Legume(2, 1, 5);}
	if (x>55 && x<105 && y>115 && y<225){PJeD.Labourage(2, 2, 1);}
	if (x>55 && x<105 && y>230 && y<340){PJeD.savoirfaire();}
		
// 3ème colonne
	if (x>110 && x<160 && y>0 && y<110)  {PJeD.Collecte(3, 1, 11);}		
	if (x>110 && x<160 && y>115 && y<225){PJeD.Collecte(3, 2, 1);}		
	if (x>110 && x<160 && y>230 && y<340){PJeD.Collecte(3, 3, 2);}
				
// 4ème colonne
	if (x>165 && x<215 && y>0 && y<110)  {PJeD.Collecte(4, 1, 3);}
	if (x>165 && x<215 && y>115 && y<225){PJeD.Collecte(4, 2, 10);}
	if (x>165 && x<215 && y>230 && y<340){Event.evenement1();}
		
// 5ème colonne
	if (x>220 && x<270 && y>0 && y<110)  {Event.evenement2();}
	if (x>220 && x<270 && y>115 && y<225){Event.evenement3();}
	if (x>220 && x<270 && y>230 && y<340){Event.evenement4();}
					
// 6ème colonne
	if (x>275 && x<325 && y>0 && y<110)  {Event.evenement5();}
	if (x>275 && x<325 && y>115 && y<225){Event.evenement6();}
	if (x>275 && x<325 && y>230 && y<340){Event.evenement7();}
					
// 7ème colonne
	if (x>330 && x<380 && y>0 && y<110)  {System.out.println("Cette case n'appartient pas au jeu à un joueur !");}
	if (x>330 && x<380 && y>115 && y<225){Event.evenement8();}
	if (x>330 && x<380 && y>230 && y<340){Event.evenement9();}
				
// 8ème colonne
	if (x>385 && x<435 && y>0 && y<110)  {System.out.println("Cette case n'appartient pas au jeu à un joueur !");}
	if (x>385 && x<435 && y>115 && y<225){Event.evenement10();}
	if (x>385 && x<435 && y>230 && y<340){Event.evenement11();}
					
// 9ème colonne
	if (x>440 && x<490 && y>0 && y<110)  {System.out.println("Cette case n'appartient pas au jeu à un joueur !");}			
	if (x>440 && x<490 && y>115 && y<225){Event.evenement12();}				
	if (x>440 && x<490 && y>230 && y<340){Event.evenement13();}
					
// 10ème colonne
	if (x>495 && x<545 && y>0 && y<110)  {PJeD.Regles();System.out.println("Cette case n'appartient pas au jeu à un joueur !");}			
	if (x>495 && x<545 && y>115 && y<225){Event.evenement14();}
	if (x>495 && x<545 && y>230 && y<340){System.out.println("debug");} //PJeD.Regles();}	
	
}

public void mouseEntered(MouseEvent arg0) {
	// L'utilisation de cette categorie pourrait afficher des message d'aide
}
public void mouseExited(MouseEvent arg0) {}
public void mousePressed(MouseEvent arg0) {}
public void mouseReleased(MouseEvent arg0) {}

}