package Exercice3;

public class Compte {
	private int solde = 0;

	synchronized public void ajouter(int somme) {
		solde += somme;
		System.out.println("ajoute " + somme);
	}

	synchronized public void retirer(int somme) {
		solde -= somme;
		System.out.println("retire " + somme);
	}

	synchronized public void operationNulle(int somme) {
		solde += somme;
		System.out.println("ajout " + somme);
		solde -= somme;
		System.out.println(" retire " + somme);
	}

	synchronized public int getSolde() {
		return solde;
	}

}
