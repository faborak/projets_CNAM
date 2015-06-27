package exercice2;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CasA {
	private ArrayList<String> Buffer;

	private int nbLecteur;

	// private final ReentrantReadWriteLock RWlock = new
	// ReentrantReadWriteLock();
	private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	// Le verrou en écriture
	private Lock writeLock = rwl.writeLock();
	// Le verrou en lecture
	private Lock readLock = rwl.readLock();

	public CasA() {
		nbLecteur = 0;
		Buffer = new ArrayList<String>();
	}

	synchronized public void write(String s) throws InterruptedException {

		// on prend le verrou d'écriture (on verouille donc aussi la lecture)
		writeLock.lock();
		try {

			Buffer.add(s + "" + Buffer.size());

		} catch (Exception e) {
			e.getMessage();
		} finally {
			// Quoi qu'il arrive, on rend le verrou de lecture
			writeLock.unlock();
		}
	}

	synchronized public String read() throws InterruptedException {

		String Temp = "";

		try {
			nbLecteur++;
			if (nbLecteur == 1) {
				// On ne prend le verrou de lecture que si on est le premier
				// lecteur
				readLock.lock();
			}

			Temp = Buffer.get(Buffer.size() - 1);

			nbLecteur--;
			if (nbLecteur == 0) {
				// On libère le verrou si on est le dernier lecteur
				readLock.unlock();
			}

		} catch (Exception e) {
			e.getMessage();
		}
		return Temp;
	}
}
