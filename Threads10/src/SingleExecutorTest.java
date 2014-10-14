import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Запускаем процессы с помощью простого исполнителя задач.
 * Этот исполнитель поддерживает только последовательное исполнение задач,
 * поэтому процессы запускаются по очереди. 
 */
public class SingleExecutorTest {
	/**
	 * Имитирует выполнение полезной работы.
	 * Печатает отчет о выполнении в виде трех сообщений.
	 */
	private static class MyProcess implements Runnable {
		final int number;

		public MyProcess(int n) { number = n; }

		public void run() {
			for (int i = 0; i < 3; i++) {
				System.out.format("Thread: %s; Process: %d; Iteration: %d%n",
						Thread.currentThread().getName(), number, i);
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		// Создаем исполнителя.
		ExecutorService executor = Executors.newSingleThreadExecutor();

		// Запускаем все работы.
		for (int i = 0; i < 25; i++) {
			executor.execute(new MyProcess(i));
		}
		System.out.println("Все процессы созданы и запущены");

		// Исполнитель завершает выполнение задач.
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.MINUTES);
		System.out.println("Выполнение всех задач закончено");
	}
}
