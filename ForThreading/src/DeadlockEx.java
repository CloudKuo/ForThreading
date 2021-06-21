class Sample {
    private String message = null;
    private final Object lock = new Object();

    public void newMessage(String x) {
        synchronized (lock) {
            message = x;
            System.out.println(message);
        }
    }

    public String getMessage() {
        synchronized (lock) {
            String temp = message;
            System.out.println(message);
            message = null;
            return temp;
        }
    }

}
class test1 implements Runnable{
	Sample s1;
	public test1(Sample s) {
		// TODO Auto-generated constructor stub
		this.s1 = s;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		s1.newMessage("123");
	}
	
}

class test2 implements Runnable{
	Sample s1;
	public test2(Sample s) {
		// TODO Auto-generated constructor stub
		this.s1 = s;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		s1.getMessage();
	}
	
}

public class DeadlockEx {
	public static void main(String[] args) {
		Object obj1 = new Object();
		Object obj2 = new Object();
		
		Thread t1 = new Thread(()-> {
			synchronized (obj1) {
				System.out.println("Get Obj1 lock");
				
				try {
					Thread.sleep(2000);
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				System.out.println("Wait for Obj2 lock");
				synchronized (obj2) {
					System.out.println("Get Obj2 lock");

				}
			}
		},"thread1");
		
		Thread t2 = new Thread(()-> {
			synchronized (obj2) {
				System.out.println("Get Obj2 lock");
				
				try {
					Thread.sleep(2000);
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				System.out.println("Wait for Obj1 lock");
				synchronized (obj1) {
					System.out.println("Get Obj1 lock");

				}
			}
		},"thread2");
		
//		t1.start();
//		t2.start();
		Sample ss = new Sample();
		Thread t3 = new Thread(new test1(ss));
		Thread t4 = new Thread(new test2(ss));

		t4.start();
		t3.start();

		
		
	}
}
