package multithreading.basics.stop_thread;

import java.time.LocalTime;

public class MyRunnable implements Runnable {

	private boolean stopThread = false;
	
	public synchronized void stopThread() {
		this.stopThread = true;
	}
	
	
	@Override
	public void run() {
		while(!stopThread) {
			
			sleep();
			System.out.println("My thread is running ... " + LocalTime.now());
			
		}
		System.out.println("STOPED!");
		
	}
	
	private void sleep() {
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
