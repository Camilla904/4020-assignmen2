<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
session=request.getSession(true); 
if(session.getAttribute("login_status")=="failed"){
	session.setAttribute("ErrorMsg", "Incorrect ID or password.");
	
}
else{
	session.setAttribute("ErrorMsg", "");

}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="BIG5">
		<title>Group1</title>
		<style>
			#container{
				margin-top: 15vh;
			}
			body {
  				background-color: linen;
  				font-family: "Roboto", sans-serif;
  				color: #543d34;

			}				
			#login-container{
				position:relative;
				box-sizing: border-box;
				margin-left:auto;
				margin-right:auto;
				display:block;
				width:590px;
				height:50vh;
				padding: 50px 40px;
				background-color:white;
				box-shadow: 0px 25px 15px -10px rgba(0, 0, 0, 0.3);
			}
			input[type="text"], input[type="password"]{
				padding: 10px 20px;
				border: 1px solid #999;
				border-radius: 3px;
				display: block;
				width: 70%;
				margin-bottom: 30px;
				margin-left:auto;
				margin-right:auto;
				box-sizing: border-box;
				outline: none;
			}

			input[type="text"]:focus, input[type="password"]:focus {
				border-color: #017e72c2;
			}

			button:hover{
				background-color: #017e72c2;
			}

			button{
				border: 1px solid #c6b883;
				padding: 10px 20px;
				border-radius: 3px;
				background-color: #c6b883;
				color: white;
				text-transform: uppercase;
				letter-spacing: 1px;
				display: block;
				cursor: pointer;
				margin-left: auto;
    			margin-right: auto;
    			margin-top: 40px;
    			width: 35%;
			}
			
			.form_label{
				margin-left: auto;
    			margin-right: auto;
    			margin-bottom: 5px;
    			width: 70%;
			}
			
			.page_name{
				font-size:75px;
				font-variant: small-caps;
				color:#017e72c2;
				text-align:center;
				letter-spacing: 1.5px;
				margin-bottom:20px;
			}
			form{
				margin-bottom: 20px;
			}
			span{
				color:red;
			}
			@media print {
  				html, body {
    				display: none;
  				}
			}
		</style>
	</head>
	<body>
		<div id="container">
			<div class="page_name">Sign In</div>
        	<div id="login-container">
            	<form action="login" method="post">
                	<div class="form_label"><label for="userId">User ID</label></div>
			    	<input type="text" placeholder="Enter User ID" name="userId" id="userId" required>
                	<div class="form_label"><label for="pwd">Password</label></div>
			    	<input type="password" placeholder="Enter Password" name="pwd" id="pwd" required>
                	<button type="submit" id="login-submit" name='verifyUser'>Sign In</button>
            	</form>
            	<span> ${sessionScope.ErrorMsg} </span>
        	</div>
    	</div>
	</body>
	<script>
  		document.addEventListener('contextmenu', event => event.preventDefault());
	</script>

</html>