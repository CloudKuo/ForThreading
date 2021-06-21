import java.util.Arrays;
import java.util.concurrent.locks.*;

public class ReentrantReadWriteLockEx<E> {
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Object[] elems;
    private int next;

    public ReentrantReadWriteLockEx(int capacity) {
        elems = new Object[capacity];
    }

    public ReentrantReadWriteLockEx() {
        this(16);
    }

    public void add(E elem) {
        lock.writeLock().lock();
        try {
            if (next == elems.length) {
                elems = Arrays.copyOf(elems, elems.length * 2);
            }
            elems[next++] = elem;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public E get(int index) {
        lock.readLock().lock();
        try {
            return (E) elems[index];
        } finally {
            lock.readLock().unlock();
        }
    }

    public int size() {
        lock.readLock().lock();
        try {
            return next;
        } finally {
            lock.readLock().unlock();
        }
    }
    
    public static void main(String[] args) {
		ReentrantLockEx<Integer> r = new ReentrantLockEx<>();
		r.add(50);
		Thread t1 = new Thread(() -> {
			for (int i = 0; i <= 10; i++) {
				r.add(10);
			}
		});
		Thread t2 = new Thread(()-> System.out.println(r.get(1)));
		t1.start();
		t2.start();
	}
}