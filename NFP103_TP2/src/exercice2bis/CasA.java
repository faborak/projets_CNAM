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
		M_Lect = new Semaphore(1); // Mutex qui prot�ge la variable NbLecteur
		M_Red = new Semaphore(1); // Sempahore qui bloque les autres r�dacteurs
									// si un r�dacteur �crit, ou si une �tape de
									// lecture a commenc�
		Red = new Semaphore(1); // S�maphore acquiert le verrou pour les
								// lecteurs
	}

	public void write(String s) throws InterruptedException {

		// initialiser la lecture
		M_Red.acquire(); // bloquer les autres r�dacteurs
		Red.acquire(); // prendre le verrou de lectrure
		try {

			Buffer.add(s + "" + Buffer.size());

		} catch (Exception e) {
			e.getMessage();
		} finally {

			// Les deux verrous sont rel�ch�s, les lecteurs et les autres
			// r�dacteurs sont lib�r�s
			M_Red.release();
			Red.release();
		}
	}

	public String read() throws InterruptedException {

		String Temp = "";

		try {

			// D�but de lecture
			M_Red.acquire(); // on bloque les r�dacteurs

			// On prend le mutex sur nbLecteur pour incr�menter et tester la
			// valeur
			M_Lect.acquire();
			nbLecteur++;
			if (nbLecteur == 1) {
				Red.acquire();// le premier lecteur acquiert le verrou de
				// lecture, et le verrou de lecture sera maintenu jusqu'� la
				// sortie du dernier lecteur, plus bas (test nbLecteur == 0)
				// Les lecteurs ont donc priorit�.
			}
			M_Lect.release();

			// On rel�che le verrou des r�dacteurs (qui sont bloqu�s par le
			// verrou de lecture Red)
			M_Red.release();

			// d�but de la lecture
			Temp = Buffer.get(Buffer.size() - 1);

			// fin de la lecture
			M_Lect.acquire();
			nbLecteur--;

			if (nbLecteur == 0) {
				Red.release(); // Relachement du verrou si je suis le dernier
								// lecteur, un r�dacteur a une chance de
								// l'acqu�rir
			}
			M_Lect.release();

		} catch (Exception e) {
			e.getMessage();
		}
		return Temp;
	}
}
