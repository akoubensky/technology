package org.ifmo.technologies;

/**
 * Showing different names of different classes
 */
public class ClassNames {
	/**
	 * Static internal class
	 */
	public static class A {
	}
	
	/**
	 * Non-static internal class
	 */
	public class B {
	}

	// Implicitly created unnamed internal class
	private static Runnable runnable = new Runnable() {
		@Override
		public void run() {
		}
	};

	public static void main(String[] args) throws ClassNotFoundException {
		printNames(String.class);
		printNames(java.util.Iterator.class);
		printNames(int.class);
		printNames(double[][].class);
		printNames(String[].class);
		printNames(Class.forName("[[Z"));
		printNames(A.class);
		printNames(B.class);
		printNames(Runnable.class);
		printNames(runnable.getClass());
	}

	private static void printNames(Class<?> clazz) {
		  System.out.format("Name: %s,\tSimple: %s,\tCanonical: %s\n",
		      clazz.getName(), clazz.getSimpleName(), clazz.getCanonicalName());
		}
}
