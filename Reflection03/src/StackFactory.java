/**
 * ���������, ����������� ������������ ����� ���������� �����
 * � ������� Reflection.
 */
public class StackFactory<T> {
	/**
	 * ���������� ��� �������� ������ ���������� �����
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
	 * �������, ��������� ���� �� ������, ������������ ��������� Stack.
	 * @return ������, �������������� ����.
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
	 * �������� �������, ��������� ���� �� �����, ��������� ���������� ���������.
	 * @param args ������, �������� ��� ������ � ����������� �����.
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
