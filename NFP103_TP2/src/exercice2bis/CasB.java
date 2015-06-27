package exercice2bis;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class CasB {
	private ArrayList<String> Buffer;

	private int nbLecteur, nbRed, nbLectEnAtt;
	Semaphore M_Lect;
	Semaphore M_Red;
	Semaphore Red;
	Semaphore M_nbRed;

	public CasB() {
		nbRed = 0;
		nbLecteur = 0;
		nbLectEnAtt = 0;
		Buffer = new ArrayList<String>();
		M_Lect = new Semaphore(1); // Mutex qui protège la variable NbLecteur
		M_nbRed = new Semaphore(1); // Mutex qui protège la variable nbRed
		M_Red = new Semaphore(0); // Sempahore initialisé à 0 : met les lecteurs
									// en attente si un rédacteur est présent
		Red = new Semaphore(1); // Sémaphore qui acquiert le verrou sur le
								// fichier de lecture
	}

	public void write(String s) throws InterruptedException {

		// Un rédacteur est ajouté en file
		M_nbRed.acquire();
		nbRed++;
		M_nbRed.release();

		// s'il reste au moins un lecteur, ce verrou n'est pas disponible
		Red.acquire();

		try {

			Buffer.add(s + "" + Buffer.size());

		} catch (Exception e) {
			e.getMessage();
		} finally {
			// Relâchement du verrou sur le fichier
			Red.release();

			// Décrémenter le nombre de rédacteurs
			M_nbRed.acquire();
			nbRed--;
			M_nbRed.release();

			// Relâcher l'ensemble des lecteurs en attente
			for (int i = 1; i == nbLectEnAtt; i++) {
				M_Red.release();
			}
		}
	}

	public String read() throws InterruptedException {

		String Temp = "";

		try {

			// si il y a un rédacteur en attente
			M_nbRed.acquire();
			if (nbRed != 0) {
				nbLectEnAtt++;
				// Sémaphore initialisé à 0 : le lecteur est mis en attente
				M_Red.acquire();
				M_nbRed.release();
			} else {
				// il faut relâcher ce mutex quoi qu'il arrive
				M_nbRed.release();
			}

			// acquisition du mutex et incrémentation du nombr de lecteurs
			M_Lect.acquire();
			nbLecteur++;
			if (nbLecteur == 1) {
				Red.acquire();// le premier lecteur acquiert le verrou de
				// sur le fichier, le rédacteur en attente doit attendre la
				// sortie du dernier lecteur.
			}
			M_Lect.release();

			// début de la lecture
			Temp = Buffer.get(Buffer.size() - 1);

			// fin de la lecture
			M_Lect.acquire();
			nbLecteur--;

			if (nbLecteur == 0) {
				Red.release(); // Relachement du verrou si je suis le dernier
								// lecteur, un rédacteur l'acquiert s'il était en attente
			}
			M_Lect.release();

		} catch (Exception e) {
			e.getMessage();
		}
		return Temp;
	}
}
