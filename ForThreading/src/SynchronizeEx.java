import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

class Sy{
	synchronized void normalCal(int in ,String name) {
		for(int i =0;i<=5;i++) {
			in= in+1;
			System.out.println(name+" normalCal: "+in);

		}
	}
	synchronized static void staticCal(int in, String name) {
		for (int i = 0; i <= 5; i++) {
			in++;
			System.out.println(name+" staticCal: "+in);

		}
	}

	public static void staticMethodB() {
		synchronized (Sy.class) {
			System.out.println("static method different way to write");
		}
	}

	public void nonStaticMethodC() {
		synchronized (this.getClass()) {
			System.out.println("non-static method different way to write");
		}
	}
}
class Bank{
	
}
public class SynchronizeEx {
	public static void main(String[] args) throws InterruptedException {
		Sy s = new Sy();
//		ExecutorService service = Executors.newCachedThreadPool();
//		IntStream.range(0, 10)
//	      .forEach(count -> 
//	        service.submit(() -> {
//				s.normalCal(5);
//			}));
//		IntStream.range(0, 10)
//	      .forEach(count -> 
//	        service.submit(() -> {
//				s.staticCal(6);
//			}));
//		service.awaitTermination(10, TimeUnit.MILLISECONDS);
		
		
		Thread t1 = new Thread(() -> {
			s.normalCal(5,"n1");
		});
		Thread t2 = new Thread(() -> {
			s.normalCal(10,"n2");
		});
		t1.start();
		t2.start();
		Thread t3 = new Thread(() -> {
			s.staticCal(5,"s1");
		});
		Thread t4 = new Thread(() -> {
			s.staticCal(10,"s2");
		});
		t3.start();
		t4.start();
	}
}
	
