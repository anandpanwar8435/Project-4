package in.co.rays.project_4.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import in.co.rays.project_4.bean.RoleBean;
import in.co.rays.project_4.exception.ApplicationException;
import in.co.rays.project_4.exception.DatabaseException;
import in.co.rays.project_4.exception.DuplicateRecordException;
import in.co.rays.project_4.util.JDBCDataSource;
import java.sql.Connection;


/**
 * JDBC Implement of role model
 * @author ajay
 *
 */
public class RoleModel {

	private static Logger log=Logger.getLogger(RoleModel.class);
	
	/**
	 * create id 
	 * @return pk
	 * @throws DatabaseException
	 */
  public Integer nextPK() throws DatabaseException{
		
		log.debug("Model nextPk started");
		
		Connection conn=null;
		int pk=0;
		try{
			conn=JDBCDataSource.getConnection();
			PreparedStatement stmt=conn.prepareStatement("Select max(id) from st_role");
			
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
	 * add new role 
	 * @param bean
	 * @return pk
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 */
	public long add(RoleBean bean) throws ApplicationException, DuplicateRecordException, SQLException{
		log.debug("Model add Started ");
		
		Connection conn=null;
		int pk=0;
		
//		RoleBean duplicateRole=findByName(bean.getName());
//		if(duplicateRole!=null){
//			throw new DuplicateRecordException("Role already exist");
//			
//		}
		try{
			conn=JDBCDataSource.getConnection();
			pk=nextPK();
			conn.setAutoCommit(false);
			PreparedStatement stmt=conn.prepareStatement("insert into st_role values(?,?,?,?,?,?,?)");
			stmt.setInt(1, pk);
			stmt.setString(2, bean.getName());
			stmt.setString(3, bean.getDescription());
			stmt.setString(4,bean.getCreatedBy());
			stmt.setString(5, bean.getModifiedBy());
			stmt.setTimestamp(6,bean.getCreatedDateTime());
			stmt.setTimestamp(7,bean.getModifiedDateTime());
			
			stmt.executeUpdate();
			conn.commit();
			stmt.close();
		
		}catch(Exception e){
			e.printStackTrace();
			log.error("Database Exception..",e);
			
			try{
				conn.rollback();
			}catch(Exception ex){
				throw new ApplicationException("Exception:add rollback exception"+ex.getMessage());
				
			}
			throw new ApplicationException("Exception:Exception in add Role");
			
		}finally{
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model and End");
		return pk;
	}
	
	/**
	 * delete role
	 * @param bean
	 * @throws ApplicationException
	 */
	public void delete(RoleBean bean) throws ApplicationException{
		log.debug("Model deete Started");
		Connection conn=null;
		
		try{
			conn=JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement stmt= conn.prepareStatement("delete from st_role where id=?");
			stmt.setLong(1,bean.getId());
			stmt.executeUpdate();
			conn.commit();
			stmt.close();
			
		}catch(Exception e){
			log.error("Database Exception..",e);
			try{
				conn.rollback();
			}catch(Exception ex){
				throw new ApplicationException ("Exception: Delete rollback exception"+ex.getMessage());
				
			}throw new ApplicationException("Exception:Exception in delete Role");
			
		}finally{
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started ");
	}
	
	/**
	 * find role with the help of name
	 * @param name
	 * @return bean
	 * @throws ApplicationException
	 */
	public RoleBean findByName(String name) throws ApplicationException{
		log.debug("Model findBy EmailId Started");
		
		StringBuffer sql=new StringBuffer("select * from st_role where name=?");
		
		RoleBean bean=null;
		Connection conn=null;
		
		try{
			conn=JDBCDataSource.getConnection();
			PreparedStatement stmt=conn.prepareStatement(sql.toString());
			stmt.setString(1,name);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				bean=new RoleBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setModifiedBy(rs.getString(5));
				bean.setCreatedDateTime(rs.getTimestamp(6));
				bean.setModifiedDateTime(rs.getTimestamp(7));
				
			}
			rs.close();
		}catch(Exception e){
			log.error("Database Exception.. ",e);
			throw new ApplicationException("Exception:Exception in getting User by emailId");
			
		}finally{
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBy EmailId End");
		return bean;
	}
	
	/**
	 * find by role with the help of role
	 * @param pk
	 * @return bean
	 * @throws ApplicationException
	 */
	public RoleBean findByPK(long pk) throws ApplicationException{
		log.debug("Model findByPK started");
		
		StringBuffer sql=new StringBuffer("Select * from st_role where id=?");
		RoleBean bean=null;
		Connection conn=null;
		try{
			conn=JDBCDataSource.getConnection();
			PreparedStatement stmt=conn.prepareStatement(sql.toString());
			stmt.setLong(1, pk);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				bean=new RoleBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setCreatedDateTime(rs.getTimestamp(6));
				bean.setModifiedDateTime(rs.getTimestamp(7));
			}
			rs.close();
		}catch(Exception e){
			log.error("Database Exception.. ",e);
			throw new ApplicationException("Exception : Exception in getting User by PK");
			
		}finally{
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findbyPk End");
		return bean;
	}
	
	/**
	 * update role 
	 * @param bean
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 */
	public void update(RoleBean bean) throws DuplicateRecordException, ApplicationException{
		log.debug("Model Update Started");
		Connection conn=null;
		
		RoleBean duplicateRole=findByName(bean.getName());
		
		if(duplicateRole!=null && duplicateRole.getId()!=bean.getId()){
			throw new DuplicateRecordException("Role already exsits");
			
		}
		try{
			conn=JDBCDataSource.getConnection();
			 conn.setAutoCommit(false);
			PreparedStatement stmt=conn.prepareStatement("update st_role set name=?,description=?,created_by=?,modified_by=?,created_date_time=?,modified_date_time=? where id=?");
			
			
			stmt.setString(1, bean.getName());
			stmt.setString(2, bean.getDescription());
			stmt.setString(3,bean.getCreatedBy());
			stmt.setString(4, bean.getModifiedBy());
			stmt.setTimestamp(5,bean.getCreatedDateTime());
			stmt.setTimestamp(6,bean.getModifiedDateTime());
			stmt.setLong(7, bean.getId());
			
			stmt.executeUpdate();
			conn.commit();
			stmt.close();
			
		}catch(Exception e){
			log.error("Database exception..",e);
			try{
				conn.rollback();
			}catch(Exception ex){
				throw new ApplicationException("Exception:delete rollback exception"+ex.getMessage());
				
				
			}
			throw new ApplicationException("Exception in updating Role");
			
		}finally{
			JDBCDataSource.closeConnection(conn);
			
		}log.debug("Model update End");
		
	}
	
	public List search(RoleBean bean) throws ApplicationException{
		return search(bean,0,0);
	}
	
	/**
	 * search role
	 * @param bean
	 * @param pageNo
	 * @param pageSize
	 * @return list
	 * @throws ApplicationException
	 */
	public List search(RoleBean bean,int pageNo,int pageSize) throws ApplicationException{
		log.debug("Model search Started");
		StringBuffer sql=new StringBuffer("select * from st_role where 1=1");
	
		if(bean!=null){
			if(bean.getId()>0){
				sql.append(" AND id= "+bean.getId());
				
			}
			if(bean.getName()!=null && bean.getName().length()>0){
				sql.append(" AND NAME like '"+bean.getName()+"%'");
			}
			if(bean.getDescription()!=null &&bean.getDescription().length()>0){
				sql.append(" AND DESCRIPTION like '"+bean.getDescription()+"%'");
				
			}
			
		}
		if(pageSize>0){
			pageNo=(pageNo-1)*pageSize;
			sql.append(" Limit "+pageNo+","+pageSize);
		}
		ArrayList list=new ArrayList();
		Connection conn=null;
		
		try{
			conn=JDBCDataSource.getConnection();
			PreparedStatement stmt=conn.prepareStatement(sql.toString());
			
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				bean=new RoleBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setCreatedDateTime(rs.getTimestamp(6));
				bean.setModifiedDateTime(rs.getTimestamp(7));
				list.add(bean);
			}
			rs.close();
		}catch(Exception e){
			log.error("Database Exception.. ",e);
			throw new ApplicationException("Exception : Exception in search Role");
			
			
		}finally{
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model search End");
		return list;
		
	}
	public List list() throws ApplicationException{
		return list(0,0);
	}
	
	/**
	 *list of role
	 * @param pageNo
	 * @param pageSize
	 * @return list
	 * @throws ApplicationException
	 */
	public List list(int pageNo,int pageSize) throws ApplicationException{
		log.debug("Model list Started");
		
		ArrayList list=new ArrayList();
		StringBuffer sql=new StringBuffer("select * from st_role");
	
		if(pageSize>0){
			pageNo=(pageNo-1)*pageSize;
			sql.append(" limit "+pageNo+","+pageSize);
		}
		Connection conn=null;
		try{
			conn=JDBCDataSource.getConnection();
			PreparedStatement stmt=conn.prepareStatement(sql.toString());
			
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				RoleBean bean=new RoleBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setCreatedDateTime(rs.getTimestamp(6));
				bean.setModifiedDateTime(rs.getTimestamp(7));
				list.add(bean);
			}
			rs.close();
		}catch(Exception e){
			log.error("Database Exception.. ",e);
			throw new ApplicationException("Exception : Exception in getting list of Role");
			
			
		}finally{
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model list End");
		return list;
		
	}
	
}
