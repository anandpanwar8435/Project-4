package in.co.rays.project_4.bean;

import java.sql.Timestamp;
import java.util.Date;


/**
 * user JavaBean encapsulates user attributes
 * @author ajay
 *
 */
public class UserBean extends BaseBean {

	public static final String ACTIVE="Active";
	public static final String INACTIVE="Inactive";
	
	/**
	 * first name of user
	 */
	private String firstName;
	/**
	 * last name of user
	 */
	private String lastName;
	/**
	 * login id name of user
	 */
	private String login;
	/**
	 * password of user
	 */
	private String password;
	/**
	 * confirm password of user
	 */

	private String confirmPassword;
	/**
	 * date of birth of user
	 */
	private Date dob;
	/**
	 * mobile no of user
	 */
	private String mobileNo;
	/**
	 * role id of user
	 */
	long roleId;
	/**
	 * unsuccessfull login of user
	 */
	int unSuccessfullLogin;
	/**
	 * gender of user
	 */
	private String gender;
	/**
	 * last login of user
	 */
	private Timestamp lastLogin;
	/**
	 * lock
	 */
	private String lock;
	/**
	 * user register ip
	 */
	private String registeredIP;
	/**
	 * user last login ip
	 */
	private String lastLoginIP;
	
	/**
	 * accessor
	 */
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public int getUnSuccessfullLogin() {
		return unSuccessfullLogin;
	}
	public void setUnSuccessfullLogin(int unSuccessfullLogin) {
		this.unSuccessfullLogin = unSuccessfullLogin;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Timestamp getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getLock() {
		return lock;
	}
	public void setLock(String lock) {
		this.lock = lock;
	}
	public String getRegisteredIP() {
		return registeredIP;
	}
	public void setRegisteredIP(String registeredIP) {
		this.registeredIP = registeredIP;
	}
	public String getLastLoginIP() {
		return lastLoginIP;
	}
	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}
	public String getKey() {
		return id+"";
	}
	public String getValue() {
		return firstName +""+lastName;
	}
	

}
