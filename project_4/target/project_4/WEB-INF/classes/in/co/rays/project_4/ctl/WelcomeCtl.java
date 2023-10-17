package in.co.rays.project_4.ctl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project_4.util.ServletUtility;


/**
 * welcome functionality controller.to  show welcome page
 * @author ajay
 *
 */
@WebServlet(name="WelcomeCtl",urlPatterns={"/WelcomeCtl"})
public class WelcomeCtl extends BaseCtl {
	
	private static final long serialVersionUID = 1L;

	private static Logger log=Logger.getLogger(WelcomeCtl.class);

	
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("welcome ctl doGet Method............");
	log.debug("WelcomeCtl Method doGet Started");
	
	ServletUtility.forward(ORSView.WELCOME_VIEW, request, response);
	
	log.debug("WelcomeCtl Method doGet Ended");
	}

	@Override
	protected String getView() {
		return ORSView.WELCOME_VIEW;
	}


}
