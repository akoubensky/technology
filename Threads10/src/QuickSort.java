import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * ������� ���������� � ������� ForkJoin �����.
 * 
 * @param <T> ��� ��������� ������������ �������
 */
public class QuickSort<T extends Comparable<T>> extends RecursiveAction {
	private static final long serialVersionUID = 1L;
	private static final int MIN_PARALLEL = 1000; 
	
	final private T[] array;		// ����������� ������
	final private int low, high;	// ������� � ������ ������� ������������ �������
	
	/**
	 * ����������� ������.
	 * 
	 * @param array
	 * @param from
	 * @param to
	 */
	public QuickSort(T[] array, int from, int to) {
		this.array = array;
		low = from;
		high = to;
	}
	
	@Override
	protected void compute() {
		int bottom = low;
		int top = high;
		if (top - bottom <= MIN_PARALLEL) {
			Arrays.sort(array, low, high);
			return;
		}
		
		// ������ �������, ������� ����� ������� �� "����" �����.
		T element = array[bottom];
		while (top > bottom) {
			while (bottom < top && array[--top].compareTo(element) >= 0) ;
			array[bottom] = array[top];
			while (bottom < top && array[++bottom].compareTo(element) <= 0) ;
			array[top] = array[bottom];
		}
		
		// ��������� �������� �� �����.
		array[bottom] = element;
		
		// ����������� ������ � ���� ��������� �����
		new QuickSort<T>(array, low, bottom).fork();
		new QuickSort<T>(array, bottom+1, high).fork();
	}
	
	/**
	 * �������� ����, ��� ������ ���������� �� �����������.
	 * 
	 * @param array	����������� ������
	 * @return		true, ���� ������ ����������, false � ��������� ������
	 */
	private static <T extends Comparable<T>> boolean test(T[] array) {
		for (int i = 0; i < array.length - 1; ++i) {
			if (array[i].compareTo(array[i+1]) > 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws InterruptedException {
		// ������� ������ �� ��������� ���������
		final int COUNT = 100000;
		
		Random rnd = new Random();
		Integer[] array = new Integer[COUNT];
		for (int i = 0; i < COUNT; i++) array[i] = rnd.nextInt(2*COUNT);
		
		// ��������� "������������" ������ ���������� � ������� ���� Fork/Join �����
		ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		
		System.out.println("������������ ����������");
		long start = System.currentTimeMillis();
		pool.execute(new QuickSort<Integer>(array, 0, COUNT));
		pool.shutdown();
		pool.awaitTermination(1, TimeUnit.MINUTES);
		long elapsed = System.currentTimeMillis() - start;
		
		System.out.format("���������� ��������� %s �� %d �����������\n",
				test(array) ? "�������" : "� ��������", elapsed);
		
		for (int i = 0; i < COUNT; i++) array[i] = rnd.nextInt(2*COUNT);
		System.out.println("���������������� ����������");
		start = System.currentTimeMillis();
		Arrays.sort(array);
		elapsed = System.currentTimeMillis() - start;
		
		System.out.format("���������� ��������� %s �� %d �����������\n",
				test(array) ? "�������" : "� ��������", elapsed);
	}
}
