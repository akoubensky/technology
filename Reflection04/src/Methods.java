import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.Test;

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
	 * ����� ������ � �������� ������ �� ��������� ������ � �������� ����������.
	 * @param methodName
	 * @param arg
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	private static void launchMethod(Class<Methods> clazz, String methodName, int arg) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// ������� ����� � ��������� ���.
		Method method = clazz.getDeclaredMethod(methodName, int.class);
		if ((method.getModifiers() & Modifier.STATIC) != 0) {
			method.invoke(null, arg);
		} else {
			method.invoke(new Methods(), arg);
		}
	}
	
	//========================================================
	// �����
	//========================================================
	
	@Test
	public void test01() {
		try {
			launchMethod(Methods.class, "m1", 15);
		} catch (Exception e) {
			assertTrue(e.getMessage(), false);
		}
	}
	
	@Test
	public void test02() {
		try {
			launchMethod(Methods.class, "m2", 25);
		} catch (Exception e) {
			assertTrue(e.getMessage(), false);
		}
	}
	
	@Test
	public void test03() {
		try {
			launchMethod(Methods.class, "m3", 35);
		} catch (Exception e) {
			assertTrue(e.getMessage(), false);
		}
	}
	
	@Test
	public void test04() {
		try {
			launchMethod(Methods.class, "m4", 45);
		} catch (Exception e) {
			System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
			assertTrue(null, false);
		}
	}
}
