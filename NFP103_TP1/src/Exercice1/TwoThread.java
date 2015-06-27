package Exercice1;

public class TwoThread extends Thread {
	
	int numero;
	
	public void run(){
		for (int i=0;i<10;i++){
			System.out.println("New thread numero "+numero);
			currentThread().yield();
		}
	}
	
	public TwoThread(int i){
		super();
		this.numero =  i;
	}
	
//	public static void main(String[] args)  {
//		TwoThread tt = new TwoThread();
//		tt.start();
//		tt.setName("Je suis le Thread principal");
//		
//		for (int i=0;i<10;i++){
////			System.out.println("Main thread");
//			System.out.println(tt.getName());
////			currentThread().yield();
//		}
//	}
	
//	static void Wait(long milli){
//		System.out.println("pause de "+milli+" ms");
//		try {
//			Thread.sleep(milli);
//		}
//		catch (InterruptedException x){
//			// ignorer
//		}
//	}
}


