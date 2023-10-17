package in.co.rays.project_4.test;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project_4.bean.CollegeBean;
import in.co.rays.project_4.exception.ApplicationException;
import in.co.rays.project_4.exception.DuplicateRecordException;
import in.co.rays.project_4.model.CollegeModel;

public class CollegeModelTest {
	
	public static CollegeModel model=new CollegeModel();

	public static void main(String args[]) throws Exception{
		
//		testAdd();
//		testDelete();
//		testUpdate();
//		testFindByName();  //done
//		testFindByPK();   //done
//		testSearch();   //done
//		testList();  //done
	}	
	
	public static void testAdd() throws Exception{
			
			try{
				CollegeBean bean=new CollegeBean();
				bean.setId(2);
				bean.setName("mitm");
				bean.setAddress("indore");
				bean.setState("mp");
				bean.setCity("indore");
				bean.setPhoneNo("64578876");
				bean.setCreatedBy("wdszc");
				bean.setModifiedBy("asfzxc");
				bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
				bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
				
				long pk=model.add(bean);
				System.out.println("add tested ");
				CollegeBean addedBean=model.findByPK(pk);
				if(addedBean==null){
					System.out.println("fail to add");
				}
			}catch(ApplicationException e){
					e.printStackTrace();
				}
				
			
		}
	
	public static void testDelete(){
		
		try{
			CollegeBean bean=new CollegeBean();
//			long pk=9L;
			bean.setId(3);
			model.delete(bean);
			System.out.println("Test Delete success");
			CollegeBean deletedBean=model.findByPK(3);
			
			if(deletedBean!=null){
				System.out.println("Test Delete fail");
			}
			
		}catch(ApplicationException e){
			e.printStackTrace();
		}
	}
	public static void testUpdate(){
		try{
			CollegeBean bean=model.findByPK(5L);
			bean.setName("oit");
		    bean.setAddress("vv");
			model.update(bean);
			System.out.println("Test Update success");
			CollegeBean updateBean=model.findByPK(5L);
			if(!"oit".equals(updateBean.getName())){
				System.out.println("Test Update fail");
			}
		}catch(ApplicationException e){
			e.printStackTrace();
		}catch(DuplicateRecordException e){
			e.printStackTrace();
		}
	}
	public static void testFindByName() {
		try{
			CollegeBean bean=model.findByName("mit");
			if(bean==null){
				System.out.println("Test Find By Name Fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getAddress());
			System.out.println(bean.getState());
			System.out.println(bean.getCity());
			System.out.println(bean.getPhoneNo());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getCreatedDateTime());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getModifiedDateTime());
			
		}catch(ApplicationException e){
			e.printStackTrace();
		}
	}
	
	public static void testFindByPK(){
		try{
			CollegeBean bean=new CollegeBean();
			long pk=1L;
			bean=model.findByPK(5);
			if(bean==null){
				System.out.println("Test Find By Pk fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getAddress());
			System.out.println(bean.getState());
			System.out.println(bean.getCity());
			System.out.println(bean.getPhoneNo());
//			System.out.println(bean.getCreatedBy());
//			System.out.println(bean.getCreatedDateTime());
//			System.out.println(bean.getModifiedBy());
//			System.out.println(bean.getModifiedDateTime());
		
		}catch(ApplicationException e){
			e.printStackTrace();
		}
	}

public static void testSearch(){
		try{
			CollegeBean bean=new CollegeBean();
			List list=new ArrayList();
			bean.setName("Mit");
			bean.setAddress("indore");
			list=model.search(bean, 1, 10);
			if(list.size()<0){
				System.out.println("Test Search Fail");
			}
			Iterator it=list.iterator();
			while(it.hasNext()){
				bean=(CollegeBean)it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getName());
				System.out.println(bean.getAddress());
				System.out.println("State :"+bean.getState());
				System.out.println("City :"+bean.getCity());
				System.out.println(bean.getPhoneNo());
				System.out.println(bean.getCreatedBy());
				System.out.println(bean.getCreatedDateTime());
				System.out.println(bean.getModifiedBy());
				System.out.println(bean.getModifiedDateTime());
				
			}
		}catch(ApplicationException e){
			e.printStackTrace();
		}
	}
	 public static void testList() {

	        try {
	            CollegeBean bean = new CollegeBean();
	            List list = new ArrayList();
	            list = model.list(1, 10);
	            if (list.size() < 0) {
	                System.out.println("Test list fail");
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) {
	                bean = (CollegeBean) it.next();
	                System.out.println(bean.getId());
	                System.out.println(bean.getName());
	                System.out.println(bean.getAddress());
	                System.out.println("state"+bean.getState());
	                System.out.println("city"+bean.getCity());
	                System.out.println(bean.getPhoneNo());
	                System.out.println(bean.getCreatedBy());
	                System.out.println(bean.getCreatedDateTime());
	                System.out.println(bean.getModifiedBy());
	                System.out.println(bean.getModifiedDateTime());
	            }

	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	    }

	
	}
