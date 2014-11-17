import java.util.concurrent.Semaphore;


public class Buffer {
	final int MAX_COUNT = 10;
	// Буфер на 10 целых чисел.
	final private Integer[] buffer = new Integer[MAX_COUNT];
	// Указывает на первую занятую ячейку.
	private int first = 0;
	// Число занятых ячеек.
	private int count = 0;

	// Ресурс пустых ячеек
	Semaphore empty = new Semaphore(MAX_COUNT);
	// Ресурс занятых ячеек
	Semaphore full = new Semaphore(0);

	/**
	 * Записывает число в буфер.
	 * @param n Записываемое число.
	 * @throws InterruptedException 
	 */
	public void addObject(Integer n) {
		empty.acquireUninterruptibly();
		synchronized(this) {
			// Записываем очередное число в буфер.
			buffer[(first + count++) % MAX_COUNT] = n;
			full.release();
		}
	}

	/**
	 * Чтение числа из буфера с удалением.
	 * @return Прочитанное число.
	 * @throws InterruptedException 
	 */
	public Integer removeObject() {
		full.acquireUninterruptibly();
		synchronized(this) {
			Integer toRemove = buffer[first++];
			first %= MAX_COUNT;
			count--;
			empty.release();
			return toRemove;
		}
	}

	/**
	 * Тестовая функция создает два потока - читателя и писателя - и запускает их.
	 * @param args Не используется.
	 */
	public static void main (String[] args) {
		final Buffer buffer = new Buffer();

		final Reader reader = new Reader(buffer);
		final Writer writer = new Writer(buffer);

		reader.start();
		writer.start();
	}

}
