package assignment2;

import java.util.LinkedList;

public class ThreadExample {
	public static void main(String[] args) throws InterruptedException 
	{
		Example example = new Example();
		// Create producer thread
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					example.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		// Create consumer thread
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					example.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t1.start();
		t2.start();
		// t1 finishes before t2
		t1.join();
		t2.join();
	}

	// This class has a list, producer (adds items to list
	// and consumer (removes items).
	public static  class Example {

		LinkedList<Integer> list = new LinkedList<>();
		int capacity = 2;
		int value = 0;

		// Function called by producer thread
		public void produce() throws InterruptedException {
			while (value < 21) {
				synchronized (this) {
					// producer thread waits while list is full
					while (list.size() == capacity)
						wait();
					System.out.println("Producer produced-" + value);
					list.add(value++);
					// notifies the consumer thread
					notify();
					Thread.sleep(1000);
				}
			}
		}

		// Function called by consumer thread
		public void consume() throws InterruptedException {
			while (value < 21) {
				synchronized (this) {
					// consumer thread waits while list is empty
					while (list.size() == 0)
						wait();
					int val = list.removeFirst();
					System.out.println("Consumer consumed-" + val);
					// Wake up producer thread
					notify();
					Thread.sleep(1000);
				}
			}
		}
	}

}
