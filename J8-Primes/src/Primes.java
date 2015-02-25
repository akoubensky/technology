import java.util.ArrayList;
import java.util.List;

/**
 * Генерация списка простых чисел простым перебором
 * нечетных элементов с проверкой их на простоту.
 */
public class Primes {
	// Количество простых чисел, которое необходимо сгенерировать
	private int size;
	
	// Список простых чисел (вначале пустой)
	public List<Integer> primes = new ArrayList<>();
	
	/**
	 * Конструктор запкскает генератор простых чисел.
	 * @param size	Количество простых чисел, которые нужно сгенерировать
	 */
	public Primes(int size) {
		this.size = size;
		generatePrimes();
	}
	
	/**
	 * Добавляет в список простых чисел еще некоторое количество простых.
	 * @param n	Сколько еще нужно добавить простых чисел.
	 */
	public void add(int n) {
		if (n <= 0) throw new IllegalArgumentException(
				"Добавить можно только положительное число простых");
		size += n;
		generatePrimes();
	}
	
	/**
	 * Перебирает нечетные числа, начиная с последнего сгенерированного числа,
	 * и проверяет их на простоту. Первое встретившееся простое число добавляется
	 * в список простых чисел.
	 */
	private void generatePrimes() {
		int s;
		while ((s = primes.size()) < size) {
			if (s == 0) primes.add(2);
			else if (s == 1) primes.add(3);
			else {
				int next = primes.get(s-1) + 2;
				while (!isPrime(next)) next += 2;
				primes.add(next);
			}
		}
	}
	
	/**
	 * Проверка числа на простоту.
	 * Используется уже сгенерированный список простых чисел.
	 * @param n	Проверяемое число.
	 * @return
	 */
	private boolean isPrime(int n) {
		for (Integer p : primes) {
			if (p * p > n) return true;
			if (n % p == 0) return false;
		}
		return true;
	}

	public static void main(String[] args) {
		// Генерируем 100001 простых чисел.
		List<Integer> primes = new Primes(100001).primes;
		// В качестве проверки печатаем первые 30 простых чисел.
		primes.stream().limit(30).forEach(x -> System.out.print(" " + x));
		System.out.println();
		// Теперь печатаем последнее найденное простое число.
		System.out.println(primes.get(100000));
	}

}
