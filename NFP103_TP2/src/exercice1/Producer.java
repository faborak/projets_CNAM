package exercice1;

import java.util.Date;

public class Producer extends Thread {

	private BufferCirc buffer;
	int lower = 1;
	int higher = 5000;

	public Producer(BufferCirc Tampon) {
		super();
		this.buffer = Tampon;
	}

	// méthode de production en boucle, avec attente aléatoire entre chaque itération
	public void run() {
		while (true) {
			int n = (int) (Math.random() * (higher - lower)) + lower;
			try {
				Date maDate = new Date(); 
				buffer.produce(maDate);
				
				// attente aléatoire
				currentThread().sleep(n);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
