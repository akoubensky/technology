import java.util.Arrays;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Генерация ограниченного потока первых простых чисел методом "решета Эратосфена".
 */
public class SplitPrimes {
	/**
	 * "Ячейка" для хранения очередного простого числа.
	 */
	private static class Buffer {
		private Integer buffer;
		public Integer get() { return buffer; }
		public void set(Integer n) { buffer = n; }
	}
	
	private Buffer buffer = new Buffer();
	
	/**
	 * Сохраняет первое число из потока в накопителе и генерирует новый
	 * поток, отбрасывая первое число и фильтруя оставшиеся таким образом,
	 * чтобы в нем не осталось чисел, кратных первому.
	 * @param s	Исходный поток (первое число в нем - простое)
	 * @return	Отфильтрованный поток без первого элемента.
	 */
	private Stream<Integer> sieve(Stream<Integer> s, Stream.Builder<Integer> builder) {
		// Извлекаем из потока первывй элемент
		Spliterator<Integer> spliterator = s.spliterator();
		spliterator.tryAdvance(buffer::set);
		Integer next = buffer.get();
		// Добавляем этот элемент в строящийся поток простых чисел
		builder.add(next);
		// Строим новый поток из оставшихся элементов, отфильтровывая кратные next.
		return StreamSupport.stream(spliterator, false).filter(x -> x % next != 0);
		
		// Внимание! Использовать старый поток уже не удастся!
		// Продвижение итератора его "испортило"!
		//return s.skip(1).filter(x -> x % next != 0);
	}
	
	/**
	 * Генератор заданного количества простых чисел.
	 * @param n	Количество генерируемых простых чисел.
	 * @return	Поток простых чисел.
	 */
	public Stream<Integer> primes(int n) {
		Stream.Builder<Integer> builder = Stream.builder();
		Stream<Integer> s = Stream.iterate(2, x -> x+1);
		while (n-- > 0) {
			s = sieve(s, builder);
		}
		return builder.build();
	}
	
	/**
	 * Генерирует поток из заданного количества простых чисел и выдает их в виде массива.
	 * @param n
	 * @return
	 */
	public Integer[] getPrimes(int n) {
		return primes(n).toArray(Integer[]::new);
	}
	
	public static void main(String[] args) {
		SplitPrimes s = new SplitPrimes();
		System.out.println(Arrays.toString(s.getPrimes(20)));
		//System.out.println(s.getPrimes(10000)[9999]);
	}
}
