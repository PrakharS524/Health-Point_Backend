<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>KrashiUnnati/cropMagegement</title>
	<link href="css/style.css" rel="stylesheet" type="text/css">
	<link href="css/dashboarMainSection.css" rel="stylesheet" type="text/css">
</head>
<body>

<%
/* 		model.addAttribute("errorList", "Succesfull login"); */
	String adminEmail=null, adminId=null, adminName=null;
	int adminPosition=3;	
	if (session.getAttribute("adminEmail")!= null) {
		adminEmail=(String)session.getAttribute("adminEmail");
		adminName=(String)session.getAttribute("adminName");
		adminPosition=(Integer)session.getAttribute("adminPosition");
		adminId=String.valueOf(session.getAttribute("adminId"));
	}
%>
<%if(adminEmail !=null && adminName !=null && adminPosition<=2 && adminId !=null){ %>
	<header>
			<jsp:include page="../navigation_bar.jsp" />
	</header>
	<main id="main"> 
	 <c:choose>
		    <c:when test="${not empty errorMessage}">
		        <p class="message_error">${errorMessage}</p>
		    </c:when>
		    <c:otherwise>
		        <c:if test="${not empty message}">
		            <p class="message">${message}</p>
		        </c:if>
		    </c:otherwise>
	 </c:choose>
	 <table class="admin-table">
		  <tr>
			   <td>Add new crops</td>
			   <td>:</td>
			   <td>
			   		<a href="addNewCropsInfoForm" class="btn-link"><button class="btn">Add</button></a>
			   </td>
		  </tr>
		  <tr>
			    <td>Add new Category</td>
			    <td>:</td>
			    <td>
			      	<a href="addCategoryInfoForm" class="btn-link"><button class="btn">Add Category</button></a>
			    </td>
		  </tr>
		  <tr>
			    <td>Update of crops</td>
			    <td>:</td>
			    <td>
			     	 <a href="updateOfCrops" class="btn-link"><button class="btn">Update</button></a>
			    </td>
		  </tr>
		  <tr>
			    <td>Delete of crops</td>
			    <td>:</td>
			    <td>
			     	 <a href="deleteOfCrops" ><button class="btn_denger">Delete</button></a>
			    </td>
		  </tr>
		  <tr>
			    <td>Update image of crops</td>
			    <td>:</td>
			    <td>
			      	<a href="updateImageOfCrops" class="btn-link"><button class="btn">Update Image</button></a>
			    </td>
		  </tr>
		</table>

	</main>
	<!--footer-->
	<jsp:include page="../footer.jsp" />
<%}else{ %>
	<p>Something went wrong, You are login</p>
<% }%>
</body>
</html>