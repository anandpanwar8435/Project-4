package in.co.rays.project_4.bean;


/**
 * subject JavaBean encapsulates subject attributes
 * @author ajay
 *
 */
public class SubjectBean extends BaseBean{

	/**
	 * name of subject
	 */
	private String subjectName;
	/**
	 * description of subject
	 */
	private String description;
	/**
	 * name of course
	 */
	private String courseName;
	/**
	 * id of course
	 */
	private long courseId;
	/**
	 * id of subject
	 */
	private long subjectId;
	/**
	 * accessor
	 */	
	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public String getKey() {
		// TODO Auto-generated method stub
		return id+"";
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return subjectName;
	}	
}
