<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>Страница сыгранных матчей</title>
  <style>
    body {
      background-color: #6b6e78;
      margin: 0;
    }

    .center-content {
      background-color: gray; /* Цвет содержимого страницы */
      padding: 20px;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      text-align: center;
    }
    table {
      width: 100%;
      text-align: center;
      font-size: 40px;
      border-collapse: separate;
      padding: 10%;
    }
  </style>
</head>
<body>
<div class="center-content">
  <h1 style="color: #93d900">Страница сыгранных матчей</h1>
  <form id="filterForm" action="/matches" method="get">
    <label style="color: #93d900" for="playerName">Фильтр по имени игрока:</label>
    <input type="text" id="playerName" name="filter_by_player_name">
    <button type="submit">Искать</button>
  </form>
</div>

<table>
  <c:forEach var="match" begin="${listBegin}" end="${listEnd}" items="${matches}">
    <tr>
      <td>
        <c:choose>
          <c:when test="${match.winner.name eq match.player1.name}">
            <div style="background-color: green">${match.player1.name}</div>
          </c:when>
          <c:otherwise>
            <div style="background-color: darkred">${match.player1.name}</div>
          </c:otherwise>
        </c:choose>
      </td>
      <td>
        <c:choose>
          <c:when test="${match.player2.name eq match.winner.name}">
            <div style="background-color: green">${match.player2.name}</div>
          </c:when>
          <c:otherwise>
            <div style="background-color: darkred">${match.player2.name}</div>
          </c:otherwise>
        </c:choose>
      </td>
    </tr>
  </c:forEach>
</table>
<div style="display: flex; justify-content: center; align-items: center;">
  <a href="/matches?page=${page - 1}">< Предыдущая</a>&nbsp&nbsp&nbsp&nbsp
  <a href="/matches?page=${page + 1}">Следующая ></a>
</div>
</body>
</html>
