package multithreading.basics;

import java.util.List;

public class _Exceptions {

	public static void main(String[] args) throws InterruptedException {
		
		Thread th1 = new Thread(new CustomThreadGroup("group1"), new MyThread(1), "Thread 1");
		Thread th2 = new Thread(new MyThread(1), "Thread 2");
		
		th1.setPriority(7);
		
		// Note - if the there is no setUncaughtExceptionHandler, the next handler to
		// take place in is ThreadGroup object acts as its handler.

		// *** Default Uncaught ExceptionHandler ***/
		Thread.setDefaultUncaughtExceptionHandler((t, exception) -> {
			System.out.println("@ Defualt Exception Handler - " + exception.getMessage() + ", At "+t); //t = [Thread 1,7,group1]
		});

		// *** Using Uncaught Exception to catch exception ***/
		th1.setUncaughtExceptionHandler((t, exception) -> {
			System.out.println("# Thread Exception Handler - " + exception.getMessage() + ", At "+t); //t = [Thread 2,5,main]
		});

		th1.start();
		th2.start();
		
		th1.join();
		th2.join();
	}
	

	static class CustomThreadGroup extends ThreadGroup {

		public CustomThreadGroup(String name) {
			super(name);
		}

		public CustomThreadGroup(ThreadGroup parent, String name) {
			super(parent, name);
		}

		@Override
		public void uncaughtException(Thread t, Throwable e) {
			super.uncaughtException(t, e);
			System.out.println(e.getMessage());
		}

	}

	static class MyThread implements Runnable {
		private final int numberOfSeconds;

		public MyThread(int numberOfSeconds) {
			this.numberOfSeconds = numberOfSeconds;
		}

		@Override
		public void run() {
			for (int i = 0; i < numberOfSeconds; i++) {
				try {
					System.out.println("Sleeping for 1s, thread: " + Thread.currentThread().getName());
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			List<String> list = null;
			list.size(); // exception Cannot invoke "java.util.List.size()" because "list" is null

			System.out.println("Result: " + (4 / 0)); // exception divide by zero
		}
	}

}
