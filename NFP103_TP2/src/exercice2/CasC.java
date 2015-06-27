package exercice2;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CasC {
	private ArrayList<String> Buffer;

	private int NbLect;
	private boolean IsWaiting = false;

	ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	// Le verrou en écriture
	Lock writeLock = rwl.writeLock();
	// Le verrou en lecture
	Lock readLock = rwl.readLock();
	// Condition qui permet à un rédacteur d'attendre si un lecteur est présent
	private final Lock lock = new ReentrantLock();
	private final Condition LecteurPresent = lock.newCondition();
	// Condition First In Firts Out pour égalité de priorité
	private final Lock FIFO = new ReentrantLock();

	public CasC() {
		NbLect = 0;
		Buffer = new ArrayList<String>();
	}

	synchronized public void write(String s) throws InterruptedException {
// Egalité de priorité entre lecteurs et rédacteurs
		FIFO.lock();
		
		// S'il y a des lecteurs, mise en attente
		if (NbLect != 0) {
			IsWaiting = true;
			LecteurPresent.await();
			IsWaiting = false;
		}
		
		// Une fois réveillé, on prend normalement le verrou de lecture
		writeLock.lock();
		try {

			Buffer.add(s + "" + Buffer.size());
					
		} catch (Exception e) {
			e.getMessage();
		} finally {
			// signaler fin d'écriture et libérer le verrou de lecture
			writeLock.unlock();
			FIFO.unlock();
			
			// réveiller un et un seul des processus en attente 
			// un boucle for de i = 1 à nbLect serait envisageable
			notify();
		}
	}

	synchronized public String read() throws InterruptedException {
		
		// Egalité de priorité entre lecteurs et rédacteurs
		FIFO.lock();
		
		String Temp = "";
		
		try {
			// un rédacteur est en attente : tous les lecteurs qui arrivent se mettent en attente
			if (IsWaiting == true) {
				wait();
			}

			// Sinon, le verrou de lecture est pris par le premier lecteurs pour l'ensemble des lecteurs
			NbLect++;
			if (NbLect == 1) {
				readLock.lock();
			} 
			
			// On relâche le verrour FIFO pour permettre à un rédacteur ou un lecteur de le prendre
			FIFO.unlock();
			
			Temp = Buffer.get(Buffer.size() - 1);
			
			NbLect--;

			// Si des lecteurs sont en attente, on compte combien il en reste pour libérer le verrou au dernier sortant
			if (NbLect == 0) {
				readLock.unlock();
			}

		} catch (Exception e) {
			e.getMessage();
		} finally {
			
		}
		return Temp;
	}
}
