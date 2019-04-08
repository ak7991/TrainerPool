<!-- //Updated as of 5:51PM 8/4/2019 -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">

<title>Request Successful</title>
<style type="text/css">
body{
	margin: 0;
	padding: 0;
	font-family: sans-serif;
	background: #34495e;
}
header {
	background :#191919;
	margin:0; 
	padding: 0;
	width: 100%;
	font-family: sans-serif;
	height: 10%;
	background-size: cover;
	background-position: center;
 }
 header h7{
	
	color: white;
	font-weight: 20;
	display: inline-block;
	text-align: right;
	width: 100%;
	position: relative;
	bottom: -30px;
	right: 100px;
	
}
 header h1{
	margin:0; 
	padding-top: 0;
	width: 80%;
	font-family: sans-serif;
	height: 10vh;
	color: white;
	font-weight: 50;
	display: inline-block;
	text-align: center;
	padding-left: 80px;
	
}

header h4{
	color: white;
	font-weight: 20;
	display: inline-block;
	text-align: left;
	width: 100%;
	margin-top: 15px;
	margin-left: 5px;

}
body,html{
    height: 100%;
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
 nav.sidebar, .main{
    -webkit-transition: margin 200ms ease-out;
      -moz-transition: margin 200ms ease-out;
      -o-transition: margin 200ms ease-out;
      transition: margin 200ms ease-out;
  }
  .main{
    padding: 10px 10px 0 10px;
  }
 @media (min-width: 765px) {
    .main{
      position: absolute;
      width: calc(100% - 40px); 
      margin-left: 40px;
      float: right;
    }
    nav.sidebar:hover + .main{
      margin-left: 200px;
    }
    nav.sidebar.navbar.sidebar>.container .navbar-brand, .navbar>.container-fluid .navbar-brand {
      margin-left: 0px;
    }
    nav.sidebar .navbar-brand, nav.sidebar .navbar-header{
      text-align: center;
      width: 100%;
      margin-left: 0px;
    }
    
    nav.sidebar a{
      padding-right: 13px;
    }
    nav.sidebar .navbar-nav > li:first-child{
      border-top: 1px #e5e5e5 solid;
    }
    nav.sidebar .navbar-nav > li{
      border-bottom: 1px #e5e5e5 solid;
    }
    nav.sidebar .navbar-nav .open .dropdown-menu {
      position: static;
      float: none;
      width: auto;
      margin-top: 0;
      background-color: transparent;
      border: 0;
      -webkit-box-shadow: none;
      box-shadow: none;
    }
    nav.sidebar .navbar-collapse, nav.sidebar .container-fluid{
      padding: 0 0px 0 0px;
    }
    .navbar-inverse .navbar-nav .open .dropdown-menu>li>a {
      color: #777;
    }
    nav.sidebar{
      width: 200px;
      height: 100%;
      margin-left: -160px;
      float: left;
      margin-bottom: 0px;
    }
    nav.sidebar li {
      width: 100%;
    }
    nav.sidebar:hover{
      margin-left: 0px;
    }
    .forAnimate{
      opacity: 0;
    }
  }
   
  @media (min-width: 1330px) {
    .main{
      width: calc(100% - 200px);
      margin-left: 200px;
    }
    nav.sidebar{
      margin-left: 0px;
      float: left;
    }
    nav.sidebar .forAnimate{
      opacity: 1;
    }
  }
  nav.sidebar .navbar-nav .open .dropdown-menu>li>a:hover, nav.sidebar .navbar-nav .open .dropdown-menu>li>a:focus {
    color: #CCC;
    background-color: transparent;
  }
  nav:hover .forAnimate{
    opacity: 1;
  }
  section{
    padding-left: 15px;
  }
  .box
{
	width: 630px;
	padding:10px;
	position: absolute;
	top:55%;
	left:50px;
	transform: translate(70%,-50%);
	background: #191919;
	text-align: center;
	border-radius: 15px 15px 15px 15px;
}
.box h1{
	color: white;
	text-transform: uppercase;
	font-weight: 500;
	
}
.box input[type = "text"],.box input[type = "date"]{
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
.box input[type = "text"]:focus,.box input[type ="date"]:focus{
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
  .box input[type = "date"]:hover{
	background: #2ecc71;
}
.dropbtn{
	background: transparent;
	border: none;
	float: right;
}
.dropdown {
  position: relative;
  display: inline-block;
  float: right;
}

.dropdown-content {
  display: none;

  background-color: #f9f9f9;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

.dropdown-content a:hover {
background-color: #e0dcdb;
 border-radius: 10px;
}

.dropdown:hover .dropdown-content {
  display: block;
   border-radius: 10px;
   
}

</style>
</head>

<body>
<header>
		<div class="main">
		<c:set var="user" value="${sessionScope.User}" > </c:set>
		<c:set var="username" value="${sessionScope.UserName}" > </c:set>
			<h7 align="left-top"><c:out value="Welcome ${username}"></c:out></h7>
			<h1 align="center"><c:out value="${user}"></c:out>Request</h1>
		<div class="dropdown">
			<button href="#" onclick="myFunction()" class="dropbtn"><i class="fas fa-user-circle fa-2x" style="color: white;"></i></button>
			  <br><br>
			  <div class="dropdown-content">
			    <a href="UserUtilities?Action=EditProfile">Edit Profile</a>
			    <a href="#">Edit Password</a>
			    <a href="UserUtilities?Action=LogOut">Logout</a>
			  </div>
			</div>
			</div>
	</header>
<nav class="navbar navbar-default sidebar" role="navigation">
    <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-sidebar-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>      
    </div>
    <div class="collapse navbar-collapse" id="bs-sidebar-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="UserUtilities?Action=Home">Home&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-home"></span></a></li>
            
        
      </ul>
    </div>
  </div>
</nav>
<div class="box" action="Register">
<table>
<tr align="center"><td><h4 style="color: white;font-family: sans-serif;font-weight: 500">Enter your current password: </h4></td>
<td><input type="password" name="OldPassword"></td></tr>
<tr align="center"><td><h4 style="color: white;font-family: sans-serif;font-weight: 500">Enter your new password: </h4></td>
<td><input type="password" name="NewPassword"></td></tr>
<tr align="center"><td><h4 style="color: white;font-family: sans-serif;font-weight: 500">Retype your new password: </h4></td>
<td><input type="password" name="NewPasswordRetype"></td></tr>
<td colspan="2"><button type="submit" name="UpdatePassword">Update Password</button></td>
</table>	
	
</div>
<div class="footer">
  		<p>Copyright@2019</p>
	</div>
</body>
</html>