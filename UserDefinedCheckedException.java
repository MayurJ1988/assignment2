package assignment2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class UserDefinedCheckedException {

	public static void main(String[] args) {
		
		FileInputStream myFile;
		//Throwing custom exception if file not found
		try {
			myFile = new FileInputStream("temp.txt");
			
		} catch (FileNotFoundException e) {
			try {
				throw new CheckedException("Please check file name or path", e);
			} catch (CheckedException e2) {
				e2.getMessage();
				e2.printStackTrace();
			}
		}
	}

}
//Creating custom checked exception class
class CheckedException extends Exception {
	
	public CheckedException(String message, Throwable th) {
		super(message, th);
	}
}