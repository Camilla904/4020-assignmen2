package itec4020Asm2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class loginHandle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get the user id and password from the request 
		String id = request.getParameter("userId");
		String pwd = request.getParameter("pwd");
		String path= "";
		response.setContentType("text/html");
		   
        if(id.equals("user") && pwd.equals("12345")){
        	//create session if no session is created before
            HttpSession session=request.getSession(true); 
            //set session attribute login status as login
        	session.setAttribute("login_status","login");  
        	// set header to prevent caching
        	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        	response.setHeader("Pragma", "no-cache");
        	path = request.getContextPath()+"/data";
        	//redirect to the mainpage that display the data
        	response.sendRedirect(path);
        	
        }
        else{
        	path = request.getContextPath()+"/login.jsp";
        	HttpSession session=request.getSession(true); 
        	//set session attribute login status as login
        	session.setAttribute("login_status","failed");  
        	// set header to prevent caching
        	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        	response.setHeader("Pragma", "no-cache");
        	// redirect to the login page again as login failed
        	response.sendRedirect("login.jsp");
        }
		
		
	}

}