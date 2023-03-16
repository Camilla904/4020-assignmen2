package itec4020Asm2;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class logoutHandle extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        //destory the session and its attribute 
        HttpSession session=request.getSession(false);  
    	session.invalidate();
    	
    	// display the logout time
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    	LocalDateTime now = LocalDateTime.now();  
    	String time = dtf.format(now);
    	
    	
    	response.setContentType("text/html");
    	PrintWriter out = response.getWriter();
    	out.print("<!DOCTYPE html>");
    	out.print("<html><body>");
	    out.print("<h3>Logout Successful!</h3><br/>");
	    out.print("<h5>"+time+"</h><br/>");
	    out.print("</body>");
	    out.print("</html>");
	    String path = request.getContextPath()+"/login.jsp";
	    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    	response.setHeader("Pragma", "no-cache");
	    // redirect to the login page in 2 seconds after showing the user that logout successfully 
		response.setHeader("Refresh", "2;url="+path); 
	}
}
