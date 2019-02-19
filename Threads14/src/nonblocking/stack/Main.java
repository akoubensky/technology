package nonblocking.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static ConcurrentStack<Integer> stack = new ConcurrentStack<>();
    private static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {
        List<Runnable> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(() -> { for (int k = 0; k < 100; k++) { stack.push(k); counter.incrementAndGet(); }});
        }
        for (int i = 0; i < 20; i++) {
            list.add(() -> { for (int k = 0; k < 100; ) if (stack.pop() != null) { k++; counter.incrementAndGet(); }});
        }

        list.parallelStream().forEach(Runnable::run);
        System.out.println(counter);
    }
}
