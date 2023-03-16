This project is using eclipse to run the code.
1. Create a Dynamic Web Project called itec4020Asm2
2. Set up and configure with Apache Tomcat (choose the version you are using)
3. Create a package called itec4020Asm2 
4. Put all servlets DataServlet, loginHandle and logoutHandle under the package created in step 3
5. Put the web.xml in /webapp/WEB-INF/
6. Put login.jsp, books.xsl, catalog.xml and bookCover.jpg under /webapp/
7. Change the the following path based on the directory of your eclipse workspace:
File xmlfile = new File("C:/Users/Cheuk Wai/eclipse-workspace/itec4020Asm2/src/main/webapp/catalog.xml");
String imgBase64 = imgBase64StringConversion("C:/Users/Cheuk Wai/eclipse-workspace/itec4020Asm2/src/main/webapp/bookCover.jpg");
File xslfile = new File("C:/Users/Cheuk Wai/eclipse-workspace/itec4020Asm2/src/main/webapp/books.xsl");

8. Run Tomcat server
9. You can using this url to access the website http://localhost:8080/itec4020Asm2/
10. You can also right click login.jsp run the code on server.
11. Enter the following Id and password then you can login the page:
	UserId = user 
	Password = 12345

