package exercice2;

public class WriterA extends Thread {

	private CasA buffer;	
	int lower = 1;
	int higher = 5000;
	String nom;

	public WriterA(CasA Tampon, String nom) {
		super();
		this.buffer = Tampon;
		this.nom = nom;
	}

	// méthode de production en boucle, avec attente aléatoire entre chaque
	// itération
	public void run() {
		while (true) {
			int n = (int) (Math.random() * (higher - lower)) + lower;
			try {
				
				buffer.write(nom);

				currentThread().sleep(n);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
