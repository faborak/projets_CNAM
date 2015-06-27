package Exercice2;

public class MonThread extends Thread{

	int numero;
	int lower = 1;
	int higher = 5000;
	
	synchronized public void run(){
		for (int i=1;i<11;i++){
			System.out.println("Thread numero "+numero+ " est en train de compter à "+i);
			try {
				int n = (int)(Math.random() * (higher-lower)) + lower;
				currentThread().wait(n);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Le Thread numero "+numero+ " a fini de compter jusqu'à 10.");
	}
	
	public MonThread(int i){
		super();
		this.numero =  i;
	}
	
}
