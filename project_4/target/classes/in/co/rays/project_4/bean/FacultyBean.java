package in.co.rays.project_4.bean;

import java.util.Date;


/**
 *  faculty JavaBean encapsulates faculty attributes
 * @author ajay
 *
 */
public class FacultyBean extends BaseBean{
	
	/**
	 * name of faculty
	 */
	private String firstName;
	/**
	 * lastName of faculty
	 */
	private String lastName;
	/**
	 * gender of faculty
	 */
	private String gender;
	/**
	 * joining Date of faculty
	 */
	private Date joiningDate;
	/**
	 * qualification of faculty
	 */
	private String qualification;
	/**
	 * email Id of faculty
	 */
	private String emailId;
	/**
	 * mobile no of faculty
	 */
	private String mobileNo;
	/**
	 * colleg id of faculty
	 */
	private long collegeId;
	/**
	 * college Name of faculty
	 */
	private String collegeName;
	/**
	 * course Id of faculty
	 */
	private long courseId;
	/**
	 * course name of faculty
	 */
	private String courseName;
	/**
	 * subject Id of faculty
	 */
	private long subjectId;
	/**
	 * subject name of faculty
	 */
	private String subjectName;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(long collegeId) {
		this.collegeId = collegeId;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	
	public String getKey() {
		// TODO Auto-generated method stub
		return id+"";
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return firstName+""+lastName;
	}
	
}
