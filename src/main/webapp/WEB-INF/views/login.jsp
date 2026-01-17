<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Login</title>
   <link href="css/login.css" rel="stylesheet" type="text/css">
</head>
<body>
  <main id="main" >
    <div id="logo">Login</div>
    <h1 id="title">Welcome back</h1>
    <p class="sub">Sign in to continue</p>
    <c:forEach var="message"  items="${errorList}" >
    	<center><i>${message}</i></center><br>
    </c:forEach>
    <form action="login" method="post">
      <div class="field">
        <label for="email">Email</label>
        <input class="input" type="email" id="email" name="email" placeholder="you@example.com" autofocus required />
      </div>
      <div class="field">
        <label for="password">Password</label>
        <input class="input" type="password" id="password" name="password" placeholder="••••••••" minlength="6" required />
      </div>
      <button type="submit"  class="btn">Login</button>
    </form>
  </main>
</body>
</html>
