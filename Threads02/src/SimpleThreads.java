/**
 * Вводит следующие примитивы:
 * t.join(), t.join(millis), t.interrupt()
 * t.getName().
 * Еще есть Thread.interrupted() - чистит interrupted статус,
 * t.isInterrupted() - только проверяет статус.
 */
public class SimpleThreads {

	// Выводит сообщение, помеченное именем нити
	static void threadMessage(String message) {
		System.out.format("%s: %s%n", Thread.currentThread().getName(), message);
	}

	public static void main(String args[]) throws InterruptedException {
		// Задержка в миллисекундах перед тем, как мы прервем нить MessageLoop
		long patience = 1000 * 14;

		threadMessage("Starting MessageLoop thread");
		long startTime = System.currentTimeMillis();
		Thread t = new Thread(() -> {
					String importantInfo[] = {
							"Один верблюд идет,",
							"И другой верблюд идет,",
							"И третий верблюд идет,",
							"И весь караван идет."
					};
					try {
						for (int i = 0; i < importantInfo.length; i++) {
							// Задержка на 4 секунды
							Thread.sleep(4000);
							// Печатаем очередное сообщение
							threadMessage(importantInfo[i]);
						}
					} catch (InterruptedException e) {
						threadMessage("Я спел все, что мог!");
					}
				},
				"Верблюды");
		t.start();

		threadMessage("Ждем, пока нить с верблюдами закончится.");
		// Ждем, пока нить MessageLoop закончится.
		while (t.isAlive()) {
			threadMessage("Пока ждем...");
			// Ждем максимум 1 секунду окончания нити MessageLoop.
			t.join(1000);
			if (((System.currentTimeMillis() - startTime) > patience) &&
					t.isAlive()) {
				threadMessage("Устали ждать!");
				t.interrupt();
				// Теперь уже недолго...
				t.join();
			}

		}
		threadMessage("Ну, наконец!");
	}
}
