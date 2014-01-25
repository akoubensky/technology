import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * ������������ ������������� ������� �������.
 */
public class Methods {
	/**
	 * ��������� ����, ���������� � ����������� ������.
	 */
	int local = 12;

	/**
	 * ��� ����������� ����� ������, ������� �������� ���� ��������.
	 * @param x
	 */
	@SuppressWarnings("unused")
	private static void m1(int x) {
		System.out.println("static m1. �������� x = " + x);
	}

	/**
	 * ������������� ����� ������ �������� ��������� �������� ����
	 * � ���� ��������.
	 * @param y
	 */
	@SuppressWarnings("unused")
	private void m2(int y) {
		System.out.format("m2. ��������� �������� local = %d, �������� y = %d\n", local,  y);
	}
	/**
	 * ������������� ����� ������ �������� ��������� �������� ����
	 * � ���� ��������.
	 * @param z
	 */
	@SuppressWarnings("unused")
	private void m3(int z) {
		System.out.format("m3. ��������� �������� local = %d, �������� z = %d\n", local,  z);
	}

	/**
	 * �������� �������.
	 * @param args ��� ����������� ������ � �������� ���������.
	 */
	public static void main(String[] args) {
		// ��������: ����������� ����� ���������� ��������?
		if (args.length != 2) {
			System.out.println("Usage: java Methods.class <method> <arg>");
			return;
		}

		// ������ ���������� ���������.
		String methodName = args[0];  // ��� ����������� ������.
		int arg;                      // �������� ������������� ���������.
		try {
			arg = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {
			System.out.println("Second argument should be an integer");
			return;
		}

		Class<?> clazz = Methods.class;
		try {
			// ������� ����� � ��������� ���.
			Method method = clazz.getDeclaredMethod(methodName, int.class);
			if ((method.getModifiers() & Modifier.STATIC) != 0) {
				method.invoke(null, arg);
			} else {
				method.invoke(new Methods(), arg);
			}
		} catch (SecurityException e) {
			System.out.println("����� " + methodName + " ����������!");
			return;
		} catch (NoSuchMethodException e) {
			System.out.println("����� " + methodName + " �� ������!");
			return;
		} catch (IllegalArgumentException e) {
			System.out.println("����� " + methodName + " ������� ����� ��������!");
			return;
		} catch (IllegalAccessException e) {
			System.out.println("����� " + methodName + " ����������!");
			return;
		} catch (InvocationTargetException e) {
			System.out.println("����� " + methodName + " ����������� � ������������� �������!");
			return;
		}
	}
}
