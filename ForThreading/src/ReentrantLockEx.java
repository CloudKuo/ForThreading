import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.*;

public class ReentrantLockEx<E> {
    private Lock lock = new ReentrantLock();
    private Object[] elems;
    private int next;

    public ReentrantLockEx(int capacity) {
        elems = new Object[capacity];
    }

    public ReentrantLockEx() {
        this(16);
    }

    public void add(E elem) {
        lock.lock();
        try {
            if (next == elems.length) {
                elems = Arrays.copyOf(elems, elems.length * 2);
            }
            elems[next++] = elem;
        } finally {
            lock.unlock();
        }
    }

    public E get(int index) {
        lock.lock();
        try {
            return (E) elems[index];
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        lock.lock();
        try {
            return next;
        } finally {
            lock.unlock();
        }
    }
    
    public static void main(String[] args) {
		ReentrantLockEx<Integer> r = new ReentrantLockEx<>();
		r.add(50);
		Thread t1 = new Thread(()-> System.out.println(r.size()));
		Thread t2 = new Thread(()-> System.out.println(r.get(0)));
		t1.start();
		t2.start();
	}
}
