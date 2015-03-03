import java.util.Arrays;
import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.stream.Stream;


public final class StreamUtil {
	public static <T1,T2,R> Stream<R> zip(BiFunction<T1, T2, R> func, Stream<T1> s1, Stream<T2> s2) {
		Stream.Builder<R> builder = Stream.builder();
		Iterator<T1> it1 = s1.iterator();
		Iterator<T2> it2 = s2.iterator();
		while (it1.hasNext() && it2.hasNext()) {
			builder.accept(func.apply(it1.next(), it2.next()));
		}
		return builder.build();
	}
	
	public static void main(String[] args) {
		Stream<Integer> s1 = Arrays.asList(0, 1, 1, 2, 3, 5, 8, 13, 21).stream();
		Stream<Integer> s2 = Arrays.asList(1, 1, 2, 3, 5, 8, 13, 21).stream();
		Stream<Integer> result = zip(Integer::sum, s1, s2);
		result.forEach(x -> System.out.print(" " + x));
		System.out.println();
		// Одним потоком не обойтись: после исполнения skip(1) поток уже не прочитать с начала!
		zip(Integer::sum, s1, s1.skip(1)).forEach(x -> System.out.print(" " + x));
	}
}
