/**
 * Author: Alexandre Koubenski
 *
 * Абстрактный стек, описанный в виде интерфейса
 *
 */
public interface Stack<T> {
	/**
	 * Прерывание, которое может возникнуть при работе со стеком:
	 * исчерпание - попытка доступа к элементам пустого стека.
	 */
	static class Underflow extends Exception {
		public Underflow() { super("Stack underflow"); }
	}

	/**
	 * Прерывание, которое может возникнуть при работе со стеком:
	 * перепонлнение - попытка втолкнуть элемент в полностью заполненный стек.
	 */
	static class Overflow extends RuntimeException {
		public Overflow() { super("Stack overflow"); }
	};

	/**
	 * Вталкивание элемента в стек
	 * @param element   вталкиваемый элемент
	 */
	void push(T element);

	/**
	 * Выталкивание элемента из стека
	 * @return   выталкиваемый элемент
	 * @throws Underflow  исчерпание стека - попытка вытолкнуть элемент из пустого стека
	 */
	T  pop() throws Underflow;

	/**
	 * Взятие элемента с вершины стека
	 * @return  верхний элемент стека
	 * @throws Underflow  исчерпание стека - попытка доступа к несуществующему элементу
	 */
	T  peek() throws Underflow;

	/**
	 * Проверка стека на пустоту
	 * @return  true, если стек пуст, false - в противном случае
	 */
	boolean empty();
}
