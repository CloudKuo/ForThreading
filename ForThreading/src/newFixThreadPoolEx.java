import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ThreadExample implements Runnable {
  @Override
  public void run() {
    String threadName= Thread.currentThread().getName();
    System.out.println("run " + threadName + " thread");
  }
}
/*在主程式會使用 newFixedThreadPool 建立一個執行緒 pool 只能使用固定數量的執行緒，如果使用的執行緒超出了這個數量，就會放在 Queue 裡進行等待其它的執行緒執行完才能執行*/


public class newFixThreadPoolEx {
	static void single() {
//		以上的程式使用了 newSingleThreadExecutor 建立了單執行緒的 thread pool，所以程式只會有一個執行緒在執行
		ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
		System.out.println("-----------single------------");
	    try {
	      for (int i = 0; i < 6; i++) {
	        singleThreadPool.execute(new ThreadExample());
	      }
	    } catch(Exception e){
	      throw new RuntimeException(e);
	    } finally {
	      singleThreadPool.shutdown();
	    }
	}
	public static void main(String args[]) {
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
		System.out.println("----------newFixedThread------------");
		try {
			for (int i = 0; i < 6; i++) {
				fixedThreadPool.execute(new ThreadExample());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			fixedThreadPool.shutdown();
		}
		
		single();
	}

}
