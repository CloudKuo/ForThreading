
public class DaemonEx {
	public static void main(String[] args) {
		Thread t = new Thread(() -> {
			while (true) {
				System.out.println("Daemon running");
			}
		});
//		��ؼg�k
//		Thread t2 = new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				System.out.println("Daemon running");
//			}
//		});
		t.setDaemon(true);
		t.start();
	}
}
