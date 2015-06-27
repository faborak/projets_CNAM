/*******************************************************************
/** Role du fichier   : Connecteur de l'application
/** Ateur             : Fabrice Lecomte        					
/** Date de creation  : 10/06/2014  
/**     Modifications :        					
/*********************************************************************/

public class Score{
	
//références croisées
	public Agricola jeu;
	public PlateauJoueurD PJoD;
	public PlateauRessourcesD PReD;
	public PionJoueur Pion;
	public AideD AiD;
	
// calcul du score	
	private int CaseVide = 0;
	private int MaisonArgile = 0;
	private int MaisonPierre = 0;
	// private int Etable = 0; variable utilisee en version complete
	private int Champ = 0;
	private int Paturage = 0;
	private int Cereale = 0;
	private int Legume = 0;
	private int Mouton = 0;
	private int Sanglier = 0;
	private int Boeuf = 0;
	private int ScoreFinal = 0;
	
	String TexteAide = "";
	
	
	public Score(PlateauJoueurD PJoD, PionJoueur Pion, PlateauRessourcesD PReD, AideD AiD){
		this.PJoD=PJoD;
		this.Pion = Pion;
		this.PReD = PReD;
		this.AiD = AiD;
		
		 System.out.println("					Fin de partie!			 ");
		 System.out.println("					Rappel des scores		 ");
		 System.out.println("			-1		1		2		3		4");
		 System.out.println("Champ		0-1		2		3		4		5+");
		 System.out.println("Paturage	0		1		2		3		4+");
		 System.out.println("Cereale	0		1-3		4-5		6-7		8+");
		 System.out.println("Legume		0		1		2		3		4+");
		 System.out.println("Mouton		0		1-3		4-5		6-7		8+");
		 System.out.println("Sanglier	0		1-2		3-4		5-6		7+");
		 System.out.println("Boeuf		0		1		2-3		4-5		6+");
		 System.out.println("-1 point par case de cour inutilisée		 ");
		 System.out.println("1 point par etable	clotûrée 				 ");
		 System.out.println("1 point par piece en argile				 ");
		 System.out.println("2 points par piece en pierre				 ");
		 System.out.println("3 points par membre de la famille			 ");
		 
			for (int i = 1; i < 6; i++) {
				for (int j = 1; j < 4; j++) {
					switch (PJoD.Plateau[i][j]){
					case 0 : CaseVide++;
					break;
					case 2 : MaisonArgile++;
					break;
					case 3 : MaisonPierre++;
					break;
					case 4 : Champ++;
					break;
					case 5 : Paturage++;
					break;
			}}}
			Cereale = PReD.GetRessource(5);
			Legume = PReD.GetRessource(6);
			Mouton = PReD.GetRessource(7);
			Sanglier = PReD.GetRessource(8);
			Boeuf = PReD.GetRessource(9);
	
	// Calcul du score
	
	ScoreFinal = ScoreFinal + MaisonArgile +(2*MaisonPierre) - CaseVide;
	// Calcul des Cereale
	
	if (Champ==0||Champ==1){ScoreFinal = ScoreFinal -1;}
	else {if (Champ ==2){ScoreFinal = ScoreFinal +1;}
	else {if (Champ ==3){ScoreFinal = ScoreFinal +2;}
	else {if (Champ ==4){ScoreFinal = ScoreFinal +3;}
	else {if (Champ >4){ScoreFinal = ScoreFinal +4;}}}}}
	
	if (Paturage ==0){ScoreFinal = ScoreFinal -1;}
	else {if (Paturage ==1){ScoreFinal = ScoreFinal +1;}
	else {if (Paturage ==2){ScoreFinal = ScoreFinal +2;}
	else {if (Paturage ==3){ScoreFinal = ScoreFinal +3;}
	else {if (Paturage >4){ScoreFinal = ScoreFinal +4;}}}}}
	
	if (Cereale ==0){ScoreFinal = ScoreFinal -1;}
	else {if (Cereale>0&&Cereale<4){ScoreFinal = ScoreFinal +1;}
	else {if (Cereale ==4 || Cereale == 5){ScoreFinal = ScoreFinal +2;}
	else {if (Cereale ==6 || Cereale == 7){ScoreFinal = ScoreFinal +3;}
	else {if (Cereale >7){ScoreFinal = ScoreFinal +4;}}}}}
	
	if (Legume ==0){ScoreFinal = ScoreFinal -1;}
	else {if (Legume ==1){ScoreFinal = ScoreFinal +2;}
	else {if (Legume ==2){ScoreFinal = ScoreFinal +2;}
	else {if (Legume ==3){ScoreFinal = ScoreFinal +3;}
	else {if (Legume >3){ScoreFinal = ScoreFinal +4;}}}}}
	
	if (Mouton ==0){ScoreFinal = ScoreFinal -1;}
	else {if (Mouton>0&&Mouton<4){ScoreFinal = ScoreFinal +1;}
	else {if (Mouton ==4 || Mouton == 5){ScoreFinal = ScoreFinal +2;}
	else {if (Mouton ==6 || Mouton == 7){ScoreFinal = ScoreFinal +3;}
	else {if (Mouton >7){ScoreFinal = ScoreFinal +4;}}}}}
	
	if (Sanglier ==0){ScoreFinal = ScoreFinal -1;}
	else {if (Sanglier>0&&Sanglier<4){ScoreFinal = ScoreFinal +1;}
	else {if (Sanglier ==4 || Sanglier == 5){ScoreFinal = ScoreFinal +2;}
	else {if (Sanglier ==6 || Sanglier == 7){ScoreFinal = ScoreFinal +3;}
	else {if (Sanglier >7){ScoreFinal = ScoreFinal +4;}}}}}
	
	if (Boeuf ==0){ScoreFinal = ScoreFinal -1;}
	else {if (Boeuf>0&&Boeuf<4){ScoreFinal = ScoreFinal +1;}
	else {if (Boeuf ==4 || Boeuf == 5){ScoreFinal = ScoreFinal +2;}
	else {if (Boeuf ==6 || Boeuf == 7){ScoreFinal = ScoreFinal +3;}
	else {if (Boeuf >7){ScoreFinal = ScoreFinal +4;}}}}}
	}
	
	public void calcul(){
	    TexteAide = "Fin de partie ! Le score final dépend de la taille de la propriété, des animaux et légumes.";
	    AiD.nouveau(TexteAide);
	    TexteAide = "Vous perdez des points si vous avez des points de mendicité, ou des cases inutilisées. Un score de 25 est acceptable.";
	    AiD.nouveau(TexteAide);
	    TexteAide = "Score final : " + ScoreFinal;
	    AiD.nouveau(TexteAide);
	}
	
}