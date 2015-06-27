package exercice1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BufferCirc {
	private Object Buffer[];
	private int capacity;
	private int IndexProd, IndexCons, NonLus;
	private int MaxTime;

	private final Lock lock = new ReentrantLock();
	private final Condition BufferPlein = lock.newCondition();
	private final Condition BufferVide = lock.newCondition();

	public BufferCirc(int capacity, int MaxTime) {
		Buffer = new Object[capacity];
		this.capacity = capacity;
		IndexProd = 0;
		IndexCons = 0;
		NonLus = 0;
		this.MaxTime = MaxTime;
	}

	public void produce(Object obj) throws InterruptedException {
		// On prend le verrou
		lock.lock();
		try {

			// Si le Buffer est plein, attendre
			if (NonLus == capacity) {
				BufferPlein.await();
				lock.unlock();

				// Sinon, ajouter la date Ã  l'IndexProd courant
			} else {

				NonLus++;
				Buffer[IndexProd] = obj;

				IndexProd++;
				if (IndexProd == capacity) {
					IndexProd = 0;
				}

				// Enoncé : réveiller consommateurs + producteurs endormis
				BufferVide.signal();
				BufferPlein.signal();
			}
		} finally {
			// Quoiqu'il arrive, on relache le verrou
			lock.unlock();
		}
	}

	public Object consume() throws InterruptedException {
		// On prend le verrou
		lock.lock();
		Object Temp = null;

		try {
			if (NonLus == 0) {
				// on attend un temps max dépendant de MaxTime
				long n = (long) (Math.random() * (MaxTime));
				BufferVide.await(n, TimeUnit.MILLISECONDS);
			} else {

				// Sinon, on procède Ã  une lecture normal
				NonLus--;
				Temp = Buffer[IndexCons];
				Buffer[IndexCons] = null;

				IndexCons++;
				if (IndexCons == capacity) {
					IndexCons = 0;
				}
			}

			// On réveille les producteurs
			BufferPlein.signal();
		} finally {
			// Quoiqu'il arrive, on relÃ¢che le verrou
			lock.unlock();
		}
		return Temp;
	}
}
