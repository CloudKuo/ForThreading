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
//  �H�W���{���O��������{���A�D�n�|��ثe�ϥΨ쪺������W�٥H�Υثe���ɶ���q�D�{���}�l���檺�ɶ��L�X�ӡA�ΨӽT�{������j�h�[���ɶ�����C
}



public class newScheduledThreadPoolEx {
	public static void main(String args[]) {
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		long lastTime = System.currentTimeMillis();
		scheduledThreadPool.scheduleAtFixedRate(new ThreadExample2(lastTime), 1, 5, TimeUnit.SECONDS);
	}
//	�ϥΤF newScheduledThreadPool �إߤF scheduled ������� pool�A�M����w�����A���O ExecutorService �ӬO ScheduledExecutorService 
//	�o�ˤ~�����Ƶ{����k�C����N�|�I�s scheduleAtFixedRate ����k�C�{���b�}�l�ᵥ�ݤF 1 ���������}�l���h����A�C�j 5 ��h����@�� ThreadExample �������
//	newSingleThreadScheduledExecutor�h�O�ϥγ�Ӱ�������o�Ө�
}
