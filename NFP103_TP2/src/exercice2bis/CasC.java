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
		M_Lect = new Semaphore(1); // Mutex qui prot�ge la variable NbLecteur
		M_FIFO = new Semaphore(1); // Sempahore qui prot�ge l'entr�e en file
									// d'attente
		Fichier = new Semaphore(1); // S�maphore qui restreint l'acc�s au
									// tableau Buffer
	}

	public void write(String s) throws InterruptedException {

		// initialiser la lecture

		M_FIFO.acquire(); // bloquer les autres r�dacteurs + lecteurs (priorit�
							// �gale)
		Fichier.acquire(); // une fois FIFO bloqu�, j'attends que les lecteurs
							// en cours sortent, le dernier relache le verrour
							// Fichier

		try {

			Buffer.add(s + "" + Buffer.size());

		} catch (Exception e) {
			e.getMessage();
		} finally {

			// Les deux verrous sont rel�ch�s, les lecteurs et les autres
			// r�dacteurs sont lib�r�s

			Fichier.release();
			M_FIFO.release(); // On permet � un lecteur ou un r�dacteur
								// d'acqu�rir le verrou

		}
	}

	public String read() throws InterruptedException {

		String Temp = "";

		try {
			M_FIFO.acquire(); // bloquer les r�dacteurs et les autres lecteurs
								// (priorit� d'acc�s �gale)

			M_Lect.acquire();
			NbLect++;
			if (NbLect == 1) {
				// si premier lecteur, je bloque l'acc�s au fichier
				Fichier.acquire();
			}
			M_Lect.release();

			// Un autre lecteur ou un autre r�dacteur peuvent prendre le verrou
			// en priorit� �gale
			M_FIFO.release();

			// D�but de lecture
			Temp = Buffer.get(Buffer.size() - 1);

			M_Lect.acquire();
			NbLect--;
			if (NbLect == 0) {
				// Le dernier lecteur relache ce verrou, si un r�dacteur a
				// acquis FIFO, il acquert celui-l� et commence � �crire
				Fichier.release();
			}
			M_Lect.release();

		} catch (Exception e) {
			e.getMessage();
		}
		return Temp;
	}
}
