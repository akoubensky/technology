/**
 * Author: Alexandre Koubenski
 *
 * ���������� ������������� ����� � ���� ������� ���������
 *
 */
public class ArrayStack<T> implements Stack<T> {
	/**
	 * ����������� ������������� ����� �� 100 ���������
	 */
	public ArrayStack() {
		this(100);
	}

	/**
	 * ����������� ������������� ����� �� �������� ����� ���������
	 * @param maxElems  - ������������ ����� ��������� �����
	 */
	public ArrayStack(int maxElems) {
		stack = (T[])new Object[maxElems];
		topPtr = 0;
	}

	/**
	 * �������� ����� �� �������
	 * @return  true, ���� ���� ����, false - � ��������� ������
	 */
	public boolean empty() {
		return topPtr == 0;
	}

	/**
	 * ������������ �������� �� �����
	 * @return   ������������� �������
	 * @throws Underflow  ���������� ����� - ������� ���������� ������� �� ������� �����
	 */
	public T pop() throws Underflow {
		if (topPtr == 0) throw new Underflow();
		return stack[--topPtr];
	}

	/**
	 * ����������� �������� � ����
	 * @param element   ������������ �������
	 */
	public void push(T element) {
		if (topPtr == stack.length) throw new Overflow();
		stack[topPtr++] = element;
	}

	/**
	 * ������ �������� � ������� �����
	 * @return  ������� ������� �����
	 * @throws Underflow  ���������� ����� - ������� ������� � ��������������� ��������
	 */
	public T peek() throws Underflow {
		if (topPtr == 0) throw new Underflow();
		return stack[topPtr-1];
	}

	private T[] stack;     // ������ �������� ���������
	private int topPtr;    // ����� ��������� � ����� - ��������� �������

	/**
	 * ������� �������� ����������������� ���������
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayStack<Integer> s = new ArrayStack<Integer>();
		// ���������� � ���� ��������������� ���� �����
		for (int i = 1; i < 6; ++i) {
			s.push(i);
		}
		// ����������� �����, ���������� � ����, � �������� ��.
		try {
			while (!s.empty()) {
				System.out.println("extracting from stack: " + s.peek());
				s.pop();
			}
		} catch (Stack.Underflow x) {
			// �������... ���������� � ���� ��������� ���� �� ������!
			System.out.println("Stack underflow!");
		}
	}
}
