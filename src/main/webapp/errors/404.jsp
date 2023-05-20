<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>404 Page Not Found</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 50px;
      text-align: center;
    }

    h1 {
      font-size: 36px;
      margin-bottom: 30px;
    }

    p {
      font-size: 18px;
      margin-bottom: 20px;
    }
  </style>
</head>
<body>
<h1>404 Page Not Found</h1>
<p>Oops! The page you are looking for could not be found.</p>
<c:choose>
  <c:when test="${not empty reason}">
    <p>Reason: ${reason}</p>
  </c:when>
  <c:otherwise>
    <p>Please check the URL or go back to the <a href="/">homepage</a>.</p>

  </c:otherwise>
</c:choose>
</body>
</html>
