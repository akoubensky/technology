import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Supplier;
import java.util.stream.Stream;


public class StreamPrimes {
	public static class Pair<T, U> {
		T first;
		U second;
		public Pair(T first, U second) {
			this.first = first;
			this.second = second;
		}
	}
	
	public static class StreamCopier<T> {
		private final Iterator<T> it;
		
		LinkedList<T> source1 = new LinkedList<>();
		LinkedList<T> source2 = new LinkedList<>();
		
		public StreamCopier(Stream<T> s) {
			it = s.iterator();
		}
		
		private Supplier<T> getSupplier(LinkedList<T> src1, LinkedList<T> src2) {
			return () -> {
				if (src1.size() == 0) {
					T nextElem = it.next();
					src1.addLast(nextElem);
					src2.addLast(nextElem);
				}
				return src1.removeFirst();
			};
		}
		
		public Stream<T> getStream1() { return Stream.generate(getSupplier(source1, source2)); }
		public Stream<T> getStream2() { return Stream.generate(getSupplier(source2, source1)); }
	}
	
	public static <T> Pair<Stream<T>, Stream<T>> duplicate(Stream<T> s) {
		StreamCopier<T> copier = new StreamCopier<T> (s);
		return new Pair<>(copier.getStream1(), copier.getStream2());
	}
	
	public static <T> Stream<T> cons(T e, Stream<T> s) {
		return Stream.concat(Stream.of(e), s);
	}
	
	public static <T> Pair<Stream<T>, Stream<T>> split(int n, Stream<T> s) {
		Pair<Stream<T>, Stream<T>> doubled = duplicate(s);
		return new Pair<Stream<T>, Stream<T>>(doubled.first.limit(n), doubled.second.skip(n));
	}
	
	public static <T> Pair<T, Stream<T>> split1(Stream<T> s) {
		Pair<Stream<T>, Stream<T>> p = split(1, s);
		return new Pair<T, Stream<T>>(p.first.findFirst().get(), p.second);
	}
	
	public static Stream<Integer> sieve(Stream<Integer> s) {
		Pair<Integer, Stream<Integer>> p = split1(s);
		Integer head = p.first;
		return Stream.concat(Stream.of(head), sieve(p.second.filter(x -> x % head != 0)));
	}
	
	public static <T> Stream<T> from(Iterator<T> it) {
		return Stream.generate(it::next);
	}
	
	public static void main(String[] args) {
		Stream<Integer> s = Stream.iterate(1, x -> 2*x);
		Pair<Stream<Integer>, Stream<Integer>> sPair = duplicate(s);
		
		Iterator<Integer> it1 = sPair.first.iterator();
		Iterator<Integer> it2 = sPair.second.skip(1).iterator();
		
		for (int i = 0; i < 10; i++) {
			System.out.print(" " + (it1.next() + it2.next()));
		}
		System.out.println();
		
		Stream<Integer> primes = sieve(Stream.iterate(2, x -> x + 1));
		primes.limit(10).forEach(x -> System.out.print(" " + x));
		System.out.println();
	}
}
