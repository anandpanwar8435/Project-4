package in.co.rays.project_4.bean;

import java.util.Date;

/**
 * imeTable JavaBean encapsulates TimeTable attributes
 * @author ajay
 *
 */
public class TimeTableBean extends BaseBean{
	
	/**
	 * subject id of timetable
	 */
	private long subId;
	/**
	 * subject name of time table
	 */
	private String subName;
	/**
	 * course id of timetable
	 */
	private long courseId;
	/**
	 * course name of timetable
	 */
	private String courseName;
	/**
	 * semester of college
	 */
	private String semester;
	/**
	 * exam date 
	 */
	private Date examDate;
	/**
	 * exam time
	 */
	private String examTime;
	/**
	 * description of timetable
	 */
	private String description;
	
	/**
	 * accessor
	 */
	public long getSubId() {
		return subId;
	}
	public void setSubId(long subId) {
		this.subId = subId;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
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
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public Date getExamDate() {
		return examDate;
	}
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}
	public String getExamTime() {
		return examTime;
	}
	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getKey() {
		// TODO Auto-generated method stub
		return id+"";
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return subName;
	}	
	

}
