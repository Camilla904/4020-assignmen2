package itec4020Asm2;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;

import java.io.PrintWriter;

import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.xml.transform.Source;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;


public class DataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path =request.getContextPath();
		HttpSession login_session = request.getSession(false);
		// redirect to the login page if login_status is not equal to login
		if(login_session.getAttribute("login_status")!="login"){
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		}
		response.setContentType("text/html");
		File xmlfile = new File("C:/Users/Cheuk Wai/eclipse-workspace/itec4020Asm2/src/main/webapp/catalog.xml");
		String imgBase64 = imgBase64StringConversion("C:/Users/Cheuk Wai/eclipse-workspace/itec4020Asm2/src/main/webapp/bookCover.jpg");
		String genre = request.getParameter("genreType");
		if(genre==null){genre = "All";}
		createXSL(genre,imgBase64);
        File xslfile = new File("C:/Users/Cheuk Wai/eclipse-workspace/itec4020Asm2/src/main/webapp/books.xsl");

		Source xml = new StreamSource(xmlfile);
		Source xslt = new StreamSource(xslfile);
		String html = convertXml(xml, xslt);
	    PrintWriter out = response.getWriter();
		out.write(html);
		// set header to prevent caching
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
	}
	
	public static String convertXml(Source xml, Source xslt) throws IOException {
		StringWriter stringwriter = new StringWriter();
		try {

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer trasformer = transformerFactory.newTransformer(xslt);
			trasformer.transform(xml, new StreamResult(stringwriter));
			String html = stringwriter.toString();
			
			return html;

		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		
		return "XML loading failed";	
	}
	
	public static void createXSL(String genre, String imgBase64) throws IOException {
		
		//writing a xsl based on the genreType the user sent 
		String xsl="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
				+ "<xsl:stylesheet version=\"1.0\"\r\n"
				+ "xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\">\r\n"
				+ "<xsl:template match=\"/\">\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "<style>\r\n"
				+ "\r\n"
				+ "body{\r\n"
				+ "	background-color:linen;\r\n" 
				+"-webkit-user-select: none; \r\n" // disable selecting function 
				+ "  -ms-user-select: none; \r\n" // disable selecting function 
				+ "  user-select: none; "  // disable selecting function 
				+ "}\r\n"
				+ ".navbar {\r\n"
				+ "  overflow: hidden;\r\n"
				+ "  background-color: #017e72c2;\r\n"
				+ "  padding:0 10px;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ ".navbar a {\r\n"
				+ "  float: left;\r\n"
				+ "  font-size: 25px;\r\n"
				+ "  color: white;\r\n"
				+ "  text-align: center;\r\n"
				+ "  padding: 14px 16px;\r\n"
				+ "  text-decoration: none;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ ".logout-form .logoutBtn {\r\n"
				+ "  font-size: 25px;  \r\n"
				+ "  border: none;\r\n"
				+ "  outline: none;\r\n"
				+ "  color: white;\r\n"
				+ "  padding: 14px 16px;\r\n"
				+ "  background-color: inherit;\r\n"
				+ "  font-family: inherit;\r\n"
				+ "  margin: 0;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+"form[action=\"logout\"]{\r\n"
				+" margin: 0;"
				+ "}\r\n"
				+ ".logout-form:hover .logoutBtn {\r\n"
				+ "  background-color: #04a595b0;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ ".logoutBtn:hover{\r\n"
				+ "  display: block;\r\n"
				+ "}\r\n"
				+ ".logout-form{\r\n"
				+ "	float:right;\r\n"
				+ "	overflow: hidden;\r\n"
				+ "}\r\n"
				+ ".catalog-list{\r\n"
				+ "	margin-top:45px;\r\n"
				+ "	margin-left: auto;\r\n"
				+ "    width: 85%;\r\n"
				+ "}\r\n"
				+ ".book{\r\n"
				+ "	margin-top:10px;\r\n"
				+ "}\r\n"
				+ "#title{\r\n"
				+ "	font-size: 20px;  \r\n"
				+ "	color:#543d34;\r\n"
				+ "	background-color: #dbd3a3;\r\n"
				+ "}\r\n"
				+ "p{\r\n"
				+ "	padding: 10px;\r\n"
				+ "}\r\n"
				+ ".genreBtn{\r\n"
				+ "	cursor: pointer;\r\n"
				+ "	font-size: 15px;  \r\n"
				+ "  	border: none;\r\n"
				+ "  	outline: none;\r\n"
				+ "  	color: #543d34;\r\n"
				+ "  	padding: 10px;\r\n"
				+ "  	background-color: #dbd3a375;;\r\n"
				+ "  	font-family: inherit;\r\n"
				+ "  	margin: 0;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ ".genreBtn:hover{\r\n"
				+ "	background-color: #dbd3a3;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "button[value=\""
				+ genre
				+ "\"]{\r\n"
				+ "	background-color: #dbd3a3;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "img{\r\n"
				+ "	width: 140px;\r\n"
				+ "    height: auto;\r\n"
				+ "    display:inline;\r\n"
				+ "}\r\n"
				+ ".book-details{\r\n"
				+ "	display: inline-block;\r\n"
				+ "    width: 70%;\r\n"
				+ "    margin-left: 30px;\r\n"
				+ "}"
				+ "@media print {\r\n"
				+ "  				html, body {\r\n"
				+ "    				display: none;\r\n"
				+ "  				}\r\n"
				+ "			}"
				+ "</style>\r\n"
				+ "</head>\r\n"
				+ "<body style=\"font-family: Arial, Helvetica, sans-serif; margin:0\">\r\n"
				+ "	<div class=\"navbar\">\r\n"
				+ "  		<a>Home</a>\r\n"
				+ "  		<div class=\"logout-form\">\r\n"
				+ "    		<form action=\"logout\" method=\"post\">\r\n"
				+ "				<button class=\"logoutBtn\" type=\"submit\" id=\"logout-submit\" >Logout</button>\r\n"
				+ "			</form>\r\n"
				+ " 		</div> \r\n"
				+ "	</div>\r\n"
				+ "	\r\n"
				+ "    \r\n"
				+ "	<div class=\"catalog-list\">\r\n"
				+ "<h1 style=\"color:#543d34;\">Books</h1>"
				+ "		<form action=\"data\" method=\"get\">\r\n"
				+ "			<button class=\"genreBtn\" type=\"submit\" name='genreType'  value=\"All\">All</button>\r\n"
				+ "        	<button class=\"genreBtn\" type=\"submit\" name='genreType' value=\"Computer\">Computer</button>\r\n"
				+ "        	<button class=\"genreBtn\" type=\"submit\" name='genreType' value=\"Fantasy\">Fantasy</button>\r\n"
				+ "        	<button class=\"genreBtn\" type=\"submit\" name='genreType' value=\"Romance\">Romance</button>\r\n"
				+ "        	<button class=\"genreBtn\" type=\"submit\" name='genreType' value=\"Horror\">Horror</button>\r\n"
				+ "        	<button class=\"genreBtn\" type=\"submit\" name='genreType' value=\"Science Fiction\">Science Fiction</button>\r\n"
				+ "   		 </form>\r\n"
				+"		<xsl:for-each select=\"catalog/book\">\r\n";
		if(!genre.equalsIgnoreCase("All")) {
			xsl= xsl + "<xsl:if test=\"genre='"+genre+ "'\">";
		}
		xsl= xsl+ "				<div class=\"book\">\r\n"
				+ "<img src=\"data:image/jpg;base64,"
				+imgBase64
				+"\" alt=\"img\" />"
				+ "<div class=\"book-details\">"
				+ "					<p id=\"title\">Title: <xsl:value-of select=\"title\"/></p>\r\n"
				+ "					<p>Author: <xsl:value-of select=\"author\"/> </p>\r\n"
				+ "					<p>Publish Date: <xsl:value-of select=\"publish_date\"/></p>\r\n"
				+ "					<p>Genre: <xsl:value-of select=\"genre\"/></p>\r\n"
				+ "					<p>Description: <xsl:value-of select=\"description\"/></p>\r\n"
				+ "</div>"
				+ "				</div>\r\n";
		if(!genre.equalsIgnoreCase("All")) {
			xsl= xsl + "</xsl:if>";
		}
		
		xsl =xsl+ "		</xsl:for-each>\r\n"
				+ "	</div>\r\n"
				+ "</body>\r\n"
				+ "<script>\r\n"
				+ "  		document.addEventListener('contextmenu', event => event.preventDefault());\r\n"
				+ "	</script>"
				+ "</html>\r\n"
				+ "</xsl:template>\r\n"
				+ "</xsl:stylesheet>\r\n";
	
		
		String xslpath = "C:/Users/Cheuk Wai/eclipse-workspace/itec4020Asm2/src/main/webapp/books.xsl";
		//update the xsl
		Files.writeString(Paths.get(xslpath), xsl);
		
	}
	
	public String imgBase64StringConversion(String path) throws IOException {

	    // read image from file
	    FileInputStream stream = new FileInputStream(path);

	    // get byte array from image stream
	    int bufLength = 2048;
	    byte[] buffer = new byte[2048];
	    byte[] data;

	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    int readLength;
	    while ((readLength = stream.read(buffer, 0, bufLength)) != -1) {
	        out.write(buffer, 0, readLength);
	    }

	    data = out.toByteArray();
	    String imageString = Base64.getEncoder().withoutPadding().encodeToString(data);

	    out.close();
	    stream.close();
		return imageString;
		
	}

}
