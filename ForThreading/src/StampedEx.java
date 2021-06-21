import java.util.Arrays;
import java.util.concurrent.locks.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;


class OtherImplement{
	void demo() {
		StampedLock sl = new StampedLock();
	    ExecutorService executor = Executors.newFixedThreadPool(2);
	    // Runnable as lambda - optimistic read
	    Runnable r1 = ()->{
	      long stamp = sl.tryOptimisticRead();
	      try{
	        System.out.println(">>In optimistic lock " + sl.validate(stamp));
	        try {
	          TimeUnit.SECONDS.sleep(2);
	        } catch (InterruptedException e) {
	          // TODO Auto-generated catch block
	          e.printStackTrace();
	        }
	        System.out.println("<<In optimistic lock " + sl.validate(stamp));            
	      }finally{
	        sl.unlock(stamp);
	      }
	    };
	        
	    // Runnable as lambda - Write lock
	    Runnable r2 = ()->{
	      System.out.println("about to get write lock");
	      try {
	        TimeUnit.SECONDS.sleep(1);
	      } catch (InterruptedException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	      }
	      long stamp = sl.writeLock();
	      try{
	        System.out.println("<<After getting write lock ");
	          
	      }finally{
	        sl.unlock(stamp);
	        System.out.println(">>Relinquished write lock ");
	      }
	    };
	        
	    executor.submit(r2);
	    // Optimistic read
	    executor.submit(r1);
	    executor.submit(r2);
	    
	    executor.shutdown();
	}
	 
}


public class StampedEx<E> {
    private StampedLock lock = new StampedLock();
    private Object[] elems;
    private int next;

    public StampedEx(int capacity) {
        elems = new Object[capacity];
    }

    public StampedEx() {
        this(16);
    }

    public void add(E elem) {
        long stamp = lock.writeLock();
        try {
            if (next == elems.length) {
                elems = Arrays.copyOf(elems, elems.length * 2);
            }
            elems[next++] = elem;
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    public E get(int index) {
        long stamp = lock.tryOptimisticRead();
        Object elem = elems[index];
        if (!lock.validate(stamp)) {
            stamp = lock.readLock();
            try {
                elem = elems[index];
            } finally {
                lock.unlockRead(stamp);
            }
        }
        return (E) elem;
    }

    public int size() {
        long stamp = lock.tryOptimisticRead();
//        ¼ÖÆ[Åª¼Ò¦¡
        int size = next;
        if (!lock.validate(stamp)) {
            stamp = lock.readLock();
            try {
                size = next;
            } finally {
                lock.unlockRead(stamp);
            }
        }
        return size;
    }
    public static void main(String[] args) {
		ReentrantLockEx<Integer> r = new ReentrantLockEx<>();
		r.add(50);
		Thread t1 = new Thread(() -> {
			for (int i = 0; i <= 10; i++) {
				r.add(10);
			}
		});
		Thread t2 = new Thread(()-> System.out.println(r.get(8)));
//		t2.start();
//		t1.start();
		new OtherImplement().demo();

	}
}


