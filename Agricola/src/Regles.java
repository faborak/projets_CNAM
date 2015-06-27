import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/*******************************************************************
/** Role du fichier   : Ouvre la fenêtre de regles
/** Ateur             : Fabrice Lecomte        					
/** Date de creation  : 10/06/2014
/**     Commentaire   : Work in progress
/**     Modifications :        					
/*********************************************************************/

public class Regles extends Frame {

	private static final long serialVersionUID = 1L;
	private final static int LG = 1080;
	private final static int HT = 800;
	private final static Color lavande = new Color( 150, 131, 236 );	
	private final static String ResumeRegles= "Bienvenue dans Agricola, au 15eme siecle.";
	
	/*En bas à gauche de l'écran, les carrés représentent les emplacements de votre propriété.
	Les deux blocs marrons représentent deux maisons en bois, les deux cercles noirs sont les deux habitants de votre ferme.
	Les emplacements verts restants sont inutilisés.
	
	Au milieu, les rectangles verts représentent les actions possibles. Chaque tour, vos deux villageois, les cercles noirs, peuvent aller faire deux actions,
	comme ramasser du bois, aller à la pêche ou construire une nouvelle cabane.
	
	Chaque tour qui passe déclenche un evènement : une nouvelle action est possible.
	
	A la fin de certains tours surviennent les récoltes : il faut alors nourrir sa famille.
	
	Au bout du 14ème tour, après les dernières récoltes, le score est décompté.
	
	Dans cette version en cours de développement, il n'y a pas de cartes : pas d'aménagement, mineur ou majeur, ni de savoir-faire.
	Certaines valeurs ont été relevées en conséquence.
	
	*/
public static void main (String[] args) { 
	Regles FenetreRegles = new Regles();
	FenetreRegles.init(); }
	
public void init(){	
	Frame f = new Frame();
	f.setTitle("Projet VARI : Regles d'Agricola");
	f.setSize(HT, LG);
	f.setBackground(lavande);	
	Panel textareaPanel = new Panel(new FlowLayout(FlowLayout.LEFT));
	Label label1 = new Label("textarea");
	textareaPanel.add(label1);
	TextArea Text1 = new TextArea(ResumeRegles, 500, 100); // Texte, lignes, colonnes
	textareaPanel.add(Text1);
	
	Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER));
	Button okButton = new Button("OK");
	okButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	dispose();
	        }
	    }
	);
	buttonPanel.add(okButton);
	f.setLayout(new GridLayout(0, 1, 2, 2));
	
	f.add(textareaPanel);
	f.add(buttonPanel);
	
	f.addWindowListener(new WindowAdapter() {
	public void windowClosing(WindowEvent event) {
		System.exit(0);
    }});
	
	f.pack();
	f.setVisible(true);
//	f.resize(LG, HT);
//	f.show();

}

}