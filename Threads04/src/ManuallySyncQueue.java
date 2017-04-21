import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Alex on 9/11/2016.
 */
public class ManuallySyncQueue {
    List<Integer> sQueue = new ArrayList<>();
    int sum = 0;
    Thread reader = new Thread(() -> {
        for (int i = 0; i < 10000; i++) {
            synchronized(sQueue) {
                try {
                    while (sQueue.isEmpty()) sQueue.wait();
                    sum += sQueue.remove(0); sQueue.notifyAll();
                } catch (InterruptedException e) {}
            }
        }
    });
    Thread writer = new Thread(() -> {
        for (int i = 0; i < 10000; i++) {
            synchronized(sQueue) {
                try {
                    while (sQueue.size() >= 10) sQueue.wait();
                    sQueue.add(i); sQueue.notifyAll();
                } catch (InterruptedException e) {}
            }
        }
    });

    public static void main(String[] args) {
        ManuallySyncQueue eq = new ManuallySyncQueue();
        eq.reader.start();
        eq.writer.start();
        try { eq.reader.join(); } catch(InterruptedException e) {}
        System.out.println(eq.sum);

        int sum = 0;
        for (int i = 0; i < 10000; i++) sum += i;
        System.out.println(sum);
    }
}
