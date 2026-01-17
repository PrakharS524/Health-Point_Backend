<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>KrashiUnnati/addNewCrop</title>
	<link href="css/form.css" rel="stylesheet" type="text/css">
</head>
<body>
	<header>
			<jsp:include page="../navigation_bar.jsp" />
	</header>
	<main id=main>
		<form action="addNewCropInfo" method="post" enctype="multipart/form-data">
	       	<p class="heading">Add New crop</p>
	       	<c:forEach var="message"  items="${errorList}" >
		    	<center style="color:red"><i>${message}</i></center><br>
		    </c:forEach>
	       	<label for="categoryId">Category Name :</label>
			<select id="categoryId" name="categoryId"  required>
				<option value="" disabled selected hidden>Choose a category</option>
				<c:forEach var="categoryDTO" items="${categoryList}">
					<c:if test="${categoryDTO.display}">
					    <option value="${categoryDTO.id}">
					        ${categoryDTO.category} 
					    </option>
				   </c:if>
				</c:forEach>
			</select>
			
		    <div class="form-group">
		        <label for="cropName">Crop Name:</label>
		        <input type="text" id="cropName" name="cropName"
		               pattern="[A-Za-z0-9 ()]+"
		               title="Only letters (A-Z, a-z, () and numbers (0-9) allowed."
		               placeholder="Enter new crop name"
		               maxlength="50" required>
		    </div>
			
			<!-- State Select Field -->
			<div class="form-group"> 
			    <label for="state">State:</label>
			    <input list="statesList" id="state" name="state" required placeholder="Select State" />
			    <datalist id="statesList">
			        <c:forEach var="state" items="${stateAll}">
			            <option value="${state}"></option>
			        </c:forEach>
			    </datalist>
			</div>
	        <jsp:include page="image_upload.jsp" />
			
			 <div class="form-group">
			        <label for="file_input">Upload File:</label>
			        <input type="file" id="file_input" name="file_input" accept=".txt, .pdf" required>
			        <small>Only .txt and .pdf(plain text) files allowed.</small>
			   </div>
			<button type="submit" class="submit">Submit</button>
	       </form>
     </main>
<!-- 	<script src="js/addNewCrops.js"></script> -->

</body>
</html>