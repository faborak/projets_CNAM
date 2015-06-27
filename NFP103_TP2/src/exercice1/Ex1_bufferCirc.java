package exercice1;


public class Ex1_bufferCirc {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// On récupère la capacite et le maxtime en entrée du programme
		int capacite = Integer.parseInt(args[0]);
		int MaxTime = Integer.parseInt(args[1]);

		try {

			// Création du tampon unique
			BufferCirc Tampon = new BufferCirc(capacite, MaxTime);

			// création de Producteurs synchronysés
			for (int i = 0; i < 6; i++) {
			Producer Producteur = new Producer(Tampon);
			Producteur.start();
			}

			// Créatino de Consommateurs synchronisés
			for (int i = 0; i < 6; i++) {
				Consumer Consommateur = new Consumer(Tampon);
				Consommateur.start();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
