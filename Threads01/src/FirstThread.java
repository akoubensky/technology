/**
 * Очень простая нить.
 */
public class FirstThread extends Thread {

	public FirstThread() { super("My first thread"); }

	@Override
	public void run() {
		System.out.println("Hello from " + getName());
	}

}
