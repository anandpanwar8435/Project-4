package in.co.rays.project_4.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project_4.bean.CourseBean;
import in.co.rays.project_4.exception.ApplicationException;
import in.co.rays.project_4.exception.DuplicateRecordException;
import in.co.rays.project_4.model.CourseModel;

public class CourseModelTest {
	
public static CourseModel model=new CourseModel();
	
	public static void main(String[] args) throws Exception {
		
//		testAdd();
//		testDelete();
//		testUpdate();
//		testFindByName(); //done
//		testFindByPK();  //done
//		testSearch();   //done
//		testList();   //done
	}
	
	public static void testAdd() throws Exception{
		try{
			CourseBean bean=new CourseBean();
			
			bean.setId(1);
			bean.setCourseName("BE");
			bean.setDescription("Bachelor");
			bean.setDuration("4 year");
			bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
			bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
			
			long pk=model.add(bean);
			System.out.println("add tested successfully");
			CourseBean addedBean=model.findByPK(pk);
			if(addedBean==null){
				System.out.println("fail to add");
			}
		}catch(ApplicationException e){
				e.printStackTrace();
			}
	}
	public static void testDelete(){
		try{
			CourseBean bean=new CourseBean();
//			long pk=9L;
			bean.setId(2);
			model.delete(bean);
			System.out.println("Test Delete success");
			CourseBean deletedBean=model.findByPK(2);
			
			if(deletedBean!=null){
				System.out.println("Test Delete fail");
			}
			
		}catch(ApplicationException e){
			e.printStackTrace();
		}
	}
	public static void testUpdate(){
		try{
			CourseBean bean=model.findByPK(2L);
			bean.setCourseName("MSC");
			model.update(bean);
			System.out.println("Test Update success");
			CourseBean updateBean=model.findByPK(2L);
			if(!"MSC".equals(updateBean.getCourseName())){
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
			CourseBean bean=model.findByName("BE");
			if(bean==null){
				System.out.println("Test Find By Name Fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getCourseName());
			System.out.println(bean.getDescription());
			System.out.println(bean.getDuration());
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
			CourseBean bean=new CourseBean();
			long pk=0L;
			bean=model.findByPK(2);
			if(bean==null){
				System.out.println("Test Find By Pk fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getCourseName());
			System.out.println(bean.getDescription());
			System.out.println(bean.getDuration());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getCreatedDateTime());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getModifiedDateTime());
			
		
		}catch(ApplicationException e){
			e.printStackTrace();
		}
	}
	
	public static void testSearch() {
		try {
		CourseBean bean = new CourseBean();
		List list = new ArrayList();
		bean.setCourseName("MSC");
		list = model.search(bean, 1, 10);
		if (list.size() < 0) {
		System.out.println("Test Search fail");
		}
		Iterator it = list.iterator();
		while (it.hasNext()) {
		bean = (CourseBean) it.next();
		System.out.println(bean.getId());
		System.out.println(bean.getCourseName());
		System.out.println(bean.getDescription());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getCreatedDateTime());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getModifiedDateTime());
		}
		} catch (ApplicationException e) {
		e.printStackTrace();
		}
		}

		public static void testList() {

		try {
		CourseBean bean = new CourseBean();
		List list = new ArrayList();
		list = model.list(1, 10);
		if (list.size() < 0) {
		System.out.println("Test list fail");
		}
		Iterator it = list.iterator();
		while (it.hasNext()) {
		bean = (CourseBean) it.next();
		System.out.println(bean.getId());
		System.out.println(bean.getCourseName());
		System.out.println(bean.getDescription());
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
