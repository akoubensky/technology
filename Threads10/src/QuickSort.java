import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;


public class QuickSort<T extends Comparable<T>> extends RecursiveAction {
	private static final long serialVersionUID = 1L;
	
	private T[] array;
	private int low, high;
	
	public QuickSort(T[] array, int from, int to) {
		this.array = array;
		low = from;
		high = to;
	}
	
	private static <T extends Comparable<T>> void quickSort(T[] array, int low, int high) {
		int bottom = low;
		int top = high;
		if (top - bottom <= 1) return;
		T element = array[bottom];
		while (top > bottom) {
			while (bottom < top && array[--top].compareTo(element) >= 0) ;
			array[bottom] = array[top];
			while (bottom < top && array[++bottom].compareTo(element) <= 0) ;
			array[top] = array[bottom];
		}
		array[bottom] = element;
		quickSort(array, low, bottom);
		quickSort(array, bottom+1, high);
	}

	@Override
	protected void compute() {
		int bottom = low;
		int top = high;
		if (top - bottom <= 1) return;
		T element = array[bottom];
		while (top > bottom) {
			while (bottom < top && array[--top].compareTo(element) >= 0) ;
			array[bottom] = array[top];
			while (bottom < top && array[++bottom].compareTo(element) <= 0) ;
			array[top] = array[bottom];
		}
		array[bottom] = element;
		new QuickSort<T>(array, low, bottom).fork();
		new QuickSort<T>(array, bottom+1, high).fork();
	}
	
	private static <T extends Comparable<T>> boolean test(T[] array) {
		for (int i = 0; i < array.length - 1; ++i) {
			if (array[i].compareTo(array[i+1]) > 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws InterruptedException {
		final int COUNT = 100000;
		Random rnd = new Random();
		Integer[] array = new Integer[COUNT];
		for (int i = 0; i < COUNT; i++) array[i] = rnd.nextInt(2*COUNT);
		
		ForkJoinPool pool = new ForkJoinPool(8);
		long start = System.currentTimeMillis();
		pool.execute(new QuickSort<Integer>(array, 0, COUNT));
		pool.shutdown();
		pool.awaitTermination(1, TimeUnit.MINUTES);
		long elapsed = System.currentTimeMillis() - start;
		System.out.println(test(array) + " in " + elapsed + " millis");
		
		array = new Integer[COUNT];
		for (int i = 0; i < COUNT; i++) array[i] = rnd.nextInt(2*COUNT);
		start = System.currentTimeMillis();
		quickSort(array, 0, COUNT);
		elapsed = System.currentTimeMillis() - start;
		System.out.println(test(array) + " in " + elapsed + " millis");
	}
}
