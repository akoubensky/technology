import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;


public class Sorter {
	private Method sortMethod = null;

	public Sorter(String method) {
		try {
			sortMethod = SortMethods.class.getMethod(method, Comparable[].class);
		} catch (Exception e) {
			System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
			return;
		}
	}

	public <T extends Comparable<T>> void sort(T[] data) {
		try {
			sortMethod.invoke(null, (Object)data);
		} catch (Exception e) {
			System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
			return;
		}
	}

	public static void main(String[] args) {
		String[] data = { "у", "попа", "была", "собака", "он", "ее","любил" };
		System.out.println(Arrays.toString(data));
		Sorter sorter = new Sorter("QuickSort");
		sorter.sort(data);
		System.out.println(Arrays.toString(data));

		Class<?> myClass = sorter.getClass();
		try {
			Field f = myClass.getDeclaredField("sortMethod");
			try {
				if (f.get(sorter) != null) {
					f.set(sorter, SortMethods.class.getMethod("InsertSort", Comparable[].class));
				} 
				String[] data1 = { "у", "попа", "была", "собака", "он", "ее","любил" };
				System.out.println(Arrays.toString(data1));
				sorter.sort(data1);
				System.out.println(Arrays.toString(data1));
			} catch (Exception e) {
				System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
				return;
			}
		} catch (Exception e) {
			System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
			return;
		}
	}
}
