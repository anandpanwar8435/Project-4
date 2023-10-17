package in.co.rays.project_4.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.log4j.Logger;

import in.co.rays.project_4.bean.DropDownListBean;
import in.co.rays.project_4.exception.ApplicationException;
import in.co.rays.project_4.exception.DatabaseException;
import in.co.rays.project_4.util.DataUtility;
import in.co.rays.project_4.util.JDBCDataSource;

/**
 * Base model 
 * @author ajay
 *
 */
public abstract class BaseModel implements Serializable, DropDownListBean,
Comparable<BaseModel>{

	private static Logger log=Logger.getLogger(BaseModel.class);
	
	/**
     * Non Business primary key
     */
	protected long id;
	 /**
     * User name that creates this record.
     */
	protected String createdBy;
	 /**
     * User name that modifies this record.
     */
	protected String modifiedBy;
	/**
     * Created timestamp of record
     */
	protected Timestamp createdDateTime;
	/**
     * Modified timestamp of record
     */
	protected Timestamp modifiedDateTime;
	
	/**
     * accessor methods
     */
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Timestamp getCreatedDateTime() {
		return createdDateTime;
	}
	public void setCreatedDateTime(Timestamp createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	public Timestamp getModifiedDateTime() {
		return modifiedDateTime;
	}
	public void setModifiedDateTime(Timestamp modifiedDateTime) {
		this.modifiedDateTime = modifiedDateTime;
	}
	
	/**
     * Compares IDs ( Primary Key).
     * If keys are equals then objects are equals.
     *
     */
	public int compareTo(BaseModel next){
		return (int)(id-next.getId());
		
	}
	
	 /**
     * created next PK of record
     *
     * @throws DatabaseException
     */
	public long nextPK() throws DatabaseException{
		log.debug("Model nextPK Started");
		Connection conn=null;
		long pk=0;
		
		try{
			conn=JDBCDataSource.getConnection();
		
		PreparedStatement stmt=conn.prepareStatement("select max(id) from"+getTableName());
		
		ResultSet rs=stmt.executeQuery();
		while(rs.next()){
			pk=rs.getInt(1);
			
		}
		rs.close();
	
	}catch (Exception e){
	log.error("Database Exception..",e);
	throw new DatabaseException("Exception:Exception in getting PK");
	}finally{
		JDBCDataSource.closeConnection(conn);
	}
		log.debug("Model nextPk End");
		return pk+1;
	}
	
	/**
     * Gets table name of Model
     *
     * @return
     */
	public abstract String getTableName();
	
	
	/**
     * Updates created by info
     *
     * @throws ApplicationException
     *
     * @throws Exception
     */
	public void updateCreateInfo() throws ApplicationException{
		
		log.debug("Model update started"+createdBy);
		
		Connection conn=null;
		
		String sql="Update"+getTableName()+"set created_by=?,created_datetime=? where id=?";
		
		log.debug(sql);
		try{
			conn=JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement stmt=conn.prepareStatement(sql);
			stmt.setString(1, createdBy);
			stmt.setTimestamp(2, DataUtility.getCurrentTimestamp());
			stmt.setLong(3, id);
			stmt.executeUpdate();
			conn.commit();
			stmt.close();
		}catch(SQLException e){
			log.error("Database Exception..",e);
			JDBCDataSource.trnRollback(conn);
			throw new ApplicationException(e.toString());
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update end");
	}
	
	 /**
     * Updates modified by info
     *
     * @param model
     * @throws Exception
     */
	public void updateModifiedInfo() throws Exception{
		log.debug("Model Update Started");
		Connection conn=null;
		
		String sql="update"+getTableName()+"set modified_by=?,modified_datetime=? where id=?";
		
		
		try{
			conn=JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement stmt=conn.prepareStatement(sql);
			stmt.setString(1,modifiedBy);
			stmt.setTimestamp(2,DataUtility.getCurrentTimestamp());
			stmt.setLong(3, id);
			stmt.executeUpdate();
			conn.commit();
			stmt.close();
			
			
		}catch(SQLException e){
			log.error("Database Exception..",e);
			JDBCDataSource.trnRollback(conn);
			
		}finally{
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}
	
	/**
     * Populate Model from ResultSet
     *
     * @param model
     * @param rs
     * @return
     */
	protected <T extends BaseModel>T populateModel(T model,ResultSet rs) throws SQLException{
		
		model.setId(rs.getLong("Id"));
		model.setCreatedBy(rs.getString("Created_By"));
		model.setModifiedBy(rs.getString("Modified_By"));
		model.setCreatedDateTime(rs.getTimestamp("Created_DateTime"));
		model.setModifiedDateTime(rs.getTimestamp("Modified_DateTime"));
		
		return model;
	}
	}
