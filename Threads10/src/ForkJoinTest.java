import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

public class ForkJoinTest {
	private static final int POOLS_NUM = Runtime.getRuntime().availableProcessors();
	
	@SuppressWarnings("serial")
	private static class MyProcess extends RecursiveAction {
		final int number;

		public MyProcess(int n) { number = n; }

		@Override
		protected void compute() {
			for (int i = 0; i < 3; i++) {
				System.out.format("Thread: %s; Process: %d; Iteration: %d%n",
						Thread.currentThread().getName(), number, i);
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ForkJoinPool executor = new ForkJoinPool(POOLS_NUM);

	    System.out.format("����� �������� %d �����������\n", POOLS_NUM);
	    long startTime = System.currentTimeMillis();
		for (int i = 0; i < 25; i++) {
			executor.execute(new MyProcess(i));
		}
		System.out.println("��� �������� ������� � ��������");

		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.MINUTES);
		System.out.format("���������� ���� ����� ��������� �� %d �����������\n",
				(int)(System.currentTimeMillis() - startTime));
	}
}
