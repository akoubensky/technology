/**
 * Author: Alexandre Koubenski
 *
 * ����������� ����, ��������� � ���� ����������
 *
 */
public interface Stack<T> {
	/**
	 * ����������, ������� ����� ���������� ��� ������ �� ������:
	 * ���������� - ������� ������� � ��������� ������� �����.
	 */
	static class Underflow extends Exception {
		public Underflow() { super("Stack underflow"); }
	}

	/**
	 * ����������, ������� ����� ���������� ��� ������ �� ������:
	 * ������������� - ������� ��������� ������� � ��������� ����������� ����.
	 */
	static class Overflow extends RuntimeException {
		public Overflow() { super("Stack overflow"); }
	};

	/**
	 * ����������� �������� � ����
	 * @param element   ������������ �������
	 */
	void push(T element);

	/**
	 * ������������ �������� �� �����
	 * @return   ������������� �������
	 * @throws Underflow  ���������� ����� - ������� ���������� ������� �� ������� �����
	 */
	T  pop() throws Underflow;

	/**
	 * ������ �������� � ������� �����
	 * @return  ������� ������� �����
	 * @throws Underflow  ���������� ����� - ������� ������� � ��������������� ��������
	 */
	T  peek() throws Underflow;

	/**
	 * �������� ����� �� �������
	 * @return  true, ���� ���� ����, false - � ��������� ������
	 */
	boolean empty();
}
