<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Таблица матча</title>
    <style>
        body {
            background-color: #6b6e78;
            margin: 0;
        }

        .content {
            background-color: gray;
            padding: 20px;
        }

        .buttons {
            display: flex;
            justify-content: center;
            gap: 1%;
        }

        table {
            font-size: 20px;
            margin-left: auto;
            margin-right: auto;
        }

        table caption {
            color: #93d900;
            font-size: 30px;
        }

        table, th, td {
            border: 7px solid gray;
            border-collapse: collapse;
            text-align: center;
            color: white;
        }

        th {
            background-color: #93d900;
        }

        td:last-child:not(th) {
            color: green;
            background-color: floralwhite;
        }
    </style>
</head>
<body>
<div class="content">
    <c:set var="match" value="${requestScope.match}" scope="request"/>
    <c:set var="winner" value="${match.winner eq 'FIRST_PLAYER' ? match.player1 : match.player2}"/>
    <table style="width: 40%">
        <caption>Match score</caption>
        <thead>
        <tr>
            <th style="background-color: gray"></th>
            <th>Set</th>
            <th>Game</th>
            <c:choose>
                <c:when test="${match.stage eq 'TIE_BREAK'}">
                    <th>Tie-Break</th>
                </c:when>
                <c:otherwise>
                    <th>Point</th>
                </c:otherwise>
            </c:choose>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td style="background-color: #93d900"><c:out value="${match.player1.name}"/></td>
            <td style="background-color: orange"><c:out value="${match.firstPlayerScore.set}"/></td>
            <td style="background-color: orange"><c:out value="${match.firstPlayerScore.game}"/></td>
            <td>
                <c:choose>
                    <c:when test="${match.stage eq 'TIE_BREAK'}">
                        <c:out value="${match.firstPlayerScore.tieBreak}"/>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${match.secondPlayerScore.point ne 'ADVANTAGE'}">
                            <c:out value="${match.firstPlayerScore.point}"/>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <td style="background-color: #93d900"><c:out value="${match.player2.name}"/></td>
            <td style="background-color: orange"><c:out value="${match.secondPlayerScore.set}"/></td>
            <td style="background-color: orange"><c:out value="${match.secondPlayerScore.game}"/></td>
            <td>
                <c:choose>
                    <c:when test="${match.stage eq 'TIE_BREAK'}">
                        <c:out value="${match.secondPlayerScore.tieBreak}"/>
                </c:when>
                <c:otherwise>
                    <c:if test="${match.firstPlayerScore.point ne 'ADVANTAGE'}">
                        <c:out value="${match.secondPlayerScore.point}"/>
                    </c:if>
                </c:otherwise>
                </c:choose>
            </td>
        </tr>
        </tbody>
    </table>
    <div class="buttons">
        <c:choose>
            <c:when test="${match.stage eq 'PLAYER_WON'}">
                <p style="color: #93d900; font-size: 20px;">
                    Игрок <span style="font-weight: bold;"><c:out value="${winner.name}"/></span> победил!<br/>
                    <a href="/new-match">Создать новый матч</a><br/>
                    <a href="/">На главную</a>

                </p>
            </c:when>
            <c:otherwise>
                <form method="POST" action="/match-score?uuid=${match.uuid}">
                    <input type="hidden" name="winner" value="firstWon">
                    <button type="submit">игрок 1 выиграл текущее очко</button>
                </form>
                <form method="POST" action="/match-score?uuid=${match.uuid}">
                    <input type="hidden" name="winner" value="secondWon">
                    <button type="submit">игрок 2 выиграл текущее очко</button>
                </form>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>