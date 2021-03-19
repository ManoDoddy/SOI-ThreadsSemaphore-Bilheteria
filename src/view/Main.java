package view;

import java.util.concurrent.Semaphore;

import controller.ThreadIngresso;

public class Main {

	public static void main(String[] args) {
		
		Semaphore semaforo = new Semaphore(1);
		
		for (int i = 0; i < 300; i++) {
			new ThreadIngresso(semaforo).start();
		}
	}

}
