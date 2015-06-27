package exercice3;

import java.util.Scanner;

/**
 * creation de matrices par des tableaux à 2 dimensions
 */
public class Ex3_CalculDeMatrices {

	/**
	 * crée et renvoie la matrice somme des deux matrices 'mat1' et 'mat2'.
	 */
	public static int[][] produit(int[][] mat1, int[][] mat2) {

		// creation e la matrice produit depuis les deux autres matrices
		int n = mat1.length;
		int m = (n > 0) ? mat1[0].length : 0;
		int n2 = mat2.length;
		int p = (n2 > 0) ? mat2[0].length : 0;
		int[][] mat = new int[n][p];

		// creation des Threads
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < p; j++) {
				mat[i][j] = 0;

				// Creation d'un Thread avec les valeurs i, j et k
				ThreadDeCalcul Calcul = new ThreadDeCalcul(i, j, m, mat, mat1,
						mat2);
				// Pas de Calcul.join() : pas d'attente entre les processus
				// Tous les processus peuvent s'executer en parallèle
				Calcul.start();
			}
		}
		return mat;
	}

	/**
	 * crée et renvoie une matrice de dimensions 'n' et 'm'
	 */
	public static int[][] creerMatrice(int n, int m, Scanner sc,
			boolean CreationMatriceRandom) {
		int[][] mat = new int[n][m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)

				// Cree la matrice de facon aleatoire,
				if (CreationMatriceRandom == true) {
					mat[i][j] = (int) Math.floor(20 * Math.random());
				} else {
					// ou valeur par valeur comme demande dans l'enonce
					System.out.println("valeur de " + i + ", " + j + " : ");
					mat[i][j] = sc.nextInt();
				}
		return mat;
	}

	/** Méthode pour affichage sur le sysout */
	public static String toString(int[][] mat) {

		// Renvoie chaque valeur de la matrice en parcourant le tableau
		String s = "";
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++)
				s += "  " + mat[i][j];
			s += "\n";
		}
		return s;
	}

	/**
	 * programme de produits de matrices.
	 * Ne fonctionne pas pour
	 */
	public static void main(String[] args) {

		// Proposition de cration aleatoire de matrices
		Scanner sc = new Scanner(System.in);
		System.out.println("Creation de matrices aléatoire ? o/n");
		char Reponse = sc.next().charAt(0);
		boolean CreationMatriceRandom = false;
		if (Reponse == 'o' || Reponse == 'O') {
			CreationMatriceRandom = true;
		}

		// Création de la premiere matrice
		System.out.println("nombre de lignes de la premiere matrice : ");
		// ne fonctionne pas pour m = 1.
		int m = sc.nextInt();
		System.out.println("nombre de colonnes de la premiere matrice : ");
		int n = sc.nextInt();
		if (CreationMatriceRandom == false) {
			System.out.println("Création de la premiere matrice :");
		}
		int[][] mat1 = creerMatrice(m, n, sc, CreationMatriceRandom);

		// Creation de la seconde matrice : on ne peut définir que le nombre p
		// de colonnes
		System.out
				.println("nombre de colonnes de la 2ème matrice pour le produit : ");
		int p = sc.nextInt();
		if (CreationMatriceRandom == false) {
			System.out.println("Création de la deuxieme matrice :");
		}
		int[][] mat2 = creerMatrice(m, p, sc, CreationMatriceRandom);

		sc.close();

		// Affichage des matrices creees
		System.out.println("\nmat1=\n" + toString(mat1) + "\nmat2=\n"
				+ toString(mat2));

		// Affichage du prduit des matrices
		System.out.println("mat1*mat2=\n" + toString(produit(mat1, mat2)));
	}
}