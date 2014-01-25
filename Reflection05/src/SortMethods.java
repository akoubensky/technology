import java.util.Random;


public final class SortMethods {
	// ��������� ��������� �����
	static Random random = new Random();

	/**
	 * ���������� ������� ������� �������.
	 * @param <T> ��� ��������� ������������ �������
	 * @param data ������, ���������� ����������
	 */
	public static <T extends Comparable<T>> void InsertSort(T[] data) {
		int i, j;
		for (i = 1; i < data.length; i++) {
			T c = data[i];
			for (j = i-1; j >= 0 && data[j].compareTo(c) > 0; j--) {
				data[j+1] = data[j];
			}
			data[j+1] = c;
		}
	}

	public static <T extends Comparable<T>> void QuickSort(T[] data) {
		QuickSort(data, 0, data.length);
	}  

	public static <T extends Comparable<T>> void QuickSort(T[] data, int from, int to) {
		if (to-from <= 1)
			return;
		else {
			int index = from + random.nextInt(to - from);
			T c = data[index];    // �������� ��������� �������
			data[index] = data[from];
			// ������������ �������� ������� �� �������� ������� � ������� c.
			int low = from, high = to;
			// Inv: (low <= high) && data[from:(low-1)] <= c && data[high:to] >= c
			while (low < high) {
				while (low < high && data[--high].compareTo(c) >= 0) ;
				data[low] = data[high];
				while (low < high && data[++low].compareTo(c) <= 0) ;
				data[high] = data[low];
			}
			// ��������� ������� �� ���� �����
			data[low] = c;
			// ���������� ��������� ������� � ������ �������� �������
			QuickSort(data, from, low);
			QuickSort(data, low+1, to);
		}
	}

	private static void print(Object[] data) {
		for (Object o : data) {
			System.out.print(o + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		String[] data1 = { "�", "����", "����", "������", "��", "��","�����" };
		print(data1);
		InsertSort(data1);
		print(data1);

		String[] data2 = { "������", "�����", "��������", "�", "������", "����","�������" };
		print(data2);
		QuickSort(data2);
		print(data2);
	}
}
