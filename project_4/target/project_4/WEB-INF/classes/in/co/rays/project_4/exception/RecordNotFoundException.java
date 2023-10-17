package in.co.rays.project_4.exception;

/**
 *  RecordNotFoundException thrown when a record not found occurred
 * @author ajay
 *
 */
public class RecordNotFoundException extends Exception{

	/**
	 * @param msg
	 *      : Error message
	 */
	public RecordNotFoundException(String msg){
		
		super(msg);
	}
}
