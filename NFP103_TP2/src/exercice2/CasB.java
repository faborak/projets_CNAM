package exercice2;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CasB {
	private ArrayList<String> Buffer;

	// private final ReentrantReadWriteLock RWlock = new
	// ReentrantReadWriteLock();
	ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	// Le verrou en �criture
	Lock writeLock = rwl.writeLock();
	// Le verrou en lecture
	Lock readLock = rwl.readLock();

	public CasB() {
		Buffer = new ArrayList<String>();
	}

	synchronized public void write(String s) throws InterruptedException {

		// le verrou writeLock donne naturellement la priorit� aux r�dacteurs
		writeLock.lock();
		try {
			// on �crit un chiffre de la valeur de la taille du Buffer
			Buffer.add(s + "" + Buffer.size());

		} catch (Exception e) {
			e.getMessage();
		} finally {
			writeLock.unlock();
		}
	}

	synchronized public String read() throws InterruptedException {

		String Temp = "";
		// le verrou readLock n'est pas prioritaire sur le writelock, il n'y a
		// pas d'impl�mentation suppl�mentaire � faire
		readLock.lock();
		try {

			Temp = Buffer.get(Buffer.size() - 1);

		} catch (Exception e) {
			e.getMessage();
		} finally {
			readLock.unlock();
		}
		return Temp;
	}
}
