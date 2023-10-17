package in.co.rays.project_4.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.project_4.bean.CollegeBean;
import in.co.rays.project_4.bean.CourseBean;
import in.co.rays.project_4.bean.FacultyBean;
import in.co.rays.project_4.bean.SubjectBean;
import in.co.rays.project_4.exception.ApplicationException;
import in.co.rays.project_4.exception.DatabaseException;
import in.co.rays.project_4.exception.DuplicateRecordException;
import in.co.rays.project_4.util.JDBCDataSource;

/**
 * JDBC Implements of faculty model
 * @author ajay
 *
 */
public class FacultyModel {
	
	private static Logger log= Logger.getLogger(FacultyModel.class);
	
	/**
	 * new id create
	 * @return pk
	 * @throws DatabaseException
	 */
	public Integer nextPK() throws DatabaseException{
		
		log.debug("Model nextPk started");
		
		Connection conn=null;
		int pk=0;
		try{
			conn=JDBCDataSource.getConnection();
			PreparedStatement stmt=conn.prepareStatement("Select max(id) from st_faculty");
			
			ResultSet rs=stmt.executeQuery();
			
			while(rs.next()){
				pk=rs.getInt(1);
			}
			rs.close();
		}catch(Exception e){
			log.error("DataBase Exception",e);
			throw new DatabaseException("Exception: Exception in getting pk");
			
		}finally{
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK end");
		return pk+1;
	}
	
	/**
	 * add new faculty
	 * @param fbean
	 * @return pk
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 */
	public long add(FacultyBean fbean) throws ApplicationException, DuplicateRecordException {
		
		Connection conn=null;
		System.out.println("tttttttttttttttttttttttttttttttttt"+fbean);
		long pk = 0;
		CollegeModel model = new CollegeModel();
		CollegeBean bean = model.findByPK(fbean.getCollegeId());
		String CollegeName = bean.getName();

		CourseModel model1 = new CourseModel();
		CourseBean bean1 = model1.findByPK(fbean.getCourseId());
		String CourseName = bean1.getCourseName();

		SubjectModel model2 = new SubjectModel();
		SubjectBean bean2 = model2.findByPK(fbean.getSubjectId());
		String SubjectName = bean2.getSubjectName();

		FacultyBean duplicataRole = findByName(fbean.getFirstName());
		// Check if create Faculty already exist
		if (duplicataRole != null) {
			throw new DuplicateRecordException("Faculty already exists");
		}

		java.util.Date d = fbean.getJoiningDate();
		long l = d.getTime();
		java.sql.Date date = new java.sql.Date(l);
		System.out.println(date);
		try {
			pk = nextPK();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn
					.prepareStatement("insert into st_faculty values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setLong(1, pk);
			ps.setString(2, fbean.getFirstName());
			ps.setString(3, fbean.getLastName());
			ps.setString(4, fbean.getGender());
			ps.setDate(5,date);
			ps.setString(6, fbean.getQualification());
			ps.setString(7, fbean.getEmailId());
			ps.setString(8, fbean.getMobileNo());
			ps.setLong(9, fbean.getCollegeId());
			ps.setString(10,CollegeName);
			ps.setLong(11, fbean.getCourseId());
			ps.setString(12,CourseName);
			ps.setLong(13, fbean.getSubjectId());
			ps.setString(14,SubjectName);
			ps.setString(15, fbean.getCreatedBy());
			ps.setString(16, fbean.getModifiedBy());
			ps.setTimestamp(17, fbean.getCreatedDateTime());
			ps.setTimestamp(18, fbean.getModifiedDateTime());
			int a = ps.executeUpdate();
			System.out.println("insert data" + a);
			ps.close();
			conn.commit();
			System.out.println("pppppppppppppppppppppppp"+a);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return 0;

	}
	
	/**
	 * find faculty by email id
	 * @param emailId
	 * @return fbean
	 * @throws ApplicationException 
	 */
	public FacultyBean findByEmail(String emailId) throws ApplicationException {
		FacultyBean fbean=null;
		
		Connection conn=null;
		try{
			conn=JDBCDataSource.getConnection();
		    PreparedStatement ps=conn.prepareStatement("select * from st_faculty where EMAIL_ID=?");
			ps.setString(1, emailId );
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				fbean = new FacultyBean();
				fbean.setId(rs.getLong(1));
				fbean.setFirstName(rs.getString(2));
				fbean.setLastName(rs.getString(3));
				fbean.setGender(rs.getString(4));
				fbean.setJoiningDate(rs.getDate(5));
				fbean.setQualification(rs.getString(6));
				fbean.setEmailId(rs.getString(7));
				fbean.setMobileNo(rs.getString(8));
				fbean.setCollegeId(rs.getLong(9));
				fbean.setCollegeName(rs.getString(10));
				fbean.setCourseId(rs.getLong(11));
				fbean.setCourseName(rs.getString(12));
				fbean.setSubjectId(rs.getLong(13));
				fbean.setSubjectName(rs.getString(14));
				fbean.setCreatedBy(rs.getString(15));   
		         fbean.setModifiedBy(rs.getString(16));
		         fbean.setCreatedDateTime(rs.getTimestamp(17));
		         fbean.setModifiedDateTime(rs.getTimestamp(18));

			}
			ps.close();
			conn.close();

			
		}catch (Exception e) 
	     {
	    	 throw new ApplicationException("exception in faculty findByEmail  add..... "+e.getMessage());                
	     } finally 
	     {
	       JDBCDataSource.closeConnection(conn);
	     }

		return fbean;
	}

	
	
	/**
	 * delete faculty
	 * @param bean
	 * @throws ApplicationException
	 */
	public void delete(FacultyBean bean) throws ApplicationException{
		
		log.debug("Model delete started");
		Connection conn= null;
		try{
			conn=JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement stmt = conn.prepareStatement("Delete from st_faculty where ID =?");
			stmt.setLong(1, bean.getId());
			stmt.executeUpdate();
			conn.commit();
			stmt.close();
		} catch (Exception e) {
		log.error("Database Exception..", e);
		try {
		conn.rollback();
		} catch (Exception ex) {
		throw new ApplicationException("Exception : Delete rollback exception "+ ex.getMessage());
		}
		throw new ApplicationException("Exception : Exception in delete college");
		} finally {
		JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete end");
		}
	
	/**
	 * find faculty by name
	 * 
	 * @param firstName
	 * @return bean
	 * @throws ApplicationException
	 */
	public FacultyBean findByName(String firstname) throws ApplicationException{
		log.debug("Model FindByName Started ");
		StringBuffer sql=new StringBuffer("Select * from st_faculty where first_name=?");
		
		
		FacultyBean bean=null;
		Connection conn=null;
		try{
			conn=JDBCDataSource.getConnection();
			PreparedStatement stmt=conn.prepareStatement(sql.toString());
			stmt.setString(1,firstname);
			
			ResultSet rs=stmt.executeQuery();
			
			while(rs.next()){
				bean=new FacultyBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setGender(rs.getString(4));
				bean.setJoiningDate(rs.getDate(5));
				bean.setQualification(rs.getString(6));
				bean.setEmailId(rs.getString(7));
				bean.setMobileNo(rs.getString(8));
				bean.setCollegeId(rs.getLong(9));
				bean.setCollegeName(rs.getString(10));
				bean.setCourseId(rs.getLong(11));
				bean.setCourseName(rs.getString(12));
				bean.setSubjectId(rs.getLong(13));
				bean.setSubjectName(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(16));
				bean.setCreatedDateTime(rs.getTimestamp(17));
				bean.setModifiedDateTime(rs.getTimestamp(18));
				
			}rs.close();
			
			
		}catch(Exception e){
			log.error("Database Exception..",e);
			throw new ApplicationException("Exception:Exception in getting Faculty by Name");
			
			
		}finally{
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model  findByName Ended");
		return bean;
	}
	
	/**
	 * find information with the help of pk
	 * @param pk
	 * @return bean
	 * @throws ApplicationException
	 */
	public FacultyBean findByPK(long pk) throws ApplicationException{
		log.debug("Model find by pk started");
		
		FacultyBean bean=null;
		Connection conn=null;
		
		try{
			conn=JDBCDataSource.getConnection();
			PreparedStatement stmt=conn.prepareStatement("Select * from st_faculty where id=? ");
			stmt.setLong(1, pk);
			
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				bean=new FacultyBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setGender(rs.getString(4));
				bean.setJoiningDate(rs.getDate(5));
				bean.setQualification(rs.getString(6));
				bean.setEmailId(rs.getString(7));
				bean.setMobileNo(rs.getString(8));
				bean.setCollegeId(rs.getLong(9));
				bean.setCollegeName(rs.getString(10));
				bean.setCourseId(rs.getLong(11));
				bean.setCourseName(rs.getString(12));
				bean.setSubjectId(rs.getLong(13));
				bean.setSubjectName(rs.getString(14));
				bean.setCreatedBy(rs.getString(15));
				bean.setModifiedBy(rs.getString(16));
				bean.setCreatedDateTime(rs.getTimestamp(17));
				bean.setModifiedDateTime(rs.getTimestamp(18));
				
			}rs.close();
			
		}catch(Exception e){
			log.error("Database Exception..",e);
			throw new ApplicationException("Exception:Exception in getting Faculty by pk");
		}finally{
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model  findByPK End");
		return bean;
		
	}
	
	/**
	 * update faculty information
	 * @param bean
	 * @throws ApplicationException
	 * @throws DatabaseException
	 */
	public void update(FacultyBean bean) throws ApplicationException, DuplicateRecordException{
		log.debug("Model update Started");
		Connection conn=null;
		
		FacultyBean beanexist=findByName(bean.getFirstName());
	
		CollegeModel model = new CollegeModel();
		CollegeBean bean1 = model.findByPK(bean.getCollegeId());
		String CollegeName = bean1.getName();

		CourseModel model1 = new CourseModel();
		CourseBean bean2 = model1.findByPK(bean.getCourseId());
		String CourseName = bean2.getCourseName();

		SubjectModel model2 = new SubjectModel();
		SubjectBean bean3 = model2.findByPK(bean.getSubjectId());
		String SubjectName = bean3.getSubjectName();

		
		java.util.Date d = bean.getJoiningDate();
		long l = d.getTime();
		java.sql.Date date = new java.sql.Date(l);
		
		if(beanexist!=null&&beanexist.getId()!=bean.getId()){
			throw new DuplicateRecordException("Faculty is already exist");
	}
		try{
			conn=JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			
			PreparedStatement stmt=conn.prepareStatement("Update st_faculty set first_name=?,last_name=?,gender=?,joining_date=?,qualification=?,email_id=?,mobile_no=?,college_id=?,college_name=?,course_id=?,course_name=?,subject_id=?,subject_name=?,created_by=?,modified_by=?,created_date_time=?,modified_date_time=? where id=?");

			stmt.setString(1, bean.getFirstName());
			stmt.setString(2,bean.getLastName());
			stmt.setString(3,bean.getGender());
			stmt.setDate(4,date);
			stmt.setString(5,bean.getQualification());
			stmt.setString(6,bean.getEmailId());
			stmt.setString(7,bean.getMobileNo());
			stmt.setLong(8,bean.getCollegeId());
			stmt.setString(9,CollegeName);
			stmt.setLong(10, bean.getCourseId());
			stmt.setString(11,CourseName);
			stmt.setLong(12,bean.getSubjectId());
			stmt.setString(13,SubjectName);
			stmt.setString(14,bean.getCreatedBy());
			stmt.setString(15,bean.getModifiedBy());
			stmt.setTimestamp(16,bean.getCreatedDateTime());
			stmt.setTimestamp(17,bean.getModifiedDateTime());
			stmt.setLong(18, bean.getId());
			
			stmt.executeUpdate();
			conn.commit();
			stmt.close();
		
		}catch(Exception e){
			e.printStackTrace();
			log.error("Database Exception..",e);
		
			try{
				conn.rollback();
			}catch(Exception ex){
		ex.printStackTrace();
			throw new ApplicationException("Exception:delete rollback exception"+ex.getMessage());
			
			}
			throw new ApplicationException("Exception in updating Faculty");
		}finally{
				JDBCDataSource.closeConnection(conn);
			}
			log.debug("Model update End");
			
	}	
	/**
	 * to search list of faculty
	 * @param bean
	 * @param pageNo
	 * @param pageSize
	 * @return list
	 * @throws ApplicationException
	 */  
	public List search(FacultyBean bean, int pageNo, int pageSize)throws ApplicationException {
		log.debug("Model search Started");
		
		StringBuffer sql = new StringBuffer("select * from st_faculty where 1=1");
		
		if (bean != null) {
		if (bean.getId() > 0) {
		sql.append(" AND id = " + bean.getId());
		}
		
		if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
		sql.append(" AND First_NAME like '" + bean.getFirstName() + "%'");
		}
		if (bean.getLastName() != null && bean.getLastName().length() > 0) {
		sql.append(" AND Last_Name like '" + bean.getLastName() + "%'");
		}
		if (bean.getGender() != null && bean.getGender().length() > 0) {
			sql.append(" AND Gender like '" + bean.getGender() + "%'");
			}
		if (bean.getJoiningDate() != null &&(((CharSequence) bean.getJoiningDate()).length()>0 )) {
			sql.append(" AND Joining_Date like '" + bean.getJoiningDate() + "%'");
			}
		if (bean.getQualification() != null && bean.getQualification().length() > 0) {
			sql.append(" AND Qualification like '" + bean.getQualification() + "%'");
			}
		if (bean.getEmailId() != null && bean.getEmailId().length() > 0) {
			sql.append(" AND Email_Id like '" + bean.getEmailId() + "%'");
			}
		if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {
			sql.append(" AND Mobile_No like '" + bean.getMobileNo() + "%'");
			}
		if (bean.getCollegeId() != 0) {
			sql.append(" AND College_Id like '" + bean.getCollegeId() + "%'");
			}
		if (bean.getCollegeName() != null && bean.getCollegeName().length() > 0) {
			sql.append(" AND College_Name like '" + bean.getCollegeName() + "%'");
			}
		if (bean.getCourseId() != 0 ) {
			sql.append(" AND Course_ID like '" + bean.getCourseId() + "%'");
			}
		if (bean.getCourseName() != null && bean.getCourseName().length() > 0) {
			sql.append(" AND Course_Name like '" + bean.getCourseName() + "%'");
			}
		if (bean.getSubjectId() != 0 ) {
			sql.append(" AND Subject_Id like '" + bean.getSubjectId() + "%'");
			}
		if (bean.getSubjectName() != null && bean.getSubjectName().length() > 0) {
			sql.append(" AND Subject_Name like '" + bean.getSubjectName() + "%'");
			}
		}
		
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
		// Calculate start record index
		pageNo = (pageNo - 1) * pageSize;
		
		sql.append(" Limit " + pageNo + ", " + pageSize);
		// sql.append(" limit " + pageNo + "," + pageSize);
		}
		
		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
		conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
		bean = new FacultyBean();
		bean.setId(rs.getLong(1));
		bean.setFirstName(rs.getString(2));
		bean.setLastName(rs.getString(3));
		bean.setGender(rs.getString(4));
		bean.setJoiningDate(rs.getDate(5));
		bean.setQualification(rs.getString(6));
		bean.setEmailId(rs.getString(7));
		bean.setMobileNo(rs.getString(8));
		bean.setCollegeId(rs.getLong(9));
		bean.setCollegeName(rs.getString(10));
		bean.setCourseId(rs.getLong(11));
		bean.setCourseName(rs.getString(12));
		bean.setSubjectId(rs.getLong(13));
		bean.setSubjectName(rs.getString(14));
		bean.setCreatedBy(rs.getString(15));
		bean.setModifiedBy(rs.getString(16));
		bean.setCreatedDateTime(rs.getTimestamp(17));
		bean.setModifiedDateTime(rs.getTimestamp(18));
		list.add(bean);
		}
		rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		log.error("Database Exception..", e);
		throw new ApplicationException("Exception : Exception in search Faculty");
		} finally {
		JDBCDataSource.closeConnection(conn);
		}
		
		log.debug("Model search End");
		return list;
		}
		
	 public List search(FacultyBean bean) throws ApplicationException {
	 return search(bean, 0, 0);
       }
		
	 public List list() throws ApplicationException {
	 return list(0, 0);
	   }
	 
	 /**
		 * to show list of faculty
		 * @param pageNo
		 * @param pageSize
		 * @return list
		 * @throws ApplicationException
		 */
	 public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from st_faculty");
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
		// Calculate start record index
		pageNo = (pageNo - 1) * pageSize;
		sql.append(" limit " + pageNo + "," + pageSize);
		}
		
		Connection conn = null;
		
		try {
		conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
		FacultyBean bean = new FacultyBean();
		bean.setId(rs.getLong(1));
		bean.setFirstName(rs.getString(2));
		bean.setLastName(rs.getString(3));
		bean.setGender(rs.getString(4));
		bean.setJoiningDate(rs.getDate(5));
		bean.setQualification(rs.getString(6));
		bean.setEmailId(rs.getString(7));
		bean.setMobileNo(rs.getString(8));
		bean.setCollegeId(rs.getLong(9));
		bean.setCollegeName(rs.getString(10));
		bean.setCourseId(rs.getLong(11));
		bean.setCourseName(rs.getString(12));
		bean.setSubjectId(rs.getLong(13));
		bean.setSubjectName(rs.getString(14));
		bean.setCreatedBy(rs.getString(15));
		bean.setModifiedBy(rs.getString(16));
		bean.setCreatedDateTime(rs.getTimestamp(17));
		bean.setModifiedDateTime(rs.getTimestamp(18));
		list.add(bean);

		}
		rs.close();
		} catch (Exception e) {
		e.printStackTrace();
		log.error("Database Exception..", e);
		throw new ApplicationException("Exception : Exception in getting list of users");
		} finally {
		JDBCDataSource.closeConnection(conn);
		}
		
		log.debug("Model list End");
		return list;
		
		}

	 }
