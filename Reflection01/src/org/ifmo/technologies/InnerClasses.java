package org.ifmo.technologies;

import java.lang.reflect.InvocationTargetException;

public class InnerClasses {
	String outerMessage = "Message";
	
	public class Inner {
		String message = "Inner";
		public Inner() { message = outerMessage; }
		public Inner(String msg) {
			message = msg;
		}
		@Override
		public String toString() {
			return message;
		}
	}

	public static class InnerS {
		String message = "InnerS";
		public InnerS() {}
		public InnerS(String msg) {
			message = msg;
		}
		public String toString() {
			return message;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Class<Inner> innerClass;
		try {
			innerClass = (Class<Inner>)Class.forName(
					"org.ifmo.technologies.InnerClasses$Inner");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
		Class<InnerS> innerSClass;
		try {
			innerSClass = (Class<InnerS>)Class.forName(
					"org.ifmo.technologies.InnerClasses$InnerS");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
		Inner objInner;
		InnerS objInnerS;
		
		try {
			objInnerS = innerSClass.newInstance();
			System.out.println(objInnerS);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		try {
			objInner = innerClass.newInstance();
			System.out.println(objInner);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		try {
			objInner = innerClass.getConstructor(String.class)
					.newInstance("other inner");
			System.out.println(objInner);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		try {
			objInnerS = innerSClass.getConstructor(String.class)
					.newInstance("other innerS");
			System.out.println(objInnerS);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		try {
			objInner = innerClass.getConstructor(InnerClasses.class)
					.newInstance(new InnerClasses());
			System.out.println(objInner);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		try {
			objInner = innerClass.getConstructor(InnerClasses.class, String.class)
					.newInstance(new InnerClasses(), "other inner");
			System.out.println(objInner);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
			}
		};
		System.out.println(runnable.getClass().getConstructors().length + " constructors");
		System.out.println(runnable.getClass().getMethods().length + " methods");
	}

}
