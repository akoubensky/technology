package org.ifmo.technologies;

import java.lang.reflect.InvocationTargetException;

/**
 * Using constructors of objects of different classes
 */
public class Constructors {
	String name;	// Some name
	int version;	// Some version
	
	@Override
	public String toString() {
		return name + "; version " + version;
	}
	
	/**
	 * Public "default" constructor.
	 */
	public Constructors() {
		this("Constructors");
	}
	
	/**
	 * Public constructor with a parameter.
	 * 
	 * @param name
	 */
	public Constructors(String name) {
		this(name, 1);
	}
	
	/**
	 * Private constructor with parameters.
	 *  
	 * @param name
	 * @param version
	 */
	private Constructors(String name, int version) {
		this.name = name;
		this.version = version;
	}

	public static void main(String[] args) {
		Class<Constructors> clazz = Constructors.class;
		Constructors object;

		// Let's try to create objects of Constructor class with several constructors
		try {
			object = clazz.newInstance();
			System.out.println(object);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		try {
			object = clazz.getConstructor().newInstance();
			System.out.println(object);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		try {
			object = clazz.getConstructor(String.class).newInstance();
			System.out.println(object);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		try {
			object = clazz.getConstructor(String.class).newInstance("Hello world");
			System.out.println(object);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		try {
			object = clazz.getConstructor(String.class, int.class).newInstance("Hello world", 2);
			System.out.println(object);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}

}
