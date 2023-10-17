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
import in.co.rays.project_4.exception.DuplicateRecordException;
import in.co.rays.project_4.model.RoleModel;
import in.co.rays.project_4.model.UserModel;
import in.co.rays.project_4.util.DataUtility;
import in.co.rays.project_4.util.DataValidator;
import in.co.rays.project_4.util.PropertyReader;
import in.co.rays.project_4.util.ServletUtility;

/**
 * user functionality controller.to perform add,delete and update operation
 * @author ajay
 *
 */
@WebServlet(name="UserCtl",urlPatterns={"/ctl/UserCtl"})
public class UserCtl extends BaseCtl {


	private static final long serialVersionUID = 1L;

	private static Logger log=Logger.getLogger(UserCtl.class);
	
	protected void preload(HttpServletRequest request){
		
		RoleModel model=new RoleModel();
		
		try{
			List l=model.list();
			request.setAttribute("roleList", l);
		}catch(ApplicationException e){
			e.printStackTrace();
			log.error(e);
		}
	}
	
	protected boolean validate(HttpServletRequest request){
		
		log.debug("UserCtl Method validate Started");
		boolean pass=true;
		
		String login = request.getParameter("login");
		String dob = request.getParameter("dob");
		String op=DataUtility.getString(request.getParameter("operation"));
		
		if(DataValidator.isNull(request.getParameter("firstName"))){
			request.setAttribute("firstName",PropertyReader.getValue("error.require","FirstName"));
			pass=false;
		}else if(!DataValidator.isName(request.getParameter("firstName"))){
			request.setAttribute("firstName","please Enter correct Name");
			pass=false;
		}
		if (DataValidator.isNull(request.getParameter("roleId"))) {
			request.setAttribute("roleId", PropertyReader.getValue("error.require", "Role Name"));
			pass = false;
		}
		
		if(DataValidator.isNull(request.getParameter("lastName"))){
			request.setAttribute("lastName",PropertyReader.getValue("error.require","LastName"));
			pass=false;
		}else if (!DataValidator.isName(request.getParameter("lastName"))) {
			request.setAttribute("lastName", "please enter correct Name");
			pass = false;
		
		
		}if (DataValidator.isNull(request.getParameter("login"))) {
			request.setAttribute("login", PropertyReader.getValue("error.require", "login id"));
			pass = false;
		}else if (!DataValidator.isEmail(login)){
			request.setAttribute("login",PropertyReader.getValue("error.email","Email Id"));
			pass=false;	
		}
		if(!OP_UPDATE.equalsIgnoreCase(op)){
			
		if(DataValidator.isNull(request.getParameter("password"))){
			request.setAttribute("password",PropertyReader.getValue("error.require","Password"));
			pass=false;
		} else if (!DataValidator.isPasswordLength(request.getParameter("password"))) {
			request.setAttribute("password", "Password should be 8 to 12 characters");
			pass = false;
		} else if (!DataValidator.isPassword(request.getParameter("password"))) {
			request.setAttribute("password", "Password Must contain uppercase, lowercase, digit & special character");
			pass = false;
		}
		if(DataValidator.isNull(request.getParameter("confirmPassword"))){
			request.setAttribute("confirmPassword",PropertyReader.getValue("error.require","Confirm Password"));
			pass=false;
		}if (!request.getParameter("password").equals(request.getParameter("confirmPassword"))
				&& !"".equals(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword","Confirm  Password  should  be matched.");
			pass = false;
		}
            }
		if (DataValidator.isNull(request.getParameter("mobile"))) {
			request.setAttribute("mobile", PropertyReader.getValue("error.require", "Mobile"));
			pass = false;
		} else if(!DataValidator.isPhoneLength(request.getParameter("mobile"))){
			 request.setAttribute("mobile", "Please Enter Valid Mobile Number");
			 pass=false;	
		}else if (!DataValidator.isPhoneNo(request.getParameter("mobile"))) {
			request.setAttribute("mobile", "Please Enter Valid Mobile Number");
			pass = false;
		}

		if(DataValidator.isNull(request.getParameter("gender"))){
			request.setAttribute("gender",PropertyReader.getValue("error.require", "Gender"));
			pass=false;
		}
		
		/*if(DataValidator.isNull(request.getParameter(dob))){
			request.setAttribute("dob",PropertyReader.getValue("error.require", "Date Of Birth"));
			pass=false;
		}*/
		else if(!DataValidator.isDate(dob)){
			request.setAttribute("dob",PropertyReader.getValue("error.require","Date Of Birth"));
			pass=false;	
		}
		else if (!DataValidator.isValidAge(dob)) {
			request.setAttribute("dob", "Age Must be greater then 18 year");
			pass = false;
		}
		
		log.debug("UserCtl Method validate Ended");
		return pass;
		
	}
	
	protected BaseBean populateBean(HttpServletRequest request){
		
		log.debug("UserCtl Method populatebean Started");
		
		UserBean bean=new UserBean();
		String role = request.getParameter("roleId");
		System.out.println("dddddddddddddddddddddddddddddd"+request.getParameter("password"));
		
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setRoleId(DataUtility.getLong(request.getParameter("roleId")));
		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));
		bean.setConfirmPassword(DataUtility.getString(request.getParameter("confirmPassword")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobile")));
		
		populateDTO(bean,request);
		log.debug("UserCtl Method populatebean Ended");
		
		
		return bean;
		
	}
	
	 /**
     * Contains DIsplay logics
     */
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		
		log.debug("UserCtl Method doGet Started");
		String op=DataUtility.getString(request.getParameter("operation"));
		
		UserModel model=new UserModel();
		
		long id=DataUtility.getLong(request.getParameter("id"));
		
		if(id>0||op!=null){
			System.out.println("in id>0 condition");
			UserBean bean;
			
			try{
				bean=model.findByPK(id);
				ServletUtility.setBean(bean, request);
			}catch(ApplicationException e){
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
		log.debug("UserCtl Method doGet Ended");
	}
	
	/**
     * Contains Submit logics
     */
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		
		log.debug("UserCtl Method doPost Started");
		String op=DataUtility.getString(request.getParameter("operation"));
		
		UserModel model=new UserModel();
		long id=DataUtility.getLong(request.getParameter("id"));
	
		System.out.println("1111111111111111111111111111111111111111 userctl dopost"+op);
		
		if(OP_SAVE.equalsIgnoreCase(op)||OP_UPDATE.equalsIgnoreCase(op)){
			
			UserBean bean=(UserBean)populateBean(request);
			System.out.println("3333333333333333333333 userctl dopost"+bean.getPassword()+bean.getFirstName()+""+bean.getLastName()+""+bean.getMobileNo()+""+bean.getPassword());
			
			try{
				if(id>0){
					model.update(bean);
					ServletUtility.setSuccessMessage("Data is successfully update", request);
				}else{
					try{
						model.add(bean);
//						bean.setId(pk);
					
				
//				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Data is successfully saved", request);
				
			}catch(ApplicationException e){
				log.error(e);
				ServletUtility.handleException(e, request, response);
			}catch(DuplicateRecordException e){
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Login id already exists", request);
				
			}
				
		}
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Login id already exists", request);
			}
		}else if(OP_DELETE.equalsIgnoreCase(op)){
			UserBean bean=(UserBean)populateBean(request);
			
			try{
				model.delete(bean);
				ServletUtility.redirect(ORSView.USER_LIST_CTL, request, response);
				return;
			}catch(ApplicationException e){
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}else if(OP_CANCEL.equalsIgnoreCase(op)){
			ServletUtility.redirect(ORSView.USER_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.USER_CTL, request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);
		log.debug("UserCtl Method doPostEnded");
	}
	
	
	
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.USER_VIEW;
	}
	
	

	
}
