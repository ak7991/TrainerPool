<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Admin Login form</title>


	<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
	<style type="text/css">
		
		body{
	margin: 0;
	padding: 0;
	font-family: sans-serif;
	background: #34495e;
}
.box
{
	width: 300px;
	padding:40px;
	position: absolute;
	top:55%;
	left:50px;
	transform: translate(165%,-50%);
	background: #191919;
	text-align: center;
	border-radius: 15px 15px 15px 15px;
}
.box h1{
	color: white;
	text-transform: uppercase;
	font-weight: 500;
	
}
.box input[type = "text"],.box input[type = "password"]{
	border: 0;
	background: none;
	display: block;
	margin : 20px auto;
	text-align: center;
	border: 2px solid #3498db;
	padding: 14px 10px;
	width: 200px;
	outline: none; 
	color : white;
	border-radius: 24px;
	transition: 0.25s;
}
.box input[type = "text"]:focus,.box input[type ="password"]:focus{
	width: 240px;
	border-color : #2ecc71;
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
	font-weight: 500;
	display: inline-block;
	text-align: center;
	width: 100%;
	
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
	</style>
	
</head>
<body>

<header>
		<div class="main">
			<h1 align="center">eTrainer Pool Management</h1>
		</div>
	</header>

<form class="box" action="../LandingPage">
	<h1> Admin Login</h1>
	<input type="text" name="AdminUsername" placeholder="Username" required="required">
	<input type="password" name="AdminPassword" placeholder="Password" required="required">
	<input type="submit" name="Action" value="Login">
	<input type="hidden" name="User" value="Admin">
	
</form>

<div class="footer">
  		<p>Copyright wali line</p>
	</div>
</body>
</html>