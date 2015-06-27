import java.awt.*;

public class PlateauJeuD extends Canvas  {

//références croisées
public Agricola jeu;
public PlateauJeuD PJeD;
public PlateauJoueurD PJoD;
public PlateauRessourcesD PReD;
public PionJoueur PJ;
public AideD Ai;
public Evenement Event;
public PlateauJeu PJe;
public boolean verrou = false;

//variables de version et de couleur
private static final long serialVersionUID = 1L;
private static final Color couleurdefond = Color.green;
private static final Color vertpale = new Color( 200, 255, 200 );

//variables des ressources du plateau, initialisées pour le premier tour
public boolean ApparitionPierre1=false;
public boolean ApparitionPierre2=false;
public boolean ApparitionMouton=false;
public boolean ApparitionSanglier=false;
public boolean ApparitionBoeuf=false;
public int TourApparitionMoutons;
private int TourApparitionSangliers;
private int TourApparitionBoeufs;
private int TourApparitionPierre1;
private int TourApparitionPierre2;
private int Bois = 3;
private int Argile = 1;
private int Roseau = 1;
private int Pierre1 = 0;
private int Pierre2 = 0;
private int Peche = 1;
private int tour ;
public int Mouton =0;
public int Sanglier =0;
public int Boeuf =0;
private String TexteAide ="";

// Gestion des évènements, du départ : Texte  [] et ceux arrivant en cours, obtenus par l'objet Evenement
public String LibelleEvenements[] = new String[15];
public String texte[] = 
{   "","","Construction","Joueur 1",
	"Cereale","Labourage","Savoir Faire",
	"Journalier","Bois : "+Bois,"Argile : "+Argile,
	"Roseau : "+Roseau,"Peche : "+Peche,"Evenement 1",
	"Evenement 2","Evenement 3","Evenement 4",
	"Evenement 5","Evenement 6","Evenement 7",
	"","Evenement 8","Evenement 9",
	"","Evenement 10","Evenement 11",
	"","Evenement 12","Evenement 13",
	"","Evenement 14","Résumé",		};

// Gestion de l'affichage des pions sur le plateau de jeu
public boolean PlateauPion[][] = new boolean[11][6] ; // Contient les endroits où sont posés des pions sur le plateau
public int x;
public int y;

public PlateauJeuD(Agricola je, PlateauRessourcesD r, PlateauJoueurD jo, PionJoueur p, AideD a) {
jeu = je;
PReD = r;
PJoD = jo;
PJ = p ;
Ai = a;
setBackground(vertpale);
setForeground(couleurdefond);
setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
for (int i=1;i<11;i++){
	for (int j=1;j<4;j++){
		PlateauPion[i][j] = false;
	}
}
PJe = new PlateauJeu(PlateauPion, texte);
}

public void paint (Graphics g) {
PJe.dessine(g);
} 

public void nouveau(){
	texte[8]="Bois : "+Bois;
	texte[9]="Argile : "+Argile;
	texte[10]="Roseau : "+Roseau;
	texte[11]="Peche : "+Peche;
	if (ApparitionMouton == true){texte[(TourApparitionMoutons+11)] = "Moutons : "+Mouton;}
	if (ApparitionSanglier == true){texte[(TourApparitionSangliers+12)] = "Sangliers : "+Sanglier;}
	if (ApparitionBoeuf == true){texte[(TourApparitionBoeufs+13)] = "Boeufs : "+Boeuf;}
	if (ApparitionPierre1 == true){texte[(TourApparitionPierre1+11)] = "Pierre1 : "+Pierre1;}
	if (ApparitionPierre2 == true){texte[(TourApparitionPierre2+13)] = "Pierre2 : "+Pierre2;}
	PJe = new PlateauJeu(PlateauPion, texte);
	repaint();
}

public PlateauJeu GetPlateauJeu(){
	return PJe;
}

//methode de nouveau tour sur PlateauJeu
public void NouveauTour(int tour) {
this.tour = tour;	
if (tour==1 || tour==2 || tour ==3 || tour ==4 || tour ==5 || tour ==6 || tour ==7){
	texte[tour+11] = LibelleEvenements[tour];
	}
else 
{
	if (tour==8 || tour==9) {
	texte[tour+12] = LibelleEvenements[tour];
	} 
	else {
		if (tour==10 || tour==11) {
			texte[tour+13] = LibelleEvenements[tour];
			} 
			else {
				if (tour==12 || tour==13) {
					texte[tour+14] = LibelleEvenements[tour];} 
					else {
						if (tour==14) {texte[29] = LibelleEvenements[14];
						}
				}
			}
		}
	}

if (LibelleEvenements[tour] == "Moutons"){
	ApparitionMouton = true;
	TourApparitionMoutons = tour;}
if (LibelleEvenements[tour] == "Sangliers"){
	ApparitionSanglier = true;
	TourApparitionSangliers = tour;}
if (LibelleEvenements[tour] == "Boeufs"){
	ApparitionBoeuf = true;
	TourApparitionBoeufs = tour;}
if (LibelleEvenements[tour] == "Pierre1"){
	ApparitionPierre1 = true;
	TourApparitionPierre1 = tour;}
if (LibelleEvenements[tour] == "Pierre2"){
	ApparitionPierre2 = true;
	TourApparitionPierre2 = tour;}
if (ApparitionPierre1 == true) {Pierre1 ++;}
if (ApparitionPierre2 == true) {Pierre2 ++;;}
if (ApparitionMouton == true) {Mouton ++;}
if (ApparitionSanglier == true) {Sanglier ++;}
if (ApparitionBoeuf == true) {Boeuf ++;}
Bois = Bois+3;
Argile++;
Roseau++;
Peche++;
for (int i=1;i<11;i++){
	for (int j=1;j<4;j++){
		PlateauPion[i][j] = false;
	}
}
nouveau();
}

//methode de construction de piece et d'etable
public void ConstructionPiece(int a, int b) {
if (verrou == false){
	   x = a; 
	   y = b;
	   if (PlateauPion[x][y] == false) {
		   PlateauPion[x][y] = true;
		   if (PJoD.attente==1||PJoD.attente==3||PJoD.attente==5||PJoD.attente==6){PJoD.FinCoupContinu();}
		   System.out.println("PJeD : Cliquez sur une ressource a utiliser dans le panneau ressources !"); 
			TexteAide = "Cliquez sur le bois, l'argile ou la pierre dans le panneau ressources !";
			Ai.nouveau(TexteAide);
		   verrou = true;
		   nouveau();
		   PJoD.PJeD = this;
		   PJoD.ConstructionPiece();
	   }
	}		
}


//methode de premierjoueur
public void premierjoueur(int a, int b) { 
	if (verrou == false){
		   x = a; 
		   y = b;
		   if (PlateauPion[x][y] == false) {
			  if (PJoD.attente==1||PJoD.attente==3||PJoD.attente==5||PJoD.attente==6){PJoD.FinCoupContinu();}   
			   System.out.println("Sans impact dans cette version beta ! Jouez une autre case !");
				TexteAide = "Sans impact dans cette version beta ! Jouez une autre case !";
				Ai.nouveau(TexteAide);
		   }
	}
}

//methode de cereale et methode de legume, Type 5 pour une cereale et 6 pour un legume	
public void Cereale_Legume(int a, int b, int Type)  { 
		if (verrou == false){
			x = a; 
			y = b;
			   if (PlateauPion[x][y] == false) {
					  if (PJoD.attente==1||PJoD.attente==3||PJoD.attente==5||PJoD.attente==6){PJoD.FinCoupContinu();}
				   PlateauPion[x][y] = true;			
				   PReD.AddRessource(Type, 1) ;
				   TexteAide = "Vous avez ramassé une Cereale.";
				   Ai.nouveau(TexteAide);
				   PReD.nouveau() ;
				   nouveau();
				   PJ.RemovePionTour(); 
				   PJoD.nouveau();
				   PJoD.NouveauCoup(this);
			   }
		}
}

//methode de labourage
public void Labourage(int a, int b, int c)  {
if (verrou == false){	
	  x = a; 
	  y = b; 
	  if (PlateauPion[x][y] == false) {
		  PlateauPion[x][y] = true;
		  if (PJoD.attente==1||PJoD.attente==3||PJoD.attente==5||PJoD.attente==6){PJoD.FinCoupContinu();}
		  verrou = true;
		  int Cas = c; // 1 : Labourage normal, 2 : Labourage de la periode 5
		  System.out.println("PJeD : Cliquez sur un emplacement de votre propriété !"); 
			TexteAide = "Labourage d'un champ en cours.";
			Ai.nouveau(TexteAide);
		  nouveau();
		  PJ.RemovePionTour(); 
		  PJoD.nouveau();
		  PJoD.PJeD = this;
		  PJoD.labourage(x, y, Cas);
	  }
	}
}

//methode de savoir faire
public  void savoirfaire() {
	if (verrou == false){
		//if (PlateauPion[2][3] == false) {
		// PlateauPion[2][3] = true;
		// nouveau();
		 // if (PJoD.attente==1||PJoD.attente==3||PJoD.attente==5||PJoD.attente==6){PJoD.FinCoupContinu();}
		System.out.println("Sans impact dans cette version beta ! Jouez une autre case !");
		TexteAide = "Sans impact dans cette version beta ! Jouez une autre case !";
		Ai.nouveau(TexteAide);
		// thread.sleep (4000);
		// PlateauPion[3][2] = false;
		// Pion.AddPionTour
		// }
	}
}

//methode generique de collecte
public void Collecte(int a, int b, int Type) { 
	if (verrou == false){	
		x = a; 
		y = b;
		if (PlateauPion[x][y] == false) {
			PlateauPion[x][y] = true;
			  if (PJoD.attente==1||PJoD.attente==3||PJoD.attente==5||PJoD.attente==6){PJoD.FinCoupContinu();}
			switch(Type){
			case 1 :
				PReD.AddRessource(1, Bois) ;
				TexteAide = "Vous avez ramassé "+Bois+ " de bois.";
				Bois = 0 ;
				break;
			case 2 :
				PReD.AddRessource(2, Argile) ;
				TexteAide = "Vous avez ramassé "+Argile+ "d'argile.";
				Argile = 0 ;
				break;
			case 3 :
				PReD.AddRessource(3, Roseau) ;
				TexteAide = "Vous avez ramassé "+Roseau+" de roseau.";
				Roseau = 0 ;
				break;
			case 4 : // Le case 4 correspond a Pierre1
				PReD.AddRessource(4, Pierre1) ;
				TexteAide = "Vous avez ramassé "+Pierre1+" de pierre";
				Pierre1 = 0 ;
				break;
			case 10 : // Le case 10 correspond a la peche
				PReD.AddRessource(10, Peche) ;
				TexteAide = "Vous avez ramassé "+Peche+" de nourriture en pechant.";
				Peche = 0 ;
				break;
			case 11 : // Le case 11 correspond a journalier
				PReD.AddRessource(10, 2) ;
				TexteAide = "Vous avez ramassé 2 de nourriture.";
				break;
			case 12 : // Le case 11 correspond a Pierre2
				PReD.AddRessource(4, Pierre2);
				TexteAide = "Vous avez ramassé "+Pierre2+" de pierre";
				Pierre2 = 0 ;
				break;
			default : System.out.println("Erreur d'appel de la methode Collecte");
				break;
			}
			Ai.nouveau(TexteAide);
			PReD.nouveau() ;
			nouveau();
			PJ.RemovePionTour(); 
			PJoD.nouveau();
			PJoD.NouveauCoup(this);
		}
	}
}

/*

methode uniquement liees aux evenements

*/

//apparition du cas cloture
public  void clotures(int a, int b, int c) { 
	if (verrou == false){
		   x = a; 
		   y = b;
			if (PlateauPion[x][y] == false) {
				PlateauPion[x][y] = true;
				  if (PJoD.attente==1||PJoD.attente==3||PJoD.attente==5||PJoD.attente==6){PJoD.FinCoupContinu();}
				verrou = true;	
				System.out.println("PJeD : Cliquez sur un emplacement de votre propriété !");
				TexteAide = "Vous allez faire un paturage, pour acceuillir des animaux";
				Ai.nouveau(TexteAide);
				int Cas = c;
				nouveau();
				PJ.RemovePionTour(); 
				PJoD.nouveau();
				PJoD.PJeD = this;
				PJoD.Clotures(Cas);
			}	
	}
}

//Cas des moutons, sangliers et boeufs
public void import_animaux(int a, int b, int c) { 
	if (verrou == false){
		   x = a; // mémorise l'emplacement du pion s'il faut annuler le coup
		   y = b;
			if (PlateauPion[x][y] == false) {
				PlateauPion[x][y] = true;
				System.out.println("coordonnées"+x+y);
				  if (PJoD.attente==1||PJoD.attente==3||PJoD.attente==5||PJoD.attente==6){PJoD.FinCoupContinu();}
				int Animal = c;
				verrou = true;	
				System.out.println("PJeD : Cliquez sur un paturage de votre propriété !");
				TexteAide = "Vous allez receuillir des animaux.";
				Ai.nouveau(TexteAide);
				nouveau();
				PJ.RemovePionTour(); 
				PJoD.nouveau();
				PJoD.PJeD = this;
				switch(Animal){
					case 1 : PJoD.import_animaux(Mouton, Animal);
					break;
					case 2 : PJoD.import_animaux(Sanglier, Animal);
					break;
					case 3 : PJoD.import_animaux(Boeuf, Animal);
					break;
				}
			}
	}
}

//apparition du cas amenagement
public void amenagement(int a, int b) { 
System.out.println("PJeD : pas d'aménagement majeur dans cette version du jeu.");
TexteAide = "Pas d'aménagement majeur dans cette version du jeu. Jouez une autre case !";
Ai.nouveau(TexteAide);
}

//apparition du cas semaille_pain
public void Semailles(int a, int b) { 
	if (verrou == false){
		   x = a; 
		   y = b;
			if (PlateauPion[x][y] == false) {
				PlateauPion[x][y] = true;
				  if (PJoD.attente==1||PJoD.attente==3||PJoD.attente==5||PJoD.attente==6){PJoD.FinCoupContinu();}
				verrou = true;	
				System.out.println("PJeD : Cliquez sur un emplacement de votre propriété !"); 
				TexteAide = "Vous faites des semailles.";
				Ai.nouveau(TexteAide);
				nouveau();
				PJ.RemovePionTour(); 
				PJoD.nouveau();
				PJoD.PJeD = this;
				PJoD.Semailles(1);
			}
	}
}

//apparition du cas renovation
public void Renovations(int a, int b, int c) { 
	if (verrou == false){	
	   x = a; 
	   y = b;
		if (PlateauPion[x][y] == false) {
			PlateauPion[x][y] = true;
			  if (PJoD.attente==1||PJoD.attente==3||PJoD.attente==5||PJoD.attente==6){PJoD.FinCoupContinu();}
			verrou = true;
			int Cas = c; // Cas 1 : Naissance normale, Cas 2 : Naissance meme sans pièce libre
			nouveau();
			PJoD.PJeD = this;
			PJoD.Renovations(x, y, Cas);
		}
	}
}

//apparition du cas naissance
public void Naissance(int a, int b, int c) { 
	if (verrou == false){	
	  x = a; 
	  y = b;
	  if (PlateauPion[x][y] == false) {
		  PlateauPion[x][y] = true;
		  if (PJoD.attente==1||PJoD.attente==3||PJoD.attente==5||PJoD.attente==6){PJoD.FinCoupContinu();}
		  int Cas = c; // Cas 1 : Naissance normale, Cas 2 : Naissance meme sans pièce libre
		  verrou = true;	
		  nouveau();
		  PJ.RemovePionTour(); 
		  PJoD.nouveau();
		  PJoD.PJeD = this;
		  PJoD.Naissance(x, y, Cas);
	  }
	}
}

//appel des regles
public void Regles(){
	System.out.println("Regles");
	Regles r =new Regles();
}


}