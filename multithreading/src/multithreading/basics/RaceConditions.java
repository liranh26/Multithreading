package multithreading.basics;

public class RaceConditions {

	// Race conditions can occur when two or more threads read and write
	// the same variable according to one of these two patterns:
	// #1. Read-modify-write
	// #2. Check-then-act

	private static int sum = 0;
	private static boolean isThreadNumOdd;

	public static void main(String[] args) throws InterruptedException {

		// ** Read-modify-write **//
		Thread th1 = new Thread(() -> {
			for (int i = 1; i <= 10; i++) 
				sum += 1; // actions: 1. read sum -> 2. increment the value -> 3. write the value 
		});

		Thread th2 = new Thread(() -> {
			for (int i = 1; i <= 10; i++) 
				sum += 1; 
		});

		th1.start();
		th2.start();

		th1.join();
		th2.join();

		System.out.println("The sum is: " + sum); // sum will have different value each time..(10/15/20....)
		
		
		
		// ** Check-then-act **//
		Thread th3 = new Thread(()->{
			if(3%2!=0)
				isThreadNumOdd = false;
		});
		
		Thread th4 = new Thread(()->{
			if(4%2==0)
				isThreadNumOdd = true;
		});
		
		th3.start();
		th4.start();
		
		th3.join();
		th4.join();
		
		System.out.println("isThreadNumOdd value is: " + isThreadNumOdd); //true or false 
	}

}
