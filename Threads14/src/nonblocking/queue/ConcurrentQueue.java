package nonblocking.queue;

import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentQueue<E> {
    private static class Elem<T> {
        T info;
        AtomicReference<Elem<T>> next = new AtomicReference<>();

        Elem(T info) { this.info = info; }
    }

    AtomicReference<Elem<E>> first = new AtomicReference<>(new Elem<E>(null)); // dummy
    AtomicReference<Elem<E>> last = new AtomicReference<>(first.get());

    public void offer(E item) {
        Elem<E> newNode = new Elem<>(item);
        while (true) {
            Elem<E> curTail = last.get();
            Elem<E> residue = curTail.next.get();
            if (curTail == last.get()) {
                if (residue == null) {
                    if (curTail.next.compareAndSet(null, newNode)) {
                        last.compareAndSet(curTail, newNode);
                        return;
                    }
                } else {
                    last.compareAndSet(curTail, residue) /* B */;
                }
            }
        }
    }

    public E poll() {
        Elem<E> oldHead;
        Elem<E> newHead;
        do {
            oldHead = first.get();
            if (oldHead.next.get() == null) {
                return null;
            }
            newHead = oldHead.next.get();
        } while (!first.compareAndSet(oldHead, newHead));
        return newHead.info;
    }
}