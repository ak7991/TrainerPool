<!-- //Updated as of 1:42PM 3/4/2019 -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
     <%@ page import="com.service.ServClass" %>
    <%@ page import="com.bean.Trainer" %>
    <%@ page import="com.bean.Skill" %>
    <%@ page import="java.util.*" %>
    <%@ page import="java.text.*" %>
      <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
      
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
<title>Trainer Dashboard</title>
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
	position: relative;
	bottom: -30px;
	right: 100px;
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
      margin-top: 5px;
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
   
}  .box{
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
			<h1 align="center"><c:out value="${user}"></c:out> DashBoard</h1>
			<div class="dropdown">
			<button href="UserUtilities?Action=Home" onclick="myFunction()" class="dropbtn"><i class="fas fa-user-circle fa-2x" style="color: white;"></i></button>
			  <br><br>
			  <div class="dropdown-content">
			    <a href="UserUtilities?Action=EditProfile">Edit Profile</a>
			    <a href="UserUtilities?Action=EditPassword">Edit Password</a>
			    <a href="UserUtilities?Action=LogOut">Logout</a>
			  </div>
			</div>
			<!-- <input type="submit" name="Action" value="Edit Profile" style="float: right;"> -->
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
        <li class="active"><a href="UserUtilities?Action=Home">Home<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-home"></span></a></li>
         <li ><a href="UserUtilities?Action=CreateRequest">Create Request<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-th-list"></span></a></li>        
        <li ><a href="UserUtilities?Action=ProgramsAllocated">Programs Allocated<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-th-list"></span></a></li>        
         
		</ul>
    </div>
  </div>
</nav>

<form  class="box" id="box" action="UserUtilities">
<table>
<tr><td>Trainer ID&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td>First Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td>Last Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td>Gender&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td>Contact Number&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td>Email&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td>Date Of Beginning&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td>Date of end&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td>Skill&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	</tr>
<tr>	
		<% 
		   ServClass srv = new ServClass();
		   
		   HttpSession hsn=request.getSession(false);
		   String Username= (String) hsn.getAttribute("UserName");
		   List<Trainer> list=srv.getTrainersByUsername(Username);
		   Iterator<Trainer> itr = list.iterator();
		  
		
		while(itr.hasNext())
		{
			Trainer t = itr.next();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			String dob = " ";
			String doe = " ";
			
			//For trainers who have created a request
			if(t.gettDateOfBegin() != null){
				dob = sdf.format(t.gettDateOfBegin());
				doe = sdf.format(t.gettDateOfEnd());
			}
			//Trainers who are yet to create a request and have dates as null
			else{
				dob = "----";
				doe = "----";
			}
		%>
			
		<td><%= t.getTrainerId() %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<td><%= t.gettFname() %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<td><%= t.gettLname() %> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<td><%= t.gettGender() %> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<td><%= t.gettContactNumber() %> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<td><%= t.getTmail() %> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<td><%= dob %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<td><%= doe %> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		
		<td><%
		   
		 List<Skill> skillList=srv.getSkillsByTId(t.getTrainerId());
		for(Skill skl:skillList)
		{%>
		
		<%= skl.getSkillName()%>&nbsp;
		<% } %>	
		</td></tr>
		<% } %>	
</table>
	
	

	</form>


<div class="footer">
  		<p>Copyright@2019</p>
	</div>
</body>
</html>