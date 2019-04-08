<!-- //Updated as of 3:01PM 4/4/2019 -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="com.service.ServClass" %>
    <%@ page import="com.bean.Trainer" %>
    <%@ page import="com.bean.Course" %>
    <%@ page import="java.util.*" %>
    
    
      <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
      
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">

<title>Program Allocated</title>
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
	padding: 0;
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
	position: relative;
	bottom: -30px;
	right: 100px;
	
}
 header h1{
	
	color: white;
	font-weight: 500;
	display: inline-block;
	text-align: center;
	width: 100%;
	margin-top: -40px;
	
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
    .box{
	width: 83%;
	padding: 30px;
	margin-left:16%;
	top: 30%;
	background: #191919;
	text-align: center;
	border-radius: 15px 15px 15px 15px;
	left: 55%; 
	color: white;
	margin-top: 25px;
	margin-bottom: 0px;
	}

.box button[type = "submit"]{
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
.box button[type = "submit"]:hover{
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

<body>
<header>
		<div class="main">
		<c:set var="user" value="${sessionScope.User}" > </c:set>
		<c:set var="username" value="${sessionScope.UserName}" > </c:set>
			<h7 align="left-top"><c:out value="Welcome ${username}"></c:out></h7>
			<h1 align="center"><c:out value="${user}">Program Allocation</c:out> </h1>
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
        <li class="active"><a href="UserUtilities?Action=Home">Home&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-home"></span></a></li>
              
        
      </ul>
    </div>
  </div>
</nav>
<form  class="box" id="box">
<table align="center" width="100%">
<tr><td>S.No.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td>Course ID &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td>Course Name &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td>Date Of Beginning&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td>Date of end&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	</tr>
<tr>	<% 
		
		HttpSession hsn = request.getSession(false);
		ServClass dbService = new ServClass();
		
		//Get the trainer object
		Trainer t = ((ArrayList<Trainer>) hsn.getAttribute("UserObjectList")).get(0);
		
		//Map with a String key [<courseId><" "><Integer Index>] and the corresponding trainer as value.
		HashMap<String, Trainer> pm = (HashMap<String, Trainer>)dbService.getRecordsByTId(t.getTrainerId());
		
		Set<String> kset= pm.keySet();
		  
		//Table index
		int i = 0;
		  	
		//Print all courses and their names
		for(String s :kset){
			%>
			<td><%= ++i %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<% Course  c = dbService.getCourseById(Integer.parseInt(s.split(" ")[0]));
			%>
			<td><%= c.getCourseId() %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td><%= c.getCourseName() %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td><%= (pm.get(s)).gettDateOfBegin() %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td><%= (pm.get(s)).gettDateOfEnd() %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		</tr>
			<% 
			}
			%>
	
</table>
	</form>

</body>
</html>