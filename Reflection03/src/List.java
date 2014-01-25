
import java.util.Iterator;

/**
 * ����� ���������� ���������� �������� ����������������� ������
 */
public class List<E> implements Iterable<E> {
	/**
	 * ���� ������
	 * @param <E> ��� ��������� ������
	 */
	private static class Node<E> {
		public E info;
		public Node<E> next;

		public Node(E info, Node<E> next) { this.info = info; this.next = next; }
		public Node(E info) { this(info, null); }
	}

	/**
	 * ������� �������� ��������� ������
	 * @param <E> ��� ��������� ������, ���������� ����������.
	 */
	private static class ListIterator<E> implements Iterator<E> {
		// ��������� �� ������� ������� ������
		Node<E> current;

		// �����������
		public ListIterator(List<E> list) { current = list.root; }

		// ���������� ������������ �������
		public boolean hasNext() { return current != null; }
		public E next() {
			E res = current.info;
			current = current.next;
			return res;
		}
		// ����� remove �� ����������.
		public void remove() { throw new UnsupportedOperationException(); }
	}

	// �������� ������� ������
	Node<E> root = null;

	/**
	 * �����, �������� �������� ��������� ������
	 */
	public Iterator<E> iterator() { return new ListIterator<E>(this); }

	/**
	 * ���������� �������� � ������ ������
	 * @param e ����������� �������
	 */
	public void addHead(E e) { root = new Node<E>(e, root); }

	/**
	 * �������� ������� �������� �� ������
	 * @return ��������� �������
	 */
	public E removeHead() { 
		E res = root.info;
		root = root.next;
		return res;
	}

	/**
	 * �������� ������� ������
	 * @return true, ���� ������ ����, false � ��������� ������.
	 */
	public boolean empty() { return root == null; }

	/**
	 * ������ ������� �������� ������
	 * @return ������ ������� � ������.
	 */
	public E head() { return root.info; }
}
