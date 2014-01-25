/**
 * Запускает два независимых процесса, работающих с одной и той же очередью.
 */
public class Launcher {
	public static void main(String[] args) {
		final SyncQueue queue = new SyncQueue();

		Thread t1 = new Thread(
				new Runnable() {
					public void run() {
						for (int i = 0; i < 50; ++i) {
							System.out.println(Thread.currentThread().getName() + ": added " + queue.offer());
							System.out.println(Thread.currentThread().getName() + ": added " + queue.offer());
							System.out.println(Thread.currentThread().getName() + ": extracted " + queue.poll());
							System.out.println(Thread.currentThread().getName() + ": extracted " + queue.poll());
							try {
								Thread.sleep(1);
							} catch (InterruptedException x) {
								System.out.println(Thread.currentThread().getName() + ": interrupted!");
								break;
							}
						}
					}
				}, "Thread 1");

		Thread t2 = new Thread(
				new Runnable() {
					public void run() {
						for (int i = 0; i < 70; ++i) {
							System.out.println(Thread.currentThread().getName() + ": added " + queue.offer());
							System.out.println(Thread.currentThread().getName() + ": extracted " + queue.poll());
							try {
								Thread.sleep(1);
							} catch (InterruptedException x) {
								System.out.println(Thread.currentThread().getName() + ": interrupted!");
								break;
							}
						}
					}
				}, "Thread 2");

		queue.offer();
		queue.offer();
		queue.offer();
		queue.offer();
		t1.start();
		t2.start();
	}
}
