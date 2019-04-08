<!-- Updated as of 3:22PM, 4/1/2019 -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="com.service.ServClass" %>
    <%@ page import="com.bean.*" %>
    <%@ page import="java.util.*" %>
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
.box{
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
	
	.search{
	margin-left:16%;
	margin-right:6%;
	top: 5%;
	text-align: center;
	color: white;
	}

.search input[type = "text"]{
	border: 0;
	background : none;
	margin: 20px auto;
	text-align: center;
	border: 2px solid #2ecc71;
	outline: none;
	color: white;
	border-radius: 24px;
	transition: 0.25s;
	cursor: pointer;
}
.search input[type = "text"]:hover{
	background: #2ecc71;
}
.search input[type = "submit"]{
	border: 0;
	background : none;
	display: block;
	margin: 20px auto;
	text-align: center;
	border: 2px solid #000000;
	outline: none;
	color: white;
	transition: 0.25s;
	cursor: pointer;
}
.search input[type = "submit"]:hover{
	background: #2ecc71;
}
.search input[type = "date"]{
	border: 0;
	background : none;
	margin: 20px auto;
	text-align: center;
	border: 2px solid #2ecc71;
	outline: none;
	color: white;
	border-radius: 24px;
	transition: 0.25s;
	cursor: pointer;
}
</style>

<body>
<header>
		<div class="main">
		<c:set var="user" value="${sessionScope.User}" > </c:set>
		<c:set var="username" value="${sessionScope.UserName}" > </c:set>
			<h7 align="left-top"><c:out value="Welcome ${username}"></c:out></h7>
			<h1 align="center">Trainer Allocation</h1>
		</div>
		<div class="logout">
		<h4 align="right-top"><a href="AdminUtilities?Action=LogOut">Log Out</a></h4>
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
        <li class="active"><a href="AdminUtilities?Action=Home">Home&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-home"></span></a></li>
         
      </ul>
    </div>
  </div>
</nav>

<div class="search">
<input type="text" name="CId" placeholder="Course Id" required="required"  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="text" name="FilterFromDate" placeholder="mm/dd/yyyy" required="required">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="text" name="FilterToDate" placeholder="mm/dd/yyyy" required="required">&nbsp;&nbsp;&nbsp;&nbsp;
<input type="submit" name="FilterTrainers" value="Filter"  >
</div>	

<form  class="box" id="box">


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
<td>Allocate&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	</tr>
<tr>	
	<% 
		
		//Ensure old session is used (new is not made)
		HttpSession hsn = request.getSession(false);
		//Getting Program Map from Admin Utilities (FilterTrainers)
		HashMap<Skill, ArrayList<Trainer>> pm = (HashMap<Skill, ArrayList<Trainer>>) hsn.getAttribute("ProgramMap");
		Set<Skill> kset= pm.keySet();
		ArrayList<Trainer> al= new ArrayList<Trainer>();
		//Get all trainers that know the skill required to teach a course
		for(Skill s: kset )
			{
				boolean flag = false;
				
				//Intersection of lists of trainers => This gives a list of trainers who know ALL the skills required for a course.
				if(!flag){
					//All the trainers who know the 1st skill for the course.
					al.addAll((ArrayList<Trainer>)pm.get(s));
					flag = true;
				}
				else{
					//Subsequently intersecting all trainers.
				    al.retainAll((ArrayList<Trainer>)pm.get(s));
				}
				
			  }

		//Display all trainers (with checkbox containing corresponding trainer's Id).
		for(Trainer t:al){
			%>
			<td><%= t.getTrainerId() %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td><%= t.gettFname() %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td><%= t.gettLname() %> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td><%= t.gettGender() %> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td><%= t.gettContactNumber() %> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td><%= t.getTmail() %> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td><%= t.gettDateOfBegin() %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td><%= t.gettDateOfEnd() %> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<% 
			
			ServClass srv = new ServClass();
			//Get all skills for a given trainer
			List<Skill> skillList = (List<Skill>)srv.getSkillsByTId(t.getTrainerId());
			 %>
			<td><%
				for(Skill skl:skillList)
					{%>
					
					<%= skl.getSkillName()%>&nbsp;
					<% } %>	
					</td>
					
				<td>&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="UpdateAllocation" value="<%= t.getTrainerId() %>" style="display: flex" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</td></tr><%
			}
			
		%>
 
</table>
	<td><input type="submit" name="AdminAction" value="AllocateTrainers" style="display: flex" >
	
	</form>
<div class="footer">
  		<p>Copyright wali line</p>
	</div>
</body>
</html>