package exercice2bis;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class CasC {
	private ArrayList<String> Buffer;

	private int NbLect;
	Semaphore M_Lect;
	Semaphore M_FIFO;
	Semaphore Fichier;

	public CasC() {
		NbLect = 0;
		Buffer = new ArrayList<String>();
		M_Lect = new Semaphore(1); // Mutex qui protège la variable NbLecteur
		M_FIFO = new Semaphore(1); // Sempahore qui protège l'entrée en file
									// d'attente
		Fichier = new Semaphore(1); // Sémaphore qui restreint l'accès au
									// tableau Buffer
	}

	public void write(String s) throws InterruptedException {

		// initialiser la lecture

		M_FIFO.acquire(); // bloquer les autres rédacteurs + lecteurs (priorité
							// égale)
		Fichier.acquire(); // une fois FIFO bloqué, j'attends que les lecteurs
							// en cours sortent, le dernier relache le verrour
							// Fichier

		try {

			Buffer.add(s + "" + Buffer.size());

		} catch (Exception e) {
			e.getMessage();
		} finally {

			// Les deux verrous sont relâchés, les lecteurs et les autres
			// rédacteurs sont libérés

			Fichier.release();
			M_FIFO.release(); // On permet à un lecteur ou un rédacteur
								// d'acquérir le verrou

		}
	}

	public String read() throws InterruptedException {

		String Temp = "";

		try {
			M_FIFO.acquire(); // bloquer les rédacteurs et les autres lecteurs
								// (priorité d'accès égale)

			M_Lect.acquire();
			NbLect++;
			if (NbLect == 1) {
				// si premier lecteur, je bloque l'accès au fichier
				Fichier.acquire();
			}
			M_Lect.release();

			// Un autre lecteur ou un autre rédacteur peuvent prendre le verrou
			// en priorité égale
			M_FIFO.release();

			// Début de lecture
			Temp = Buffer.get(Buffer.size() - 1);

			M_Lect.acquire();
			NbLect--;
			if (NbLect == 0) {
				// Le dernier lecteur relache ce verrou, si un rédacteur a
				// acquis FIFO, il acquert celui-là et commence à écrire
				Fichier.release();
			}
			M_Lect.release();

		} catch (Exception e) {
			e.getMessage();
		}
		return Temp;
	}
}
