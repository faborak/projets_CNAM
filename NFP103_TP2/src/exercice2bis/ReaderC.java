package exercice2bis;

public class ReaderC extends Thread {

	private CasC buffer;	
	int lower = 1;
	int higher = 500;

	public ReaderC(CasC Tampon) {
		super();
		this.buffer = Tampon;
	}

	public void run() {
		while (true) {
			int n = (int) (Math.random() * (higher - lower)) + lower;
			try {

				Object Temp = buffer.read();
				if (Temp != "") {
					System.out.println(Temp);
				}				

				currentThread().sleep(n);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
