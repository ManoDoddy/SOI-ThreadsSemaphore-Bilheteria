package controller;

import java.util.concurrent.Semaphore;

public class ThreadIngresso extends Thread {
	
	private Semaphore semaforo;
	private static int ingressos = 100;
	private int compra = (int) ((Math.random() * 4) + 1);
	
	public ThreadIngresso(Semaphore semaforo) {
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {
		if(login()) {
			if(process()) {
				try {
					semaforo.acquire();
					validation();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					semaforo.release();
				}
			}
		}
	}

	private boolean login() {
		int timeLogin = (int) ((Math.random() * 1951) + 50);
		try {
			sleep(timeLogin);
			if(timeLogin>1000) {
				System.out.println("#"+getId()+" LOGIN TIMEOUT");
				return false;
			}
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean process() {
		int timeProcess = (int) ((Math.random() * 2001) + 1000);
		try {
			sleep(timeProcess);
			if(timeProcess>2500) {
				System.out.println("#"+getId()+" SESSION TIMEOUT");
				return false;
			}
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private void validation() {
		if(ingressos - compra < 0) {
			System.out.println("#"+getId()+" NÃO FOI POSSÍVEL REALIZAR A COMPRA. INGRESSOS INDISPONIVEIS!");
		}else {
			ingressos-=compra;
			System.out.println("#"+getId()+" "+compra+" INGRESSO(S) COMPRADO(S)");
			System.out.println(ingressos+" INGRESSOS DISPONÍVEIS");
		}
	}
}
