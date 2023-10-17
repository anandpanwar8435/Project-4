package in.co.rays.project_4.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project_4.bean.TimeTableBean;
import in.co.rays.project_4.exception.ApplicationException;
import in.co.rays.project_4.exception.DuplicateRecordException;
import in.co.rays.project_4.model.TimeTableModel;

public class TimeTableModelTest {
	
	public static TimeTableModel model=new TimeTableModel();

	public static void main(String[] args) throws Exception {
	   testAdd(); 
//	   testDelete(); 
//	   testUpdate(); 
//	   testFindByName(); 
//	   testFindByPK();  //done
//	   testSearch();   //done
//	   testList();    //done
		
	}

	public static void testAdd() throws Exception {
	try{
	TimeTableBean bean = new TimeTableBean();
	bean.setId(1);
	bean.setSubName("Maths");
	bean.setCourseName("BE");
	long pk=model.add(bean);
	System.out.println("add tested ");
	TimeTableBean addedBean=model.findByPK(pk);
	if(addedBean==null){
	System.out.println("fail to add");
	}
	}catch(ApplicationException e){
	e.printStackTrace();
	}
	}
	
	public static void testDelete() throws Exception{
		TimeTableBean bean = new TimeTableBean();
		try{
		// long pk = 1;
		bean.setId(1);
		model.delete(bean);
		System.out.println("Test delets success");
		TimeTableBean deletedBean = model.findByPK(1);
		if (deletedBean != null) {
		System.out.println("Test Delete fail");
		}

		}catch (ApplicationException e){
		e.printStackTrace();
		}
		}
	public static void testUpdate(){
		try{
			TimeTableBean bean=model.findByPK(2L);
			bean.setSubName("Maths");
		    bean.setCourseName("MSC");
			model.update(bean);
			System.out.println("Test Update success");
			TimeTableBean updateBean=model.findByPK(2L);
			if(!"Maths".equals(updateBean.getSubName())){
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
			TimeTableBean bean=model.findByName("Maths");
			if(bean==null){
				System.out.println("Test Find By Name Fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getSubName());
			System.out.println(bean.getCourseName());
			System.out.println(bean.getSemester());
			System.out.println(bean.getSubId());
			System.out.println(bean.getCourseId());
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
			TimeTableBean bean=new TimeTableBean();
			long pk=0L;
			bean=model.findByPK(1);
			if(bean==null){
				System.out.println("Test Find By Pk fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getSubName());
			System.out.println(bean.getCourseName());
			System.out.println(bean.getSemester());
			System.out.println(bean.getSubId());
			System.out.println(bean.getCourseId());
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
		TimeTableBean bean = new TimeTableBean();
		List list = new ArrayList();
		bean.setSubName("Maths");
		list = model.search(bean, 1, 10);
		if (list.size() < 0) {
		System.out.println("Test Search fail");
		}
		Iterator it = list.iterator();
		while (it.hasNext()) {
		bean = (TimeTableBean) it.next();
		System.out.println(bean.getId());
		System.out.println(bean.getSubName());
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
		TimeTableBean bean = new TimeTableBean();
		List list = new ArrayList();
		list = model.list(1, 10);
		if (list.size() < 0) {
		System.out.println("Test list fail");
		}
		Iterator it = list.iterator();
		while (it.hasNext()) {
		bean = (TimeTableBean) it.next();
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
