<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/image_upload.css" rel="stylesheet" type="text/css">
</head>
<body>
	<p id="imageUploadHeading">Image Upload</p>
	<div class="add_image_container">
	  <input type="file" id="image_input" name="image_input" accept="image/*" required hidden>
	  <label for="image_input" id="image_input_label">
	    <div id="upload_text">Upload Image</div>
	    <img id="profile_pic" src="#" alt="Preview" style="display:none;" />
	  </label>
	 </div>
</body>
<script src="js/image_upload.js"></script>
</html>