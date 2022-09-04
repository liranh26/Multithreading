package multithreading.basics.stop_thread;

public class Runner {

	public static void main(String[] args) throws InterruptedException {
		MyRunnable runnable = new MyRunnable();
		Thread thread = new Thread(runnable);
		
		System.out.println("[1] Thread state: " +thread.getState());
		thread.start();
		
		System.out.println("[2] Thread state: " +thread.getState());
		Thread.sleep(1000);
		
		System.out.println("[3] Thread state: " +thread.getState());
		runnable.stopThread();
		
		System.out.println("[4] Thread state: " +thread.getState());

		thread.join();
		
		//with join before will be TERMINATED
		//without join it will be TIMED_WAITING - main thread will keep running while the thread is stopping the while loop
		System.out.println("[5] Thread state: " +thread.getState()); 
		
	}

}
