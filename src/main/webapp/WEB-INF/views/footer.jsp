<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link href="css/footer.css" type="text/css" rel="stylesheet">
</head>
<body>
	<% 
	   String user_email1=null;
	user_email1=(String)session.getAttribute("user_email");       
     %>
	<footer id="footer">
	 <div class="information-container">
		 <div class="information-links">
	            <p class="topicInfo">Quick Links</p>
	            <ul>
	              <li><a href="webName">Home</a></li>
	              <!-- <li><a href="#">krashi</a></li>
	              <li><a href="#">krashi</a></li>
	              <li><a href="#">krashi</a></li>
	              <li><a href="#">krashi</a></li> -->
	            </ul>
	     </div>
         <div class="information-about">
           <a  href="webName" id="webnamef"><span id="Krashif">Krashi</span><span>Unnati</span><a>
           <p>Empowering farmers with smart crop trading and trusted agriculture information.
				Buy, sell, and stay informed — all in one place.</p>
         </div>
	      
         <div class="information-contact">
           <p class="topicInfo">Contact Us</p>
           <p>Email: mdjavedmansoori22@gmail.com</p>
           <p>Phone: +91 74159 48500</p>
           <p>Location: Indore, India</p>
         </div>
	      
         <div class="information-social">
           <p class="topicInfo">Follow Us</p>
             <a href="#">facebook</a>
             <a href="#">Twitter</a>
             <a href="#">LinkedIn</a>
         </div>
	</div>
		<div class="information-bottom">
          <p>&copy; 2025 KrashiUnnati. All rights reserved.</p>
        </div>
	</footer>

</body>
</html>