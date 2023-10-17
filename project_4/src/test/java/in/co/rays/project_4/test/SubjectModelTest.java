package in.co.rays.project_4.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project_4.bean.SubjectBean;
import in.co.rays.project_4.exception.ApplicationException;
import in.co.rays.project_4.exception.DuplicateRecordException;
import in.co.rays.project_4.model.SubjectModel;

public class SubjectModelTest {

	public static SubjectModel model=new SubjectModel();
	
	public static void main(String[] args) throws Exception {
		
//		testAdd();
//		testDelete();
//		testUpdate();
//		testFindByName();
//		testFindByPK();   //done
//		testSearch();   //done
//		testList();    //done
		
	}
	
	public static void testAdd() throws Exception{
		try{
			SubjectBean bean=new SubjectBean();
			
			bean.setId(1);
			bean.setSubjectName("Chemistry");
			bean.setCourseName("BE");
			bean.setSubjectId(103);
			bean.setCourseId(3);
			bean.setDescription("Bachelor");
			bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
			bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
			
			long pk=model.add(bean);
			System.out.println("add tested successfully");
			SubjectBean addedBean=model.findByPK(pk);
			if(addedBean==null){
				System.out.println("fail to add");
			}
		}catch(ApplicationException e){
				e.printStackTrace();
			}
	}
	public static void testDelete(){
		try{
			SubjectBean bean=new SubjectBean();
//			long pk=9L;
			bean.setId(3);
			model.delete(bean);
			System.out.println("Test Delete success");
			SubjectBean deletedBean=model.findByPK(3);
			
			if(deletedBean!=null){
				System.out.println("Test Delete fail");
			}
			
		}catch(ApplicationException e){
			e.printStackTrace();
		}
	}
	public static void testUpdate(){
		try{
			SubjectBean bean=model.findByPK(2L);
			bean.setSubjectName("Chemistry");
			model.update(bean);
			System.out.println("Test Update success");
			SubjectBean updateBean=model.findByPK(2L);
			if(!"Chemistry".equals(updateBean.getSubjectName())){
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
			SubjectBean bean=model.findByName("Chemistry");
			if(bean==null){
				System.out.println("Test Find By Name Fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getSubjectName());
			System.out.println(bean.getDescription());
			System.out.println(bean.getCourseName());
			System.out.println(bean.getCourseId());
			System.out.println(bean.getSubjectId());
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
			SubjectBean bean=new SubjectBean();
			long pk=0L;
			bean=model.findByPK(2);
			if(bean==null){
				System.out.println("Test Find By Pk fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getSubjectName());
			System.out.println(bean.getDescription());
			System.out.println(bean.getCourseName());
			System.out.println(bean.getCourseId());
			System.out.println(bean.getSubjectId());
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
		SubjectBean bean = new SubjectBean();
		List list = new ArrayList();
		bean.setSubjectName("Math");
		list = model.search(bean, 1, 10);
		if (list.size() < 0) {
		System.out.println("Test Search fail");
		}
		Iterator it = list.iterator();
		while (it.hasNext()) {
		bean = (SubjectBean) it.next();
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
		SubjectBean bean = new SubjectBean();
		List list = new ArrayList();
		list = model.list(1, 10);
		if (list.size() < 0) {
		System.out.println("Test list fail");
		}
		Iterator it = list.iterator();
		while (it.hasNext()) {
		bean = (SubjectBean) it.next();
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
