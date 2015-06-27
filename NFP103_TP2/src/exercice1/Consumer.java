package exercice1;

public class Consumer extends Thread {

	private BufferCirc buffer;
	int lower = 1;
	int higher = 500;

	public Consumer(BufferCirc Tampon) { // // à compléter
		super();
		this.buffer = Tampon;
	}

	public void run() {
		while (true) {
			int n = (int) (Math.random() * (higher - lower)) + lower;
			try {

				// si le consommateur est sorti sans rien lire, la méthode retourne null, ne rien afficher
				Object Temp = buffer.consume();
				if (Temp != null) {
					System.out.println(Temp);
				}

				// attente aléatoire
				currentThread().sleep(n);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
