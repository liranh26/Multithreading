package multithreading.basics;

public class Daemon_Thread {
	
	public static void main(String[] args) throws InterruptedException {
		Thread thread1 = new Thread(new MyThread(10), "#10 seconds thread");
		Thread thread2 = new Thread(new MyThread(3), "$3 seconds thread");
		
		// ** All daemon threads terminate when All the non-daemon (user) threads terminate **
		// ex. for daemon thread: Java Garbage Collector
		thread1.setDaemon(true);

		thread1.start();
		thread2.start();
		
		// if we want to let the deamon thread finish its execution we need to join it (keep alive non-daemon thread..)
		thread1.join();

	}
	
	
	
	static class MyThread implements Runnable{

		private final int numSeconds;
		
		MyThread(int num){
			numSeconds = num;
		}
		
		@Override
		public void run() {
			int start = 0;
			while(start <= numSeconds*1000) {
				System.out.println("Thread: " + Thread.currentThread().getName() + " is sleeping for " +(start/1000)+" second..");
				sleep();
				start+=1000;	
			}
		}
		
		private static void sleep() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
		
	}
}
