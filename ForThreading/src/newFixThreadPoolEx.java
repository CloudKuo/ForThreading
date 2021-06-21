import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ThreadExample implements Runnable {
  @Override
  public void run() {
    String threadName= Thread.currentThread().getName();
    System.out.println("run " + threadName + " thread");
  }
}
/*�b�D�{���|�ϥ� newFixedThreadPool �إߤ@�Ӱ���� pool �u��ϥΩT�w�ƶq��������A�p�G�ϥΪ�������W�X�F�o�Ӽƶq�A�N�|��b Queue �̶i�浥�ݨ䥦����������槹�~�����*/


public class newFixThreadPoolEx {
	static void single() {
//		�H�W���{���ϥΤF newSingleThreadExecutor �إߤF�������� thread pool�A�ҥH�{���u�|���@�Ӱ�����b����
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
