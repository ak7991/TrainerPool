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
	padding: 0;
	width: 100%;
	font-family: sans-serif;
	height: 15%;
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
 header h1{
	
	color: white;
	font-weight: 50;
	display: inline-block;
	text-align: center;
	width: 80%;
	
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
	width: 100%;
	padding: 40px;
	margin-left:40px;
	top: 0%;
	background: #191919;
	text-align: center;
	border-radius: 15px 15px 15px 15px;
	left: 35%; 
	color: white;
	margin-top: 25px;
	margin-bottom: 0px;
	}
.box input[type = "text"],.box input[type ="password"],.box input[type="email"]{
	border: 0;
	background: none;
	display: inline-block;
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

<body>
<header>
		<div class="main">
		<c:set var="user" value="${sessionScope.User}" > </c:set>
		<c:set var="username" value="${sessionScope.UserName}" > </c:set>
			<h7 align="left-top"><c:out value="Welcome ${username}"></c:out></h7>
			<h1 align="center">Allocation</h1>
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
        <li class="active"><a href="#">Home<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-home"></span></a></li>
              
      
      </ul>
    </div>
  </div>
</nav>

<form  class="box" id="box">
<table>
<tr><td>Trainer ID</td>
<td>First Name</td>
<td>Last Name</td>
<td>Gender</td>
<td>COntact NUmber</td>
<td>Email</td>
<td>Date Of Beggiein</td>
<td>Date of end</td>
	</tr>
<tr>		<script>
		   ServClass srv = new ServClass();
		   List<Trainer> list=srv.getAllTrainers();
		   Iterator<Trainer> itr = list.iterator();
		   </script>
		<script>
		while(itr.hasNext())
		{
			Trainer t = itr.next();
		
			</script>
		<td>	<script>	t.getTrainerId()	</script> </td>
			
			<script>		}	</script>	
			
		
		
		<td>
	</tr>
</table>
	</form>
<div class="footer">
  		<p>Copyright wali line</p>
	</div>
</body>
</html>