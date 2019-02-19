package atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MyTest {
    static AtomicInteger ii = new AtomicInteger(0);
//    static Counter c = new Counter();

    public static void main(String[] args) {
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) list.add(new Thread(()->{ for (int k = 0; k < 10000; k++) ii.incrementAndGet(); }));
//        for (int i = 0; i < 10; i++) list.add(new Thread(()->{ for (int k = 0; k < 10000; k++) c.incr(); }));
        for (Thread t : list) t.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        System.out.println(ii);
//        System.out.println(c.getCounter());
    }
}
