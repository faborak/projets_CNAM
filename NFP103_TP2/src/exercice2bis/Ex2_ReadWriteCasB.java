package exercice2bis;

public class Ex2_ReadWriteCasB {

	public static void main(String[] args) {

		try {
			// Cas B : Priorité aux rédacteurs. Dès qu'un rédacteur demande
			// l'accès au tableau, il doit l'obtenir sans réquisition.
			CasB Tampon = new CasB();

			for (int i = 0; i < 2; i++) {
				WriterB Ecrivain = new WriterB(Tampon, "" + (char) ('A' + i));
				Ecrivain.start();
			}

			for (int i = 0; i < 6; i++) {
				ReaderB Lecteur = new ReaderB(Tampon);
				Lecteur.start();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
