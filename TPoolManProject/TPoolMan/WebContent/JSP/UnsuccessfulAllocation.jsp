<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
      
      
<!DOCTYPE html>
<html>
<head>
<title>Unsuccessful Allocation</title>
	<meta charset="ISO-8859-1">
	
		<meta charset="utf-8">
  		<meta name="viewport" content="width=device-width, initial-scale=1">
  		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<style>
	    	
	body{
	margin: 0;
	padding: 0;
	font-family: sans-serif;
	background: #34495e;
}

header{
	background :#191919;
	margin:0; 
	padding: 0;
	width: 100%;
	font-family: sans-serif;
	height: 10vh;
	background-size: cover;
	background-position: center;
	
}

header h1{
	
	color: white;
	font-weight: 30;
	display: inline-block;
	text-align: center;
	width: 100%;
	
}
.heading{
	color: white;
	 bottom: 60%;
	text-transform: uppercase;
	font-family: sans-serif;
	position:fixed;
	font-size: 20px;
	width: 100%;
	color:white;
	text-align: center;

}

.login{
position: fixed;
   left:38%;
   bottom: 40%;
   width: 10%;
   color: black;
   text-align: center;
   height: 20px;
   font-size: large;
   font-family: sans-serif;

}


.signUp{
position: fixed;
   left:52%;
   bottom: 40%;
   width: 10%;
   color: black;
   text-align: center;
   height: 20px;
   font-size: large;
   font-family: sans-serif;

}

.footer {
   position: fixed;
   left: 0;
   bottom: 0;
   width: 100%;
   background-color: #191919 ;
   color: white;
   text-align: center;
   height: 20px;
   font-size: small;
   font-family: sans-serif;
   }
.box input[type = "submit"]{
	border: 0;
	background : none;
	display: block;
	margin: 20px auto;
	text-align: center;
	border: 2px solid #2ecc71;
	padding: 14px 40px;
	outline: none;
	color: white;
	border-radius: 24px;
	transition: 0.25s;
	cursor: pointer;
}
.box input[type = "submit"]:hover{
	background: #2ecc71;
}

</style>
</head>
<body>
<header>
		<div class="main">
			<h1 align="center">eTrainer Pool Management</h1>
		</div>
</header>
<%
HttpSession hsn=request.getSession(false);
int unAllocate=(Integer)hsn.getAttribute("UnsuccessfulUpdates");
%>
<div class="heading">
	<h1><%=unAllocate%> Unsuccessful Allocation</h1>
</div>
<form class="box" id="box"  action="./HTML/Index.html">
<div class="login">
<input type="submit" name="Login" value="Relogin" width="20%">
</div>
</form>	
<form class="box" id="box"  action="./HTML/SignUp.html">
<div class="signUp">
<input type="submit" name="SignUp" value="SignUp" width="20%">
</div>
</form>	
<div class="footer">
  		<p>Copyright wali line</p>
</div>


</body>
</html>