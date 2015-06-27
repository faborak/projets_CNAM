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
		M_Lect = new Semaphore(1); // Mutex qui prot�ge la variable NbLecteur
		M_nbRed = new Semaphore(1); // Mutex qui prot�ge la variable nbRed
		M_Red = new Semaphore(0); // Sempahore initialis� � 0 : met les lecteurs
									// en attente si un r�dacteur est pr�sent
		Red = new Semaphore(1); // S�maphore qui acquiert le verrou sur le
								// fichier de lecture
	}

	public void write(String s) throws InterruptedException {

		// Un r�dacteur est ajout� en file
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
			// Rel�chement du verrou sur le fichier
			Red.release();

			// D�cr�menter le nombre de r�dacteurs
			M_nbRed.acquire();
			nbRed--;
			M_nbRed.release();

			// Rel�cher l'ensemble des lecteurs en attente
			for (int i = 1; i == nbLectEnAtt; i++) {
				M_Red.release();
			}
		}
	}

	public String read() throws InterruptedException {

		String Temp = "";

		try {

			// si il y a un r�dacteur en attente
			M_nbRed.acquire();
			if (nbRed != 0) {
				nbLectEnAtt++;
				// S�maphore initialis� � 0 : le lecteur est mis en attente
				M_Red.acquire();
				M_nbRed.release();
			} else {
				// il faut rel�cher ce mutex quoi qu'il arrive
				M_nbRed.release();
			}

			// acquisition du mutex et incr�mentation du nombr de lecteurs
			M_Lect.acquire();
			nbLecteur++;
			if (nbLecteur == 1) {
				Red.acquire();// le premier lecteur acquiert le verrou de
				// sur le fichier, le r�dacteur en attente doit attendre la
				// sortie du dernier lecteur.
			}
			M_Lect.release();

			// d�but de la lecture
			Temp = Buffer.get(Buffer.size() - 1);

			// fin de la lecture
			M_Lect.acquire();
			nbLecteur--;

			if (nbLecteur == 0) {
				Red.release(); // Relachement du verrou si je suis le dernier
								// lecteur, un r�dacteur l'acquiert s'il �tait en attente
			}
			M_Lect.release();

		} catch (Exception e) {
			e.getMessage();
		}
		return Temp;
	}
}
