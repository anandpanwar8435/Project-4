package in.co.rays.project_4.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project_4.bean.BaseBean;
import in.co.rays.project_4.bean.SubjectBean;
import in.co.rays.project_4.bean.TimeTableBean;
import in.co.rays.project_4.exception.ApplicationException;
import in.co.rays.project_4.exception.DuplicateRecordException;
import in.co.rays.project_4.model.CourseModel;
import in.co.rays.project_4.model.SubjectModel;
import in.co.rays.project_4.model.TimeTableModel;
import in.co.rays.project_4.util.DataUtility;
import in.co.rays.project_4.util.DataValidator;
import in.co.rays.project_4.util.PropertyReader;
import in.co.rays.project_4.util.ServletUtility;

/**
 * Timetable functionality controller. to perform add,delete and update
 * operation
 * @author anand
 *
 */
@WebServlet(name="TimeTableCtl",urlPatterns={"/ctl/TimeTableCtl"})
public class TimeTableCtl extends BaseCtl{
	
	private static Logger log = Logger.getLogger(TimeTableCtl.class);

	/* (non-Javadoc)
	 * @see in.co.rays.ors.controller.BaseCtl#preload(javax.servlet.http.HttpServletRequest)
	 */
	protected void preload(HttpServletRequest request) {
		CourseModel cmodel = new CourseModel();
		SubjectModel smodel = new SubjectModel();
		
		try {
			List clist = cmodel.list();
			List slist = smodel.list();	
			request.setAttribute("CourseList", clist);
			request.setAttribute("SubjectList", slist);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@"+clist);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@"+slist);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	protected boolean validate(HttpServletRequest request) {
		log.debug("validate method of TimeTable Ctl started");
		boolean pass = true;
		System.out.println("inside validate of tt ctl");
		System.out.println(request.getParameter("courseId"));
		System.out.println(request.getParameter("subId"));
		System.out.println(request.getParameter("semester"));
		System.out.println(request.getParameter("ExamDate"));
		System.out.println(request.getParameter("ExamTime"));
		System.out.println(request.getParameter("Description"));
		
		
		
		if (DataValidator.isNull(request.getParameter("courseId"))) {
			request.setAttribute("courseId", PropertyReader.getValue("error.require", "Course"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("subId"))) {
			request.setAttribute("subId", PropertyReader.getValue("error.require", "Subject"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("semester"))) {
			request.setAttribute("semester", PropertyReader.getValue("error.require", "Semester"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("ExamDate"))) {
			request.setAttribute("ExamDate", PropertyReader.getValue("error.require", "Exam Date"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("ExamTime"))) {
			request.setAttribute("ExamTime", PropertyReader.getValue("error.require", "Exam Time"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("Description"))) {
			request.setAttribute("Description", PropertyReader.getValue("error.require", "Description"));
			pass = false;
		}
		
		
		log.debug("validate method of TimeTable Ctl End");
		return pass;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.ors.controller.BaseCtl#populateBean(javax.servlet.http.HttpServletRequest)
	 */
	protected TimeTableBean populateBean(HttpServletRequest request) {
		log.debug("populateBean method of TimeTable Ctl start");
		TimeTableBean bean = new TimeTableBean();
		
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@inside populate of tt ctl");
		System.out.println(request.getParameter("courseId"));
		System.out.println(request.getParameter("subId"));
		System.out.println(request.getParameter("semester"));
		System.out.println(request.getParameter("ExamDate"));
		
		System.out.println(request.getParameter("ExamTime"));
		System.out.println(request.getParameter("Description"));
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		
		bean.setSubId(DataUtility.getLong(request.getParameter("subId")));
	//	bean.setSubjectName(DataUtility.getString(request.getParameter("subname")));
		bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));
//		bean.setCourseName(DataUtility.getString(request.getParameter("coursename")));
		bean.setSemester(DataUtility.getString(request.getParameter("semester")));
		bean.setExamDate(DataUtility.getDate(request.getParameter("ExamDate")));
		bean.setExamTime(DataUtility.getString(request.getParameter("ExamTime")));
		bean.setDescription(DataUtility.getString(request.getParameter("Description")));
		
		/*System.out.println("<<<<<<__________>>>>>>>>");
		System.out.println(request.getParameter("ExDate"));
		System.out.println("<<<<<<__________>>>>>>>>");
*/		populateDTO(bean, request);
		log.debug("populateBean method of TimeTable Ctl End");
		return bean;
	}
    
	
	
	
	
	
	
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		log.debug("do Get method of TimeTable Ctl Started");
		//System.out.println("Timetable ctl .do get started........>>>>>");

			String op = DataUtility.getString(request.getParameter("operation"));
			long id = DataUtility.getLong(request.getParameter("id"));
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@inside doget of tt ctl");
		System.out.println(request.getParameter("id"));
			TimeTableModel model = new TimeTableModel();
			TimeTableBean bean = null;
			if (id > 0) {
				try {
					bean = model.findByPk(id);
					
					ServletUtility.setBean(bean, request);
				} catch (Exception e) {
					e.printStackTrace();
					log.error(e);
					//ServletUtility.handleException(e, request, response);
				}
			}

			log.debug("do Get method of TimeTable Ctl End");
			System.out.println("Timetable ctl .do get End........>>>>>");
				
		
		ServletUtility.forward(getView(), request, response);
	
	}
	
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	log.debug("do post method of TimeTable Ctl start");
	System.out.println("inside tt Add");
	List list;
	String op = DataUtility.getString(request.getParameter("operation"));
	long id = DataUtility.getLong(request.getParameter("id"));

	TimeTableModel model = new TimeTableModel();

	
	if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) 
	{
		TimeTableBean bean = (TimeTableBean)populateBean(request);
		//System.out.println(bean.getCourseName()+"#####################"+ bean.getSubName());
		try {
			if(id>0){
				model.update(bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage(" TimeTable is Successfully Updated", request);
				
			}else{
				System.out.println("inside tt Add");
			model.add(bean);
			//ServletUtility.setBean(bean, request);
			ServletUtility.setSuccessMessage(" TimeTable is Successfully Added", request);
	

			} 
			/*ServletUtility.setBean(bean, request);
			ServletUtility.setSuccessMessage(" TimeTable is Successfully Saved", request);
			*/
		}catch (ApplicationException e) {
			log.error(e);
			e.printStackTrace();
//			ServletUtility.handleException(e, request, response);
//			return;
		
		}
			catch (DuplicateRecordException e) {
			e.printStackTrace();
			
			ServletUtility.setBean(bean, request);
			ServletUtility.setErrorMessage("Time Table already Exists", request);
			}
	}
	else if (OP_CANCEL.equalsIgnoreCase(op))
	{
		ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request, response);
		return;
	}
	else if ( OP_RESET.equalsIgnoreCase(op))
	{
		ServletUtility.redirect(ORSView.TIMETABLE_CTL, request, response);
		return;
	}
	

	ServletUtility.forward(getView(), request, response);
	


}	
	
	
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.TIMETABLE_VIEW;
	}

}