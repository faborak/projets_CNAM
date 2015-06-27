package Exercice3;

import java.util.concurrent.Semaphore;

public class Operation extends Thread {
	private Compte compte;
	Semaphore sem;

	public Operation(String nom, Compte compte, Semaphore sem) {
		super(nom);
		this.compte = compte;
		this.sem = sem;
	}

	public void run() {
		while (true) {
			try {
			int i = (int) (Math.random() * 100000);
			String nom = getName();
			System.out.println(nom);
				sem.acquire();
				try {
					compte.ajouter(i);
					compte.retirer(i);
					// compte.operationNulle(i);
					int solde = compte.getSolde();
					System.out.println(nom);
					if (solde != 0) {
						System.out.println(nom + ":**solde=" + solde);
						System.exit(1);
					}
				} finally {
					sem.release();
				}
			} catch (InterruptedException ie) {
				// ...
			}
		}
	}

	public static void main(String[] args) {
		Compte compte = new Compte();
		Semaphore sem = new Semaphore(1);
		
		for (int i = 0; i < 20; i++) {
			Operation operation = new Operation("" + (char) ('A' + i), compte, sem);
			operation.start();
			// try {
			// operation.join();
			// } catch (InterruptedException e) {
			// e.printStackTrace();
			// }
		}
	}

}
