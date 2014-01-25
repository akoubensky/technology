
/**
 * ���������� ����� � ���� �������� ����������������� ������ ���������
 *
 * @param <E> ��� ��������� �����
 */
public class ListStack<E> implements Stack<E>{
	private static class ListNode<T> {
		T info;
		ListNode<T> next;
		public ListNode(T info, ListNode<T> next) { this.info = info; this.next = next; }
		public ListNode(T info) { this(info, null); }
	}

	ListNode<E> first = null;  // ������, �������������� �������� �����.

	/**
	 * �������� ������� �����.
	 * @return true, ���� ���� ����, false � ��������� ������.
	 */
	public boolean empty() { return first == null; }

	/**
	 * ������ ������� ������� ����� ��� ��� ��������.
	 * @return ������� ������� �����.
	 */
	public E peek() throws Stack.Underflow {
		if (empty()) throw new Stack.Underflow();
		return first.info;
	}

	/**
	 * ������������ �������� �������� �� �����.
	 * @return ����������� �������.
	 */
	public E pop() throws Stack.Underflow {
		if (empty()) throw new Stack.Underflow();
		ListNode<E> toPop = first;
		first = first.next;
		return toPop.info;
	}

	/**
	 * ������������ �������� �� ������� �����.
	 * @param element ������������� �������.
	 */
	public void push(E element) {
		first = new ListNode<E>(element, first);
	}

	/**
	 * �������, ����������� ����������������� ������ ����������.
	 * @param args �� ������������.
	 */
	public static void main(String[] args) {
		ListStack<Integer> s = new ListStack<Integer>();
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
