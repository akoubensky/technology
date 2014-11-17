import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class InfiniteSummatorExec implements Callable<Integer>{
	public Integer call() {
		int sum = 0;
		for (int i = Integer.MIN_VALUE; i <= Integer.MAX_VALUE; i+=2) {
			sum += i;
		}
		return sum;
	}
	
	private static void printStatus(Future<Integer> ft) {
		System.out.println("Cancelled: " + ft.isCancelled() + "; done: " + ft.isDone());
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService service = Executors.newCachedThreadPool();
		Future<Integer> ft = service.submit(new InfiniteSummatorExec());
		Thread.sleep(1000);
		printStatus(ft);
		System.out.println("Successfully cancelled: " + ft.cancel(true));
		printStatus(ft);
		if (ft.isDone()) System.out.println(ft.get());
	}
}
