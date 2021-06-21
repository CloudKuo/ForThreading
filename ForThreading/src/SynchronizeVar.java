import java.util.ArrayList;
import java.util.List;
class ArrayListThreadTest1 implements Runnable {
    private List<String> list;
    public ArrayListThreadTest1(List<String> list) {
        this.list = list;
    }
    @Override
    public void run() {
      for (int i = 0; i < 10; i++) {
        try {
          Thread.sleep(1000);
          synchronized(list) {
            this.list.add("a" + i);
          }
        } catch(Exception e) {
          throw new RuntimeException(e);
        }
      }
    }
}

public class SynchronizeVar {
	public static void main(String args[]) throws Exception {
        List<String> list = new ArrayList<String>();
        Thread thread1 = new Thread(new ArrayListThreadTest1(list));
        Thread thread2 = new Thread(new ArrayListThreadTest1(list));
        Thread thread3 = new Thread(new ArrayListThreadTest1(list));
        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
        thread3.start();
        thread3.join();
        System.out.println("list size is " + list.size());
        System.out.println(list.toString());
    }
}
