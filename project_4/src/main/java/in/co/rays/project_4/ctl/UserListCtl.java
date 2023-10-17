package in.co.rays.project_4.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project_4.bean.BaseBean;
import in.co.rays.project_4.bean.UserBean;
import in.co.rays.project_4.exception.ApplicationException;
import in.co.rays.project_4.model.RoleModel;
import in.co.rays.project_4.model.UserModel;
import in.co.rays.project_4.util.DataUtility;
import in.co.rays.project_4.util.PropertyReader;
import in.co.rays.project_4.util.ServletUtility;


/**
 * user list funcitonality controller.to perform search and show operation
 * @author anand
 *
 */
@WebServlet(name="UserListCtl",urlPatterns={"/ctl/UserListCtl"})
public class UserListCtl extends BaseCtl{
	
	private static Logger log=Logger.getLogger(UserListCtl.class);

	protected void preload(HttpServletRequest request) {
		RoleModel model=new RoleModel();
		/*UserModel model1=new UserModel();*/
		try {
			List list=model.list();
			/*List list1=model1.list();*/
			request.setAttribute("roleList", list);
			/*request.setAttribute("userList", list1);*/

			
		} catch (Exception e) {
			log.error(e);
			
		}
	}	
	protected BaseBean populateBean(HttpServletRequest request){
		
		UserBean bean=new UserBean();
		
		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		
		bean.setLastName(DataUtility.getString(request.getParameter("last")));
		
		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setRoleId(DataUtility.getLong(request.getParameter("roleId")));
		System.out.println("1234567898765432"+bean.getFirstName());
		populateDTO(bean, request);
		return bean;
	}
	
	/**
	 * Contains Display logics
	 */
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		
		log.debug("UserListCtl doGet Start");
		List list=null;
//		List next;
		/*int pageNo=1;
		int pageSize=DataUtility.getInt(PropertyReader.getValue("page.size"));*/
		
		int pageNo=DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize=DataUtility.getInt(request.getParameter("pageSize"));
		
		pageNo=(pageNo==0)?1:pageNo;
		pageSize=(pageSize==0)?DataUtility.getInt(PropertyReader.getValue("page.size")):pageSize;
		
		System.out.println("==========>>>>>>>>>>>>>>>>"+pageSize);
		UserBean bean=(UserBean)populateBean(request);
		
		String op=DataUtility.getString(request.getParameter("operation"));
		String[] ids=request.getParameterValues("ids");
		System.out.println("This is Ids "+ids);
		UserModel model=new UserModel();
		try{
			list=model.search(bean, pageNo, pageSize);
//			list=model.search(bean, pageNo+1, pageSize);
			ServletUtility.setList(list, request);
			if(list==null||list.size()==0){
				ServletUtility.setErrorMessage("No record found", request);
				
			}if(list==null||list.size()==0){
				request.setAttribute("nextListSize", 0);
				
			}else{
				request.setAttribute("nextListSize", list.size());
			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);
			
		}catch(ApplicationException e){
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		}
		log.debug("UserListCtl doGet End");
	}
	
	/**
	 * Contains Submit logics
	 */
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		System.out.println("doPost of UserListCtl &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		log.debug("UserListCtl doPost start");
		
		List list=null;
		List next=null;
		
		int pageNo=DataUtility.getInt(request.getParameter("pageNo"));
		System.out.println("This is pageNo "+pageNo);
		
		int pageSize=DataUtility.getInt(request.getParameter("pageSize"));
		System.out.println("This is pageSize "+pageSize);

		pageNo = (pageNo==0) ? 1 :pageNo;
		pageSize = (pageSize==0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;
		
		UserBean bean=(UserBean)populateBean(request);
		String op=DataUtility.getString(request.getParameter("operation"));
		System.out.println("This is Operation "+op);
		
		String[] ids=request.getParameterValues("ids");
		System.out.println("This is Ids "+ids);
		
		UserModel model=new UserModel();
		
		try{
			if(OP_SEARCH.equalsIgnoreCase(op)||OP_NEXT.equalsIgnoreCase(op)||OP_PREVIOUS.equalsIgnoreCase(op)){
				
				if(OP_SEARCH.equalsIgnoreCase(op)){
					System.out.println("This is Search Operation 755555555555555555555555555555555555");
					pageNo=1;
				}else if(OP_NEXT.equalsIgnoreCase(op)){
					pageNo++;
				}else if(OP_PREVIOUS.equalsIgnoreCase(op)&&pageNo>1){
					pageNo--;
				}
			}else if (OP_NEW.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.USER_CTL, request, response);
				return;
			}else if(OP_NEXT.equalsIgnoreCase(op)){
				ServletUtility.redirect(ORSView.USER_LIST_CTL, request, response);
				return;
			} else if (OP_RESET.equalsIgnoreCase(op)) {

				ServletUtility.redirect(ORSView.USER_LIST_CTL, request, response);
				return;
			}else if (OP_DELETE.equalsIgnoreCase(op)) {
						
				System.out.println("11111111111111111111111111111");
						pageNo = 1;
						if (ids != null && ids.length > 0) {
							UserBean deletebean = new UserBean();
							for (String id : ids) {
								deletebean.setId(DataUtility.getInt(id));
								model.delete(deletebean);
								ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
							}
							System.out.println("2222222222222222222222");
				}else{
					ServletUtility.setErrorMessage("Select at least one record", request);
					
				}
			}if (OP_BACK.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.USER_LIST_CTL, request, response);
				return;	
			}
			System.out.println("This is Block 8888888888888888888888888888888888888888888888888");

			bean = (UserBean) populateBean(request);
			list=model.search(bean, pageNo, pageSize);
			System.out.println("This is Ctl model.search 999999999999999999999999999999999999999998");

			ServletUtility.setList(list, request);
			System.out.println("This List mtd 22222222222222222222222222222222222222222222222");

			if(list==null||list.size()==0&&!OP_DELETE.equalsIgnoreCase(op)){
				System.out.println("This is 3 Operation 1111111111111111111111111111111111111111111111");

				ServletUtility.setErrorMessage("No record found", request);
				System.out.println("This is Block 4545454545454545454554545454545454545454");

			}
			
			if (next == null || next.size() == 0) {
				System.out.println("This is Block ************************************");

				request.setAttribute("nextListSize", "0");
			} else {
				request.setAttribute("nextListSize", next.size());
				System.out.println("This is Block 88888888888888888888888___________________88888888888888888888888888");

			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);
		
		}catch(ApplicationException e){
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		}
	log.debug("UserListCtl doGet End");
}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.USER_LIST_VIEW;
	}

	
}
