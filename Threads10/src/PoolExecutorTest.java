import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class PoolExecutorTest {
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
    ExecutorService executor = Executors.newFixedThreadPool(3);
    
    for (int i = 0; i < 25; i++) {
    	executor.execute(new MyProcess(i));
    }
    executor.shutdown();
    executor.awaitTermination(1, TimeUnit.MINUTES);
    
    System.out.println("Все процессы созданы и запущены");
  }
}
