import java.awt.*;
import java.awt.event.*;

/*******************************************************************
/** Role du fichier   : Connecteur de l'application
/** Ateur             : Fabrice Lecomte        					
/** Date de creation  : 29/04/2014  
/**     Modifications : 10/06/2014 : Ajout de la barre d'aide       					
/*********************************************************************/

public class Agricola extends Frame {

private static final long serialVersionUID = 1L;
public final static int LG = 930;
public final static int HT = 750;
private static Color vertpale = new Color( 200, 255, 200 );

//références croisées
public Agricola jeu;
public PlateauJeuD PJeD;
public PlateauJoueurD PJoD;
public PlateauRessourcesD PReD;
public PionJoueur Pion;
public PlateauJeu PJe;

// variables de jeu
int Pions ;
int Pointnourriture;
int Tour;
String TextedAide = "";

// Nécessaire pour lancer l'application
public static void main (String[] args) { 
Agricola jeu = new Agricola();
 jeu.init();
}

public void init() { 
	Frame f = new Frame();
	f.setTitle("Projet VARI : Agricola");
	f.setSize(HT, LG);
	f.setBackground(vertpale);
	
	GridBagLayout gb = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	f.setLayout(gb);
	
	PionJoueur Pion = new PionJoueur(this);
	Pion.AddPionTotaux();
	Pion.AddPionTotaux();
	for (int i = 0; i < Pion.GetPionTotaux(); i++){
	Pion.AddPionTour();}
	AideD AiD = new AideD(this, "Bienvenue dans Agricola ! Le panneau de droite sont vos ressources actuelles.");
	PlateauRessourcesD PReD = new PlateauRessourcesD(this);
	PlateauJoueurD PJoD = new PlateauJoueurD(this, PReD, Pion, AiD);
	PlateauJeuD PJeD = new PlateauJeuD(this, PReD, PJoD, Pion, AiD);
	Evenement Event = new Evenement(this, PJeD);
	GestionActionJe GAJe = new GestionActionJe(PJeD, Event);
	GestionActionJo GAJo = new GestionActionJo(PJoD);
	GestionActionRe GARe = new GestionActionRe(PReD);
	

	//Ajout de la barre de texte d'aide 10/06/2014
	gbc.gridx = 0;
	gbc.gridy = 0;
	gbc.gridheight =1;
	gbc.weightx = 2; // définit le nombre de cellules du composant
	gbc.weighty = 1;
	gbc.gridwidth = GridBagConstraints.REMAINDER ; 
	gbc.insets = new Insets(5, 5, 5, 5);
	gbc.fill = GridBagConstraints.BOTH;
	gbc.anchor = GridBagConstraints.LINE_START;
	f.add(AiD, gbc) ;
	
	// Positionnement du Plateau de Jeu
	gbc.gridx = 0;
	gbc.gridy = 1;
	gbc.gridheight =2;
	gbc.weightx = 5; // définit le nombre de cellules du composant
	gbc.weighty = 4;
	gbc.gridwidth = GridBagConstraints.RELATIVE ; // avant dernier composant de sa ligne
	gbc.fill = GridBagConstraints.BOTH;
	gbc.anchor = GridBagConstraints.LINE_START;
	f.add(PJeD, gbc) ;
	PJeD.addMouseListener(GAJe); 
	
	// Positionnement du plateau Joueur
	gbc.gridx = 1; // définit la position 0, 1 en dessous
	gbc.gridy = 3;
	gbc.gridheight = GridBagConstraints.REMAINDER; // dernier composant de sa colonne
	gbc.gridwidth = GridBagConstraints.RELATIVE ; // avant dernier composant de sa ligne
	gbc.weightx = 1; // définit le nombre de cellules du composant
	gbc.weighty = 2;
	gbc.fill = GridBagConstraints.BOTH;
	gbc.anchor = GridBagConstraints.PAGE_END; // aligné avec le panneau ressources
	f.add(PJoD, gbc); 
	PJoD.addMouseListener(GAJo);
	
	// Positionnement du plateau des ressources
	gbc.gridx = 2; // définit la position 0, 1 en dessous
	gbc.gridy = 1;
	gbc.gridwidth = GridBagConstraints.REMAINDER ;  // dernier de sa ligne et de sa colonne
	gbc.gridheight = GridBagConstraints.REMAINDER ; // dernier de sa ligne et de sa colonne
	gbc.weightx = 2; 
	gbc.weighty = 1;
	gbc.anchor = GridBagConstraints.LAST_LINE_END; 
	f.add(PReD, gbc);  
	PReD.addMouseListener(GARe); 
	
	//Ajout de la barre des menus et de ses composants
	Menu m = new Menu("File");
	MenuItem NP = new MenuItem("Nouvelle Partie");
	NP.addActionListener(new GestionMenu(1));
	m.add(NP);
	MenuItem Save = new MenuItem("Sauvegarder");
	Save.addActionListener(new GestionMenu(2));
	m.add(Save);
	MenuItem Load = new MenuItem("Charger Partie");
	Load.addActionListener(new GestionMenu(3));
	m.add(Load);
	MenuItem Quit = new MenuItem("Quitter");
	Quit.addActionListener(new GestionMenu(4));
	m.add(Quit);
	Menu m1 = new Menu("View");
	MenuItem Regles = new MenuItem("Règles");
	// Le menu GestionMenu servira dans une version ultérieure a gerer ces actions
	Regles.addActionListener(new GestionMenu(5));
	m1.add(Regles);
	MenuBar mb = new MenuBar();
	mb.add(m);
	mb.add(m1);
	f.setMenuBar(mb); 
	

// Ajout d'un WindowListener pour fermetyre par la croix rouge
	f.addWindowListener(new WindowAdapter() {
	public void windowClosing(WindowEvent event) {
		System.exit(0);
    }}); 
	
	f.pack(); 
	f.setVisible(true);
	f.resize(LG, HT);
	f.show(); 
	
	}

}