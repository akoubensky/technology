package org.ifmo.technologies;

import java.lang.reflect.Constructor;

/**
 * Иллюстрация к созданию объектов в случае параметризованных классов
 * @param <T>
 */
public class MyComparator<T extends Comparable<T>> {
	T object1;
	T object2;
	
	public MyComparator(T obj1, T obj2) {
		object1 = obj1;
		object2 = obj2;
	}
	
	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder(object1.toString());
		int comp = Math.round(Math.signum(object1.compareTo(object2)));
		switch (comp) {
		case -1:
			buf.append(" меньше ");
			break;
		case 0:
			buf.append(" равен ");
			break;
		case 1:
			buf.append(" больше ");
			break;
		}
		return buf.append(object2).toString();
	}
	
	public static void main(String[] args) {
		// Сравниваем две строки с помощью прямого обращения к MyComparator
		MyComparator<String> comparator1 = new MyComparator<>("Вася", "Петя");
		System.out.println(comparator1);
		
		// Теперь то же самое, но с помощью Reflection
		try {
			Class<MyComparator> clazz = MyComparator.class;
			Constructor<MyComparator> constr = clazz.getConstructor(Comparable.class, Comparable.class);
			MyComparator comparator2 = constr.newInstance(25, 15);
			System.out.println(comparator2);
			MyComparator comparator3 = constr.newInstance(25, "Сережа");
			System.out.println(comparator3);
		} catch (Exception e) {
			System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
		}
	}
}
