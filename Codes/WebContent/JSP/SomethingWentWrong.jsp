<!-- //Updated as of 1:16PM 2/4/2019 -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
      <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
      
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<title>Insert title here</title>
</head>

<style>
body{
	margin: 0;
	padding: 0;
	font-family: sans-serif;
	background: #34495e;
}
  
header {
	background :#191919;
	margin:0; 
	padding: 30;
	width: 100%;
	font-family: sans-serif;
	height: 10vh;
	background-size: cover;
	background-position: center;
 }
  header h7{
	color: white;
	font-weight: 20;
	display: inline-block;
	text-align: right;
	width: 100%;
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
  header h1{
 
 	margin:0; 
	padding: 30;
	width: 80%;
	font-family: sans-serif;
	height: 10vh;
	color: white;
	font-weight: 50;
	display: inline-block;
	text-align: center;
	
	
}
body,html{
    height: 100%;
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
 .text{
	width: 83%;
	padding: 30px;
	margin-left:16%;
	margin-right:6%;
	top: 10%;
	background: #191919;
	text-align: center;
	border-radius: 15px 15px 15px 15px;
	left: 55%; 
	color: white;
	margin-top: 25px;
	margin-bottom: 0px;
	}
</style>

<body>
      <%
	  /*
	   *One of the only cases (case 1 of 3) of redirection to an html page right from the webpage.
	   */
	   
      //Check which user encountered an error
      String userType = (String) session.getAttribute("User");
      String targetHome = "";
      String targetLogout = "";
      
      if(userType != null){
    	  
    	  try{
	    	  boolean loginStatus = (Boolean) session.getAttribute("LoginStatus");
	    	  if(loginStatus){
		    	  if(userType.equals("Admin")){
		    		  targetHome = "AdminUtilities?Action=Home";
		    		  targetLogout = "AdminUtilities?Action=Logout";
		    	  }
		    	  else{
		    		  targetHome = "UserUtilities?Action=Home";
		    		  targetLogout = "UserUtilities?Action=Logout";
		    	  }
	    	  }
	    	  else{
	        	  targetHome = "HTML/Index.html";
	        	  targetLogout = "HTML/Index.html";
	    	 	 }
	    	}
    	  catch(NullPointerException e){
    		  e.printStackTrace();
    		  System.out.println("Login status not set while authentication, relogin");
        	  targetHome = "HTML/Index.html";
        	  targetLogout = "HTML/Index.html";
    	  }
      }  
      else{
    	  targetHome = "HTML/Index.html";
    	  targetLogout = "HTML/Index.html";
      }
      %>
<header>
		<div class="logout">
		<h4 align="right-top"><a href="<%=targetLogout %>">Log Out</a></h4>
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

        <li class="active"><a href="<%=targetHome%>">Home&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-home"></span></a></li>
        </ul>
    </div>
  </div>
</nav>
<div class="text">
		<h2 align="center">Oops! Something went wrong.</h2>
		<!-- //One of the only cases (case 2 of 2) of redirection to an html page right from the webpage. -->
		<a href="HTML/Index.html"> <h5 align="center">Redirect to Index page</h2>
		</a>
		
			</div>
<div class="footer">
  		<p>Copyright wali line</p>
	</div>
</body>
</html>