class Tools {
    public static ThreadLocal threadLocal = new ThreadLocal();
}
class MyThread extends Thread {
    @Override
    public void run() {
        if (Tools.threadLocal.get() == null) {
            Tools.threadLocal.set(Thread.currentThread().getName() + ", " + Math.random());
        }
        System.out.println(Tools.threadLocal.get());
    }
}
public class ThreadLocalEx {
	public static void main(String[] args) {
		ThreadLocal<Integer> t = new ThreadLocal<>();
		t.set(2);
		System.out.println(t.get());
		t.remove();
		System.out.println(t.get());

		ThreadLocal<Integer> t2 = ThreadLocal.withInitial(() -> 20);
		System.out.println(t2.get());
		for (int i = 0; i < 5; i++) {
			MyThread thread = new MyThread();
			thread.setName("thread " + i);
			thread.start();
		}
	}
}
