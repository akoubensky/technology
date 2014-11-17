import java.util.LinkedList;
import java.util.List;

/**
 * Простое, но не работающее решение задачи о производителе и потребителе
 * с использованием обычного списка.
 */
public class NaiveLauncher {
	public static void main(String[] args) {
		// Очередь реализована в виде обычного списка
		List<Integer> list = new LinkedList<>();
		
		// Поток, записывающий 10000 элементов в очередь
		Thread supplier = new Thread(() -> {
			for (int i = 1; i <= 10000; i++) {
				if (i % 1000 == 0) {
					System.out.println("Added: " + i);
				}
				list.add(i);
			}
		});
		
		// Поток, считывающий 10000 элементов из очереди. Зависает на пустой очереди
		// до тех пор, пока в ней не появится хотя бы один элемент.
		Thread consumer = new Thread(() -> {
			for (int i = 1; i <= 10000; i++) {
				while (list.isEmpty()) ;
				Integer ii = list.remove(0);
				if (ii % 1000 == 0) {
					System.out.println("Extracted: " + i);
				}
			}
		});
		
		consumer.start();
		supplier.start();
	}
}
