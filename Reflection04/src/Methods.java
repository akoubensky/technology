import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Демонстрация динамического запуска методов.
 */
public class Methods {
	/**
	 * Локальное поле, хранящееся в экземплярах класса.
	 */
	int local = 12;

	/**
	 * Это статический метод класса, который печатает свой аргумент.
	 * @param x
	 */
	@SuppressWarnings("unused")
	private static void m1(int x) {
		System.out.println("static m1. Аргумент x = " + x);
	}

	/**
	 * Нестатический метод класса печатает локальное значение поля
	 * и свой аргумент.
	 * @param y
	 */
	@SuppressWarnings("unused")
	private void m2(int y) {
		System.out.format("m2. Локальное значение local = %d, аргумент y = %d\n", local,  y);
	}
	/**
	 * Нестатический метод класса печатает локальное значение поля
	 * и свой аргумент.
	 * @param z
	 */
	@SuppressWarnings("unused")
	private void m3(int z) {
		System.out.format("m3. Локальное значение local = %d, аргумент z = %d\n", local,  z);
	}

	/**
	 * Тестовая функция.
	 * @param args Имя вызываемого метода и значение аргумента.
	 */
	public static void main(String[] args) {
		// Проверка: необходимое число аргументов передано?
		if (args.length != 2) {
			System.out.println("Usage: java Methods.class <method> <arg>");
			return;
		}

		// Чтение аргументов программы.
		String methodName = args[0];  // Имя вызываемого метода.
		int arg;                      // Значение передаваемого аргумента.
		try {
			arg = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {
			System.out.println("Second argument should be an integer");
			return;
		}

		Class<?> clazz = Methods.class;
		try {
			// Находим метод и запускаем его.
			Method method = clazz.getDeclaredMethod(methodName, int.class);
			if ((method.getModifiers() & Modifier.STATIC) != 0) {
				method.invoke(null, arg);
			} else {
				method.invoke(new Methods(), arg);
			}
		} catch (SecurityException e) {
			System.out.println("Метод " + methodName + " недоступен!");
			return;
		} catch (NoSuchMethodException e) {
			System.out.println("Метод " + methodName + " не найден!");
			return;
		} catch (IllegalArgumentException e) {
			System.out.println("Метод " + methodName + " неверно задан аргумент!");
			return;
		} catch (IllegalAccessException e) {
			System.out.println("Метод " + methodName + " недоступен!");
			return;
		} catch (InvocationTargetException e) {
			System.out.println("Метод " + methodName + " применяется к неправильному объекту!");
			return;
		}
	}
}
