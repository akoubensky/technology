
public class InfiniteSummatorThread implements Runnable {
	public void run() {
		int sum = 0;
		for (int i = Integer.MIN_VALUE; i <= Integer.MAX_VALUE; i+=2) {
			sum += i;
//			if (Thread.interrupted()) break;
		}
		System.out.println(sum);
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new InfiniteSummatorThread());
		t.start();
		Thread.sleep(1000);
		t.interrupt();
		System.out.println("Alive: " + t.isAlive() + "; interrupted: " + t.isInterrupted());
	}
}
