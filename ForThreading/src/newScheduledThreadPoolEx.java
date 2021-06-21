import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class ThreadExample2 implements Runnable {
  private long lastTime;

  public ThreadExample2(long lastTime) {
    this.lastTime = lastTime;
  }

  @Override
  public void run() {
      String threadName = Thread.currentThread().getName();
      System.out.println("run " + threadName + " thread, " + (new Date().getTime() - lastTime));
  }
//  以上的程式是執行緒的程式，主要會把目前使用到的執行緒名稱以及目前的時間減掉從主程式開始執行的時間印出來，用來確認執行緒隔多久的時間執行。
}



public class newScheduledThreadPoolEx {
	public static void main(String args[]) {
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		long lastTime = System.currentTimeMillis();
		scheduledThreadPool.scheduleAtFixedRate(new ThreadExample2(lastTime), 1, 5, TimeUnit.SECONDS);
	}
//	使用了 newScheduledThreadPool 建立了 scheduled 的執行緒 pool，然後指定的型態不是 ExecutorService 而是 ScheduledExecutorService 
//	這樣才能執行排程的方法。之後就會呼叫 scheduleAtFixedRate 的方法。程式在開始後等待了 1 秒之後執行緒開始的去執行，每隔 5 秒去執行一次 ThreadExample 的執行緒
//	newSingleThreadScheduledExecutor則是使用單個執行緒做這個事
}
