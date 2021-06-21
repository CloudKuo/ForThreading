import java.util.concurrent.ThreadFactory;

class NormalThread implements Runnable {
  private String message;

  public NormalThread(String message) {
    this.message = message;
  }

  @Override
  public void run() {
    System.out.println(this.message);
  }
}

class ThreadFactoryExample implements ThreadFactory {
	private int count = 0;

	@Override
	public Thread newThread(Runnable r) {
		count = count + 1;
		return new Thread(r);
	}

	public int getCount() {
		return count;
	}
}

public class ThreadFactoryEx {
	public static void main(String args[]) {
//		NormalThread a = new NormalThread("message1");
//		Thread thread1 = new Thread(a);
//
//		NormalThread b = new NormalThread("message2");
//		Thread thread2 = new Thread(b);
//
//		NormalThread c = new NormalThread("message3");
//		Thread thread3 = new Thread(c);
//
//		thread1.start();
//		thread2.start();
//		thread3.start();
		
		ThreadFactoryExample factory = new ThreadFactoryExample();

	    Thread thread1 = factory.newThread(new NormalThread("message1"));
	    Thread thread2 = factory.newThread(new NormalThread("message2"));
	    Thread thread3 = factory.newThread(new NormalThread("message3"));

	    System.out.println(factory.getCount());

	    thread1.start();
	    thread2.start();
	    thread3.start();
	}
}
