/**
 * Создает и запускает простую нить с помощью интерфейса Runnable.
 *
 */
public class MainClass1 {

	public static void main(String args[]) {
		Thread thread2 = new Thread(
				new Runnable() {
					@Override
					public void run() {
						System.out.println("Hello from thread " + Thread.currentThread().getName());
					}
				},
				"Thread 2");
		thread2.start();
		System.out.println("Hello from " + Thread.currentThread().getName());
	}

}
