<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
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
			<jsp:include page="navigation_bar.jsp" />
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

		<jsp:include page="dashboarMainSection.jsp" />
	</main>
	<!--footer-->
	<jsp:include page="footer.jsp" />
	
<%}else{ %>
	<p>Something went wrong, You are login</p>
<% }%>	
</body>
</html>