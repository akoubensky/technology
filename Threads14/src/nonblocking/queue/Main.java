package nonblocking.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static ConcurrentQueue<Integer> queue = new ConcurrentQueue<>();
    private static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {
        List<Runnable> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(() -> { for (int k = 0; k < 100; k++) { queue.offer(k); counter.incrementAndGet(); }});
        }
        for (int i = 0; i < 20; i++) {
            list.add(() -> { for (int k = 0; k < 100; ) { if (queue.poll() != null) { k++; counter.incrementAndGet(); }}});
        }

        list.parallelStream().forEach(Runnable::run);
        System.out.println(counter);
    }
}
