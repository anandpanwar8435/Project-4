package in.co.rays.project_4.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project_4.bean.RoleBean;
import in.co.rays.project_4.exception.ApplicationException;
import in.co.rays.project_4.exception.DuplicateRecordException;
import in.co.rays.project_4.model.RoleModel;

public class RoleModelTest {
	
	public static RoleModel model=new RoleModel();
	
	public static void main(String[] args) throws SQLException {
		
//		testAdd();
//		testDelete();
//		testUpdate();
//		testFindByPK();  //done
//		testFindByName();  //done
//		testSearch();	//done           
//		testList();		//done
		
	}
	
	public static void testAdd() throws SQLException{
		
		try{
			RoleBean bean=new RoleBean();
			bean.setId(1);
			bean.setName("Ajay");
			bean.setDescription("sadf");
			long pk=model.add(bean);
			
			RoleBean addedbean=model.findByPK(pk);
			if(addedbean==null){
				System.out.println("Test add fail");
			}
		}catch(ApplicationException e){
			e.printStackTrace();
		}catch(DuplicateRecordException e){
			e.printStackTrace();
		}
		
	}
	 public static void testDelete() {

	        try {
	            RoleBean bean = new RoleBean();
	            long pk = 0L;
	            bean.setId(pk);
	            model.delete(bean);
	            RoleBean deletedbean = model.findByPK(pk);
	            if (deletedbean != null) {
	                System.out.println("Test Delete fail");
	            }
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	    }

	    
	    public static void testUpdate() {

	        try {
	            RoleBean bean = model.findByPK(1L);
	            bean.setName("vijay");
	            bean.setDescription("Ejjjjjjjjng");
	            model.update(bean);

	            RoleBean updatedbean = model.findByPK(1L);
	            if (!"vijay".equals(updatedbean.getName())) {
	                System.out.println("Test Update fail");
	            }
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        } catch (DuplicateRecordException e) {
	            e.printStackTrace();
	        }
	    }

	    
	    public static void testFindByPK() {
	        try {
	            RoleBean bean = new RoleBean();
	            long pk = 1L;
	            bean = model.findByPK(pk);
	            if (bean == null) {
	                System.out.println("Test Find By PK fail");
	            }
	            System.out.println(bean.getId());
	            System.out.println(bean.getName());
	            System.out.println(bean.getDescription());
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }

	    }

	    
	    public static void testFindByName() {
	        try {
	            RoleBean bean = new RoleBean();
	            bean = model.findByName("admin");
	            if (bean == null) {
	                System.out.println("Test Find By PK fail");
	            }
	            System.out.println(bean.getId());
	            System.out.println(bean.getName());
	            System.out.println(bean.getDescription());
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	    
	    }


	    public static void testSearch() {

	        try {
	            RoleBean bean = new RoleBean();
	            List list = new ArrayList();
	            bean.setName("admin");
	            list = model.search(bean, 0, 0);
	            if (list.size() < 0) {
	                System.out.println("Test Serach fail");
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) {
	                bean = (RoleBean) it.next();
	                System.out.println(bean.getId());
	                System.out.println(bean.getName());
	                System.out.println(bean.getDescription());
	            }

	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }

	    }

	   
	    public static void testList() {

	        try {
	            RoleBean bean = new RoleBean();
	            List list = new ArrayList();
	            list = model.list(1, 10);
	            if (list.size() < 0) {
	                System.out.println("Test list fail");
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) {
	                bean = (RoleBean) it.next();
	                System.out.println(bean.getId());
	                System.out.println(bean.getName());
	                System.out.println(bean.getDescription());
	            }

	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	    }

}
