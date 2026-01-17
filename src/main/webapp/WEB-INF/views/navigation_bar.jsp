<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link href="css/navigation_bar.css" type="text/css" rel="stylesheet">
</head>
<body>
	<% 
	   String user_email=null, name=null;
		user_email=(String)session.getAttribute("adminEmail");
	 	name = (String) request.getAttribute("name");
	 	
     %>
<header>
  <nav id="nav_bar">
    <ul>
      <li>
        <a id="menu_list">
          <span class="material-symbols-outlined" id="menu">menu</span>
        </a>

        <ul id="sidebar_left">
        
          <li><a href="cropInformationManagement"  <%-- <%if("cropManagement".equals(name)||name==null){%>Style="color: blue;" <%}%> --%>>Crops information Management</a></li>
         <!--  <li><a href="cropInformationManagement.jsp">Crops information</a></li> -->
          <li><a href="#logOut">Feedback/Complain Management</a></li>
          <li><a href="new_admin_form">Add New Admin</a></li>
          <li><a href="logOut">LogOut</a></li>
        </ul>

        <a id="webName" href="webName"><span id="Krashi">Krashi</span><span>Unnati</span></a>
      </li>
    </ul>

    <ul id="nav_ul">
      <li>
        <div id="profile_box">
          <a href="profile.jsp">
            <span class="material-symbols-outlined nav_default_pic">person</span>
          </a>
        </div>
      </li>
    </ul>
  </nav>
</header>
<script src="js/sideBar.js"></script>
</body>
</html>