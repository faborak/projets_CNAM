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
	// Le verrou en �criture
	Lock writeLock = rwl.writeLock();
	// Le verrou en lecture
	Lock readLock = rwl.readLock();
	// Condition qui permet � un r�dacteur d'attendre si un lecteur est pr�sent
	private final Lock lock = new ReentrantLock();
	private final Condition LecteurPresent = lock.newCondition();
	// Condition First In Firts Out pour �galit� de priorit�
	private final Lock FIFO = new ReentrantLock();

	public CasC() {
		NbLect = 0;
		Buffer = new ArrayList<String>();
	}

	synchronized public void write(String s) throws InterruptedException {
// Egalit� de priorit� entre lecteurs et r�dacteurs
		FIFO.lock();
		
		// S'il y a des lecteurs, mise en attente
		if (NbLect != 0) {
			IsWaiting = true;
			LecteurPresent.await();
			IsWaiting = false;
		}
		
		// Une fois r�veill�, on prend normalement le verrou de lecture
		writeLock.lock();
		try {

			Buffer.add(s + "" + Buffer.size());
					
		} catch (Exception e) {
			e.getMessage();
		} finally {
			// signaler fin d'�criture et lib�rer le verrou de lecture
			writeLock.unlock();
			FIFO.unlock();
			
			// r�veiller un et un seul des processus en attente 
			// un boucle for de i = 1 � nbLect serait envisageable
			notify();
		}
	}

	synchronized public String read() throws InterruptedException {
		
		// Egalit� de priorit� entre lecteurs et r�dacteurs
		FIFO.lock();
		
		String Temp = "";
		
		try {
			// un r�dacteur est en attente : tous les lecteurs qui arrivent se mettent en attente
			if (IsWaiting == true) {
				wait();
			}

			// Sinon, le verrou de lecture est pris par le premier lecteurs pour l'ensemble des lecteurs
			NbLect++;
			if (NbLect == 1) {
				readLock.lock();
			} 
			
			// On rel�che le verrour FIFO pour permettre � un r�dacteur ou un lecteur de le prendre
			FIFO.unlock();
			
			Temp = Buffer.get(Buffer.size() - 1);
			
			NbLect--;

			// Si des lecteurs sont en attente, on compte combien il en reste pour lib�rer le verrou au dernier sortant
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
