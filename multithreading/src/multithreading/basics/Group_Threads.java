package multithreading.basics;

import java.util.ArrayList;
import java.util.List;

public class Group_Threads {
	
	static int num = 1;
	final static int WAITING_TIME = 1000;
	final static int DELAY_START = 100;
	final static int multiplyValue = 2;
	
	public static void main(String[] args) throws InterruptedException {
		
		ThreadGroup group = new ThreadGroup("my group");

		List<Thread> threads = new ArrayList<Thread>();

		threads.add(new Thread(group, new numThread(), "Thread 1"));
		threads.add(new Thread(group, new numThread(), "Thread 2"));
		threads.add(new Thread(group, new numThread(), "Thread 3"));

		for (Thread thread : threads) {
			thread.start();
		}
		
		Thread.sleep(WAITING_TIME);
		group.interrupt();
		
		
		
		
	}
	
	
	static class numThread extends Thread {
		@Override
		public void run() {
			boolean stop = false;
			while (!stop) {
				try {
					Thread.sleep(DELAY_START);
					num *= multiplyValue;
				} catch (InterruptedException e) {
					stop = true;
					System.out.println("Interrupted!");
				}

			}
			//The result for num will be zero (for WAITING_TIME = 2000) because the number overflows the integer max value.
			//If we change WAITING_TIME to value less then 2000.. 1000 we will get a result.
			System.out.println(Thread.currentThread().getName() + ", num is:" + num);
		}
	}
}
