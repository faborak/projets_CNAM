package Exercice2;

import Exercice1.TwoThread;

public class MonThreadImpl {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i = 1; i < 5 + 1; i++) {
			try {
				MonThread t = new MonThread(i);
				t.start();
//				t.join();
//				t.stop();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
