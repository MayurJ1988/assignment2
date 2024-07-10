package assignment2;

import java.util.List;

public class UserDefinedUncheckedException {
	
	List<String> list = null;
	public static void main(String[] args) {
		UserDefinedUncheckedException obj1 = new UserDefinedUncheckedException();
		obj1.checkList("Apple");
	}
	public void checkList(String str) {
		
		//throw exception if List is null
		if (list != null) {
			list.add(str);
		} else {
			throw new CustomException("List is NULL");
		}
	}

}
//create a custom unchecked exception class
class CustomException extends RuntimeException {
	
	public CustomException(String message) {
		super(message);
	}
}
