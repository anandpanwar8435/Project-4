package in.co.rays.project_4.exception;


/**
 * DatabaseException is prpogated by DAO classes when an unhandled Database
 * exception occurred
 * @author ajay
 *
 */
public class DatabaseException extends Exception{
	
	/**
	 * @param msg
	 * error message
	 */
	public DatabaseException(String msg){
		super(msg);
	}

}
