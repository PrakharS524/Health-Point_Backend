<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/form.css" rel="stylesheet" type="text/css">
</head>
<body>
	<header>
			<jsp:include page="../navigation_bar.jsp" />
	</header>
	<main id=main>
        <form action="addNewCategoryInfo" method="post">
        	<p class="heading">Add New Category</p>
			<div class="form-group">
				<label for="category">Category Name:</label> <input
					type="text" id="category" name="category"
					pattern="[A-Za-z0-9 ()]+"
					title="Only letters (A-Z, a-z, ()) and numbers (0-9) allowed."
					placeholder="enter new category, duplicate not allow" maxlength="30" autofocus required>
			</div>
		     <!-- Existing categories dropdown -->
		    <div class="form-group">
		       <label for="categoryId">Existing Category Name check  :</label>
				<select id="categoryId" name="categoryId"  readonly>
					<option value="" disabled selected hidden>Click To Check Only, Not Fill</option>
					<c:forEach var="categoryDTO" items="${categoryList}">
						<c:if test="${categoryDTO.display}">
						    <option value="${categoryDTO.id}">
						        ${categoryDTO.category} 
						    </option>
					   </c:if>
					</c:forEach>
				</select>
		    </div>
			<input type="submit" value="Submit">
        </form>
        
        </main>
</body>
</html>


