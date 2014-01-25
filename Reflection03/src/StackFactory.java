/**
 * Программа, реализующая динамический выбор реализации стека
 * с помощью Reflection.
 */
public class StackFactory<T> {
	/**
	 * Переменная для хранения класса реализации стека
	 */
	private Class<Stack<T>> stackClass;

	public StackFactory(String className) {
		try {
			stackClass = (Class<Stack<T>>)Class.forName(className);
		} catch (ClassNotFoundException ex) {
			stackClass = null;
		}
	}

	public StackFactory(Class<Stack<T>> stackClass) {
		this.stackClass = stackClass;
	}

	/**
	 * Функция, создающая стек по классу, реализующему интерфейс Stack.
	 * @return объект, представляющий стек.
	 */
	public Stack<T> create() {
		try {
			return stackClass.newInstance();
		} catch (InstantiationException e) {
			return null;
		} catch (IllegalAccessException e) {
			return null;
		}
	}

	/**
	 * Тестовая функция, создающая стек по имени, заданному аргументом программы.
	 * @param args строка, задающая имя класса с реализацией стека.
	 */
	public static void main(String[] args) {
		String className = "ArrayStack";
		if (args.length > 0) {
			className = args[0];
		}
		StackFactory<String> factory = new StackFactory<String>(className);
		Stack<String> stack = factory.create();
		stack.push("Element");
		try {
			System.out.println("Just pushed: " + stack.peek());
			System.out.println("Just popped: " + stack.pop());
		} catch (Stack.Underflow e) {
			e.printStackTrace();
		}
	}
}
