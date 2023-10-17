package in.co.rays.project_4.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.rays.project_4.bean.BaseBean;
import in.co.rays.project_4.bean.UserBean;
import in.co.rays.project_4.exception.ApplicationException;
import in.co.rays.project_4.exception.RecordNotFoundException;
import in.co.rays.project_4.model.UserModel;
import in.co.rays.project_4.util.DataUtility;
import in.co.rays.project_4.util.DataValidator;
import in.co.rays.project_4.util.PropertyReader;
import in.co.rays.project_4.util.ServletUtility;


/**
 * change password operation functionality perform
 * @author ajay
 *
 */
@ WebServlet(name="ChangePasswordCtl",urlPatterns={"/ctl/ChangePasswordCtl"})
public class ChangePasswordCtl extends BaseCtl {
	
	public static final String OP_CHANGE_MY_PROFILE="Change My Profile";

	private static Logger log=Logger.getLogger(ChangePasswordCtl.class);
	
	protected boolean validate(HttpServletRequest request){
		log.debug("ChangePasswordCtl Method validate Started");
		
		boolean pass=true;
		String op=request.getParameter("operation");
		
		if(OP_CHANGE_MY_PROFILE.equalsIgnoreCase(op)){
			return pass;
		}
		if(DataValidator.isNull(request.getParameter("oldPassword"))){
			request.setAttribute("oldPassword",PropertyReader.getValue("error.require","Old Password"));
			pass=false;
		}else if (!DataValidator.isPassword(request.getParameter("oldPassword"))) {
			request.setAttribute("oldPassword", "Please Enter valid Password");
			pass = false;
		}
		if(DataValidator.isNull(request.getParameter("newPassword"))){
			request.setAttribute("newPassword",PropertyReader.getValue("error.require","New Password"));
			pass=false;
		}else if (!DataValidator.isPassword(request.getParameter("newPassword"))) {
			request.setAttribute("newPassword", "Please Enter vaild Password");
			pass = false;
		}
		if(DataValidator.isNull(request.getParameter("confirmPassword"))){
			request.setAttribute("confirmPassword",PropertyReader.getValue("error.require","Confirm Password"));
			pass=false;
		}
		if(!request.getParameter("newPassword").equals
				(request.getParameter("confirmPassword"))&&!"".equals(request.getParameter("confirmPassword"))){
			ServletUtility.setErrorMessage("New and confirm passwords not matched", request);
			pass=false;
		}
		log.debug("ChangePasswordCtl Method validate Ended");
		return pass;
	}
	/*protected BaseBean populateBean(HttpServletRequest request){
		log.debug("ChangePasswordCtl Method populatebean Started");
		
		UserBean bean=new UserBean();
		bean.setPassword(DataUtility.getString(request.getParameter("oldPassword")));
		
		bean.setConfirmPassword(DataUtility.getString(request.getParameter("oldPassword")));
		
		bean.setConfirmPassword(DataUtility.getString(request.getParameter("confirmPassword")));
		populateDTO(bean,request);
		log.debug("ChangePasswordCtl Method populatebean Ended");
		return bean;
	}*/
	
	/**
     * Display Logics inside this method
     */
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		
		ServletUtility.forward(getView(), request, response);
	}
	
	/**
     * Submit logic inside it
     */
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		HttpSession session=request.getSession(true);
		log.debug("ChangePasswordCtl Method doPost Started");
		
		String op=DataUtility.getString(request.getParameter("operation"));
		UserModel model=new UserModel();
		UserBean bean=(UserBean)populateBean(request);
		UserBean userbean=(UserBean)session.getAttribute("user");
		
		String newPassword=request.getParameter("newPassword");
		String oldPassword=request.getParameter("oldPassword");
		long id =userbean.getId();
		
		if(OP_SAVE.equalsIgnoreCase(op)){
			try{
				boolean flag=model.changePassword(id, oldPassword, newPassword);
						
				if(flag==true){
					model.findByLogin(userbean.getLogin());
//					session.setAttribute("user",bean);
					ServletUtility.setSuccessMessage("Password has been changed Successfully", request);
					
				}		
			}catch(ApplicationException e){
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}catch(RecordNotFoundException e){
				ServletUtility.setErrorMessage("Old Password is invalide", request);
				
			}
		}else if(OP_CHANGE_MY_PROFILE.equalsIgnoreCase(op)){
			ServletUtility.redirect(ORSView.MY_PROFILE_CTL, request, response);
			return;
		}
		ServletUtility.forward(ORSView.CHANGE_PASSWORD_VIEW, request, response);
		log.debug("ChangePasswordCtl Method doGet Ended");
	}
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.CHANGE_PASSWORD_VIEW;
	}
	
}
