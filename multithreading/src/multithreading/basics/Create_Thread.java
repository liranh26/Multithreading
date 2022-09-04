package multithreading.basics;

public class Create_Thread {
	
	public static void main(String[] args) throws InterruptedException {
		
		// create a thread
		Thread thread = new Thread(()-> {
			System.out.println("Liran thread is running!");
			for (int i = 0; i < 5; i++) {
				try {
					Thread.sleep(i*50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Liran thread is still running...");
			}
		}, "Liran_Thread");

		thread.start();
		
		// setting main thread to sleep - here the program (main thread) will stop but liran thread will run...
		Thread.sleep(500);
		
		if(Thread.currentThread().getName().equals("main"))
			System.out.println("Main thread is running");

		
		
		Runnable myRunnable = new Runnable() {
			
			@Override
			public void run() {
				System.out.println("thread 2 is running...");
				
			}
		};
		
		Thread thread2 = new Thread(myRunnable, "Thd2");
		// thread 2 will run only after main will wake up again
		thread2.start();

		
		thread.join();
		thread2.join();
		

	}
	
}
