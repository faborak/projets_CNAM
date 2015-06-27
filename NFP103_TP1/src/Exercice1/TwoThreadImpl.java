package Exercice1;

public class TwoThreadImpl {

//	private boolean actif = true;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// n = Integer.parseInt(args[0]);
		int n = 5;
		for (int i = 1; i < n + 1; i++) {
			try {
				TwoThread t = new TwoThread(i);
				t.start();
//				System.in.read();
				t.join();
				t.stop();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

//	public void arreter() {
//		actif = false;
//	}
}
