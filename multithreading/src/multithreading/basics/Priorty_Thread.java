package multithreading.basics;

public class Priorty_Thread {

	public static void main(String[] args) throws InterruptedException {

		Thread th1 = new Thread(() -> {
			Thread currThread = Thread.currentThread();
			System.out.println("Current thread" + currThread + ", with priorty: " + currThread.getPriority());
		});

		th1.setName("Thread 1: max priorty");
		th1.setPriority(Thread.MAX_PRIORITY);
		

		Thread th2 = new Thread(() -> {
			Thread currThread = Thread.currentThread();
			System.out.println("Current thread" + currThread + ", with priorty: " + currThread.getPriority());
		});
		
		th2.setName("Thread 2: min priorty");
		th2.setPriority(Thread.MIN_PRIORITY);
		
		th2.start();
		th1.start(); //thread 1 will print MOST of the time before thread2 but NOT always!
		
		th2.join();
		th1.join();

		
		
		// Check Thread state
		Thread thread = new Thread(()->{
			Thread curr = Thread.currentThread();
			System.out.println("[1] Thread state :" + curr.getState()); // Runnable
		});
		
		System.out.println("[2] Thread state : " + thread.getState()); // New
		
		thread.start();
		
		System.out.println("[3] Thread state : " + thread.getState()); // Runnable
		
		thread.join();
		
		// this will be printed only after the thread joined to main thread.
		System.out.println("[4] Thread state : " + thread.getState()); // TERMINATED
		
		
	}

}
