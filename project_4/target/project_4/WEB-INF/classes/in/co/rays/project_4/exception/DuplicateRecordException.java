package in.co.rays.project_4.exception;

/**
 * DuplicateRecordException thrown when a duplicate record occurred
 * @author ajay
 *
 */
public class DuplicateRecordException extends Exception{
	
	/**
	 * @param msg
	 * error msg
	 */
	public DuplicateRecordException(String msg){
		super(msg);
	}

}
