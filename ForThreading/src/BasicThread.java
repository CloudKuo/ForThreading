
public class BasicThread extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("This is my thread");
		System.out.println("Thread name :"+Thread.currentThread().getName());
		for (int i=0 ; i <10;i++) {
			System.out.println("Printer 2 :"+ i);
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println("----------------App start-------------");
		BasicThread b = new BasicThread();
//		b.setPriority(5);
		b.start();
		Thread t = new Thread(new AnotherCreateThread());
//		t.setPriority(5);
		t.start();
		System.out.println("Thread name :"+Thread.currentThread().getName());
		for (int i=0 ; i <10;i++) {
			System.out.println("Printer 1 :"+ i);
		}
		System.out.println("----------------App finished-------------");
		t.start();

	}
}


class AnotherCreateThread implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Second thread");
		System.out.println( "Thread name :"+Thread.currentThread().getName());
		for (int i=0 ; i <10;i++) {
			System.out.println("Printer 3 :"+ i);
		}

	}
	
}