class Printer {
//	synchronized void printDocuments(int numOfCopies , String docName) {
	void printDocuments(int numOfCopies, String docName) {

		for (int i = 0; i <= numOfCopies; i++) {
			System.out.println(">>Printing :" + docName + " " + i);
		}
	}
}
class Thread1 extends Thread{
	Printer p;
	public Thread1(Printer thisP) {
		// TODO Auto-generated constructor stub
		this.p = thisP;
	}
	@Override
	public void run() {
		synchronized (p) {
			p.printDocuments(10, "Cloud's DLC");
		}
		
	}
}
class Thread2 extends Thread{
	Printer p;
	public Thread2(Printer thisP) {
		// TODO Auto-generated constructor stub
		this.p = thisP;
	}
	@Override
	public void run() {
		synchronized (p) {
			p.printDocuments(5, "Tifa's DLC");
		}
		
	}
}


public class MultiThreadBasic {
	public static void main(String[] args) {
		System.out.println("----------------App start-------------");
//		Scenario is that we have multiple thread working on the same Printer Object
//		If Multiple threads are going to work on the same object we must Synchronize them 
		Printer print = new Printer();
		Thread1 t = new Thread1(print);
		Thread2 t2 = new Thread2(print);
		t.setPriority(8);
//		t2.setPriority(8);
//		System.out.println(t2.getPriority());
		t.start();
//		try {
//			t.join();
//		}catch (InterruptedException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
		t2.start();
		System.out.println("----------------App finished-------------");
	}
}
