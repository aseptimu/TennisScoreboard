<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Таблица матча</title>
</head>
<body>
    <c:set var = "salary" scope = "session" value = "${2000*2}"/>
    <c:if test = "${salary > 2000}">
        <p>My salary is:  <c:out value = "${salary}"/><p>
    </c:if>
</body>
</html>
