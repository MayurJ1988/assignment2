package assignment2;

public class TablePrinting {

	public static void main(String[] args) throws InterruptedException {
		
		Table table = new Table();
		//Create first thread
		Thread thread1 = new Thread (new Runnable() {
			public void run() {
				table.printTable(2);
			}
		});
		//Create second thread
		Thread thread2 = new Thread (new Runnable() {
			public void run() {
				table.printTable(3);
			}
		});
		thread1.start();
		thread2.start();
		//Thread1 finishes before Thread2
		thread1.join();
		thread2.join();
	}
}
class Table {
	public void printTable(int number) {
		//Synchronized block
		synchronized (this) {
			for(int i=1; i<=10; i++) {
				System.out.println(+number+"*"+i+"="+(number*i));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					System.out.println(e);
				}
			}
		}
	}
	
}
