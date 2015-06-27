package exercice2bis;

public class Ex2_ReadWriteCasB {

	public static void main(String[] args) {

		try {
			// Cas B : Priorit� aux r�dacteurs. D�s qu'un r�dacteur demande
			// l'acc�s au tableau, il doit l'obtenir sans r�quisition.
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
