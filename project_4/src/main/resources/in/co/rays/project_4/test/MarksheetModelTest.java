package in.co.rays.project_4.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project_4.bean.MarksheetBean;
import in.co.rays.project_4.exception.ApplicationException;
import in.co.rays.project_4.exception.DuplicateRecordException;
import in.co.rays.project_4.model.MarksheetModel;

public class MarksheetModelTest {

	public static MarksheetModel model=new MarksheetModel();
	
	public static void main(String[] args) {
		
//		   testAdd();
  	   testDelete();
//         testUpdate();
//         testFindByRollNo();
//         testFindByPK();
//         testSearch();       
//         testMeritList(); 
//         testList();

	}

	private static void testAdd() {
		
		try{
			MarksheetBean bean=new MarksheetBean();
			bean.setRollNo("4");
			bean.setPhysics(78);
			bean.setChemistry(87);
			bean.setMaths(74);
			bean.setStudentId(2L);
			long pk=model.add(bean);
			
			MarksheetBean addedBean=model.findByPK(pk);
			if(addedBean==null){
				System.out.println("Test add fail");
			}
			
		
		
		}catch(ApplicationException e){
			e.printStackTrace();
			
		}catch(DuplicateRecordException e){
			e.printStackTrace();
		}
	}
	
	public static void testDelete(){
		try{
			MarksheetBean bean=new MarksheetBean();
			long pk=13L;
			bean.setId(pk);
			model.delete(bean);
			MarksheetBean deletedBean=model.findByPK(pk);
			if(deletedBean!=null){
				System.out.println("Test Delete Fail");
				
			}
			
		}catch(ApplicationException e){
			e.printStackTrace();
		}
	}
	
	public static void testUpdate(){
		try{
			MarksheetBean bean=model.findByPK(4);
			bean.setName("anil");
			bean.setChemistry(66);
			bean.setMaths(76);
			bean.setStudentId(1);
			model.update(bean);
			
			MarksheetBean updateBean=model.findByPK(3L);
			System.out.println("Test update successfull");
			
			/*if(!"IIM".equals(updateBean.getName())){
				System.out.println("Test Update Fail");
			}*/
		}catch(ApplicationException e){
			e.printStackTrace();
		}catch(DuplicateRecordException e){
			e.printStackTrace();
		}
	}
	public static void testFindByRollNo(){
		try{
			MarksheetBean bean=model.findByRollNo("1");
			if(bean==null){
				System.out.println("Test Find By RollNo Fail");
					
			}
			System.out.println(bean.getId());
			System.out.println(bean.getRollNo());
			System.out.println(bean.getName());
			System.out.println(bean.getPhysics());
			System.out.println(bean.getChemistry());
			System.out.println(bean.getMaths());
			
			
		}catch(ApplicationException e){
			e.printStackTrace();
		}
		
	}
	public static void testFindByPK(){
	
		try{
			MarksheetBean bean=new MarksheetBean();
			long pk=3L;
			bean=model.findByPK(pk);
			if(bean==null){
				System.out.println("find by pk fail");
				
			}
			System.out.println(bean.getId());
			System.out.println(bean.getRollNo());
			System.out.println(bean.getName());
			System.out.println(bean.getPhysics());
			System.out.println(bean.getChemistry());
			System.out.println(bean.getMaths());
			
			
		}catch(ApplicationException e){
			e.printStackTrace();
		}
		
	}
	public static void testSearch(){
		try{
			MarksheetBean bean=new MarksheetBean();
			List list=new ArrayList();
			bean.setName("Vishal");
			list=model.search(bean,1,4);
			if(list.size()<0){
				System.out.println("Test search fail");
			}
			Iterator it=list.iterator();
			while(it.hasNext()){
			bean=(MarksheetBean)it.next();
		
			System.out.println(bean.getId());
			System.out.println(bean.getRollNo());
			System.out.println(bean.getName());
			System.out.println(bean.getPhysics());
			System.out.println(bean.getChemistry());
			System.out.println(bean.getMaths());
			}
			
		}catch(ApplicationException e){
			e.printStackTrace();
		}
	
	}

	public static void testMeritList(){
		
		try{
			MarksheetBean bean=new MarksheetBean();
			List list=new ArrayList();
			list=model.getMeritList(1, 4);
			if(list.size()<0){
				System.out.println("Test list fail");
			}
			Iterator it=list.iterator();
			while(it.hasNext()){
				bean= (MarksheetBean)it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getRollNo());
				System.out.println(bean.getName());
				System.out.println(bean.getPhysics());
				System.out.println(bean.getChemistry());
				System.out.println(bean.getMaths());
				
		}	
			}catch(ApplicationException e){
				e.printStackTrace();
			}
	}
	public static void testList(){
		try{
			MarksheetBean bean=new MarksheetBean();
			List list=new ArrayList();
			list=model.list(1,6);
			if(list.size()<0){
				System.out.println("Test List fail");
				
			}
			Iterator it=list.iterator();
			while(it.hasNext()){
				bean=(MarksheetBean)it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getRollNo());
				System.out.println(bean.getName());
				System.out.println(bean.getPhysics());
				System.out.println(bean.getChemistry());
				System.out.println(bean.getMaths());
				System.out.println(bean.getCreatedBy());
				System.out.println(bean.getCreatedDateTime());
				System.out.println(bean.getModifiedBy());
				System.out.println(bean.getModifiedDateTime());
				
		}
			}catch(ApplicationException e){
				e.printStackTrace();
			}
			
		}
	
}
