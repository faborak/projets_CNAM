package exercice2bis;

public class Ex2_ReadWriteCasC {

	public static void main(String[] args) {

		try {

			// Cas C : Quand un r�dacteur arrive, il se met en attente jusqu'�
			// ce que nblecteur == 0, tous les lecteurs arrivant se mettent en
			// attente. Le dernier lecteur reveille le r�dacetur, le r�dacteur
			// r�veille ensuite les lecteurs.
			CasC Tampon = new CasC();

			for (int i = 0; i < 2; i++) {
				WriterC Ecrivain = new WriterC(Tampon, "" + (char) ('A' + i));
				Ecrivain.start();
			}

			for (int i = 0; i < 6; i++) {
				ReaderC Lecteur = new ReaderC(Tampon);
				Lecteur.start();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
