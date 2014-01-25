
/**
 * Реализация стека в виде простого однонаправленного списка элементов
 *
 * @param <E> тип элементов стека
 */
public class ListStack<E> implements Stack<E>{
	private static class ListNode<T> {
		T info;
		ListNode<T> next;
		public ListNode(T info, ListNode<T> next) { this.info = info; this.next = next; }
		public ListNode(T info) { this(info, null); }
	}

	ListNode<E> first = null;  // Список, представляющий элементы стека.

	/**
	 * Проверка пустоты стека.
	 * @return true, если стек пуст, false в противном случае.
	 */
	public boolean empty() { return first == null; }

	/**
	 * Выдает верхний элемент стека без его удаления.
	 * @return верхний элемент стека.
	 */
	public E peek() throws Stack.Underflow {
		if (empty()) throw new Stack.Underflow();
		return first.info;
	}

	/**
	 * Выталкивание верхнего элемента из стека.
	 * @return вытолкнутый элемент.
	 */
	public E pop() throws Stack.Underflow {
		if (empty()) throw new Stack.Underflow();
		ListNode<E> toPop = first;
		first = first.next;
		return toPop.info;
	}

	/**
	 * Заталкивание элемента на вершину стека.
	 * @param element заталкиваемый элемент.
	 */
	public void push(E element) {
		first = new ListNode<E>(element, first);
	}

	/**
	 * Функция, проверяющая работоспособность данной реализации.
	 * @param args не используется.
	 */
	public static void main(String[] args) {
		ListStack<Integer> s = new ListStack<Integer>();
		// Вталкиваем в стек последовательно пять чисел
		for (int i = 1; i < 6; ++i) {
			s.push(i);
		}
		// Выталкиваем числа, записанные в стек, и печатаем их.
		try {
			while (!s.empty()) {
				System.out.println("extracting from stack: " + s.peek());
				s.pop();
			}
		} catch (Stack.Underflow x) {
			// Странно... прерывания в этой программе быть не должно!
			System.out.println("Stack underflow!");
		}
	}
}
