package exercice2bis;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class CasA {
	private ArrayList<String> Buffer;

	private int nbLecteur;
	Semaphore M_Lect;
	Semaphore M_Red;
	Semaphore Red;

	public CasA() {
		nbLecteur = 0;
		Buffer = new ArrayList<String>();
		M_Lect = new Semaphore(1); // Mutex qui protège la variable NbLecteur
		M_Red = new Semaphore(1); // Sempahore qui bloque les autres rédacteurs
									// si un rédacteur écrit, ou si une étape de
									// lecture a commencé
		Red = new Semaphore(1); // Sémaphore acquiert le verrou pour les
								// lecteurs
	}

	public void write(String s) throws InterruptedException {

		// initialiser la lecture
		M_Red.acquire(); // bloquer les autres rédacteurs
		Red.acquire(); // prendre le verrou de lectrure
		try {

			Buffer.add(s + "" + Buffer.size());

		} catch (Exception e) {
			e.getMessage();
		} finally {

			// Les deux verrous sont relâchés, les lecteurs et les autres
			// rédacteurs sont libérés
			M_Red.release();
			Red.release();
		}
	}

	public String read() throws InterruptedException {

		String Temp = "";

		try {

			// Début de lecture
			M_Red.acquire(); // on bloque les rédacteurs

			// On prend le mutex sur nbLecteur pour incrémenter et tester la
			// valeur
			M_Lect.acquire();
			nbLecteur++;
			if (nbLecteur == 1) {
				Red.acquire();// le premier lecteur acquiert le verrou de
				// lecture, et le verrou de lecture sera maintenu jusqu'à la
				// sortie du dernier lecteur, plus bas (test nbLecteur == 0)
				// Les lecteurs ont donc priorité.
			}
			M_Lect.release();

			// On relâche le verrou des rédacteurs (qui sont bloqués par le
			// verrou de lecture Red)
			M_Red.release();

			// début de la lecture
			Temp = Buffer.get(Buffer.size() - 1);

			// fin de la lecture
			M_Lect.acquire();
			nbLecteur--;

			if (nbLecteur == 0) {
				Red.release(); // Relachement du verrou si je suis le dernier
								// lecteur, un rédacteur a une chance de
								// l'acquérir
			}
			M_Lect.release();

		} catch (Exception e) {
			e.getMessage();
		}
		return Temp;
	}
}
