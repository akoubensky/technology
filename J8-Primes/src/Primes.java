import java.util.ArrayList;
import java.util.List;


public class Primes {
	private int size;
	public List<Integer> primes = new ArrayList<>();
	
	public Primes(int size) {
		this.size = size;
		generatePrimes();
	}
	
	public void add(int n) {
		size += n;
		generatePrimes();
	}
	
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
	
	private boolean isPrime(int n) {
		for (Integer p : primes) {
			if (n % p == 0) return false;
		}
		return true;
	}

	public static void main(String[] args) {
		List<Integer> primes = new Primes(50001).primes;
		System.out.println(primes.get(50000));
	}

}
