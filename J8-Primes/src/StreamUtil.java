import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterators;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


public final class StreamUtil {
	/**
	 * Реализация "застегивания" потоков с помощью промежуточной структуры данных
	 * Stream.Builder.
	 * 
	 * @param func	Функция "застежки"
	 * @param s1	Первый поток
	 * @param s2	Второй поток
	 * @return		"Застегнутый" поток
	 */
	public static <T1,T2,R> Stream<R> zip(BiFunction<T1, T2, R> func, Stream<T1> s1, Stream<T2> s2) {
		Stream.Builder<R> builder = Stream.builder();
		Iterator<T1> it1 = s1.iterator();
		Iterator<T2> it2 = s2.iterator();
		while (it1.hasNext() && it2.hasNext()) {
			builder.accept(func.apply(it1.next(), it2.next()));
		}
		return builder.build();
	}
	
	/**
	 * Реализация "застегивания" потоков с помощью простых итераторов
	 * Stream.Builder.
	 * 
	 * @param func	Функция "застежки"
	 * @param s1	Первый поток
	 * @param s2	Второй поток
	 * @return		"Застегнутый" поток
	 */
	public static <T1,T2,R> Stream<R> zipSplit(BiFunction<T1, T2, R> func, Stream<T1> s1, Stream<T2> s2) {
		Iterator<T1> it1 = s1.iterator();
		Iterator<T2> it2 = s2.iterator();
		Iterator<R> itRes = new Iterator<R>() {

			@Override
			public boolean hasNext() {
				return it1.hasNext() && it2.hasNext();
			}

			@Override
			public R next() {
				return func.apply(it1.next(), it2.next());
			}
		};
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(itRes, 0), false);
	}
	
	/**
	 * Реализация слияния двух упорядоченных потоков
	 * @param s1	Первый поток
	 * @param s2	Второй поток
	 * @return		Упорядоченный поток - результат слияния.
	 */
	public static <T extends Comparable<T>> Stream<T> merge(Stream<T> s1, Stream<T> s2) {
		class Merger<T extends Comparable<T>> {
			T e1, e2;
			Iterator<T> it1, it2;
			Merger(Iterator<T> it1, Iterator<T> it2) {
				this.it1 = it1;
				this.it2 = it2;
				if (it1.hasNext()) e1 = it1.next();
				if (it2.hasNext()) e2 = it2.next();
			}
			private T nextElem(Iterator<T> it) { return it.hasNext() ? it.next() : null; }
			public Iterator<T> iterator() {
				return new Iterator<T>() {
					public boolean hasNext() {
						return e1 != null || e2!= null;
					}
					public T next() {
						T result;
						if (e1 == null) {
							result = e2;
							e2 = nextElem(it2);
						} else if (e2 == null || e1.compareTo(e2) < 0) {
							result = e1;
							e1 = nextElem(it1);
						} else {
							result = e2;
							e2 = nextElem(it2);
						}
						return result;
					}
				};
			}
		}
		return StreamSupport.stream(
			Spliterators.spliteratorUnknownSize(
				new Merger(s1.iterator(), s2.iterator()).iterator(), 0),
			false);
	}
	
	public static void main(String[] args) {
		Stream<Integer> s1 = Stream.of(0, 1, 1, 2, 3, 5, 8, 13, 21);
		Stream<Integer> s2 = Stream.of(1, 1, 2, 3, 5, 8, 13, 21);
		Stream<Integer> stream1 = zip(Integer::sum, s1, s2);
		System.out.println(stream1.collect(Collectors.toList()));
		
		s1 = Stream.of(0, 1, 1, 2, 3, 5, 8, 13, 21);
		s2 = Stream.of(1, 1, 2, 3, 5, 8, 13, 21);
		Stream<Integer> stream2 = zipSplit(Integer::sum, s1, s2);
		System.out.println(stream2.collect(Collectors.toList()));
		// Одним потоком не обойтись: после исполнения skip(1) поток уже не прочитать с начала!
//		s1 = Arrays.asList(0, 1, 1, 2, 3, 5, 8, 13, 21).stream();
//		zip(Integer::sum, s1, s1.skip(1)).forEach(x -> System.out.print(" " + x));
		
		s1 = Stream.of(0, 2, 4, 6, 7, 9, 11);
		s2 = Stream.of(2, 3, 5, 5, 8, 10, 12);
		Stream<Integer> stream3 = merge(s1, s2);
		System.out.println(stream3.collect(Collectors.toList()));
	}
}
