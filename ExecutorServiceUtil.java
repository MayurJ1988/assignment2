package assignment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceUtil {

	public static void main(String[] args) {
		Service service = new Service();
		//Creating object of ExecutorService
		ExecutorService exeService = Executors.newFixedThreadPool(3);
		exeService.execute(service);
		exeService.shutdown();
		try {
			exeService.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			System.out.println(e);
		}
		
	}

}
class Service implements Runnable {

	@Override
	public void run() {
		
		try {
			String basePath = new File("input.txt").getAbsolutePath();
			//Creating file object and Scanner object of file
			File in = new File(basePath);
			Scanner input = new Scanner(in);
			//Creating file writter object
			File out = new File(new File("output.txt").getAbsolutePath());
			PrintWriter output = new PrintWriter(out);
			//Creating Queue to store data from file
			Queue<String> queue = new LinkedList<String>();
			//Reading data from file and storing in Queue
			while (input.hasNext()) {
				queue.add(input.nextLine());
			}
			//Writting data to another file from Queue
			while (queue.peek() != null) {
				output.write(queue.remove());
				output.write("\n");
			}
			output.flush();
			
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}
	
}
