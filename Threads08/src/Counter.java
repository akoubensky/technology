import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Взаимная блокировка процессов преодолена
 * с помощью условной постановки замка.
 */
public class Counter {
	private int counter = 0;
	private final Lock lock = new ReentrantLock();

	public void change(Counter other) {
		boolean myLock = lock.tryLock();
		boolean otherLock = other.lock.tryLock();
		if (myLock && otherLock) {
			// Удалось войти в критическую секцию, закрыв оба семафора
			counter++;
			other.counter++;
		}
		if (myLock) lock.unlock();
		if (otherLock) other.lock.unlock();
	}

	public static void main(String[] args) {
		final Counter c1 = new Counter();
		final Counter c2 = new Counter();
		Thread t1 = new Thread(() -> {
						for (int i = 0; i < 1000; ++i) {
							c1.change(c2);
						}
						System.out.format(
								"Thread %s done, counters = (%d, %d)\n",
								Thread.currentThread().getName(), c1.counter, c2.counter);
				});
		Thread t2 = new Thread(() -> {
						for (int i = 0; i < 1000; ++i) {
							c2.change(c1);
						}
						System.out.format(
								"Thread %s done, counters = (%d, %d)\n",
								Thread.currentThread().getName(), c1.counter, c2.counter);
				});
		t1.start();
		t2.start();
	}
}
