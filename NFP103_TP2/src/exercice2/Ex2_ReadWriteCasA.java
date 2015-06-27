package exercice2;

public class Ex2_ReadWriteCasA {

	public static void main(String[] args) {

		try {

			// Cas A : Priorité aux lecteurs. Si un nouveau lecteur arrive alors
			// que nblecteur !=0, le nouveau lecteur peut consulter le tableau
			CasA Tampon = new CasA();

			for (int i = 0; i < 2; i++) {
				WriterA Ecrivain = new WriterA(Tampon, "" + (char) ('A' + i));
				Ecrivain.start();
			}

			for (int i = 0; i < 6; i++) {
				ReaderA Lecteur = new ReaderA(Tampon);
				Lecteur.start();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
