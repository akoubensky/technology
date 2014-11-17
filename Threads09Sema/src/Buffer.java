import java.util.concurrent.Semaphore;


public class Buffer {
	final int MAX_COUNT = 10;
	// ����� �� 10 ����� �����.
	final private Integer[] buffer = new Integer[MAX_COUNT];
	// ��������� �� ������ ������� ������.
	private int first = 0;
	// ����� ������� �����.
	private int count = 0;

	// ������ ������ �����
	Semaphore empty = new Semaphore(MAX_COUNT);
	// ������ ������� �����
	Semaphore full = new Semaphore(0);

	/**
	 * ���������� ����� � �����.
	 * @param n ������������ �����.
	 * @throws InterruptedException 
	 */
	public void addObject(Integer n) {
		empty.acquireUninterruptibly();
		synchronized(this) {
			// ���������� ��������� ����� � �����.
			buffer[(first + count++) % MAX_COUNT] = n;
			full.release();
		}
	}

	/**
	 * ������ ����� �� ������ � ���������.
	 * @return ����������� �����.
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
	 * �������� ������� ������� ��� ������ - �������� � �������� - � ��������� ��.
	 * @param args �� ������������.
	 */
	public static void main (String[] args) {
		final Buffer buffer = new Buffer();

		final Reader reader = new Reader(buffer);
		final Writer writer = new Writer(buffer);

		reader.start();
		writer.start();
	}

}
