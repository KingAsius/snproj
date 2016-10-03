<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Dialogues</title>
    <sec:authorize access="!isAuthenticated()">
        <meta http-equiv="refresh" content="0;URL=/sn.com/" />
    </sec:authorize>
    <link type="text/css" rel="stylesheet" href="/resources/styles/style.css" />
    <link type="text/css" rel="stylesheet" href="/resources/styles/skin.css" />
    <link type="text/css" rel="stylesheet" href="/resources/styles/messages/style.css" />
</head>
<body class="home">
<div id="wrap">
    <div id="header"> <img src="/resources/images/logo.png" />
        <div id="nav">
            <ul class="menu">
                <li><a href="home">Home</a></li>
                <li><a href="users">Users</a></li>
                <li class="current_page_item"><a href="messages">Messages</a></li>
                <li><a href="/logout">Exit</a></li>
            </ul>
        </div>
        <!--end nav-->
    </div>
    </div>
<h3>${otherUserName}</h3>
<form>
    <input type="button" class="button" value="Back to dialogues" onClick='location.href="messages"'>
</form>
<form method="post">
<input type="text" id="amount" name="amount" value=${amount} hidden>
    <input type="submit" value="More messages">
    </form>
<div class="messages_block">
<c:forEach items="${messages}" var="item">
    <div class="message_block"><span class="message">${item}</span></div>
    <br>
</c:forEach>
</div>
<form method="post" class="post">
    <textarea class="textarea" name="textarea" id="textarea" maxlength="800" cols="100"></textarea>
    <br>
    <input type="submit" value="WRITE" class="button">
</form>
<div id="footer">
    <p class="copyright">Made by <b>Lisonn</b>. Not a commercial project. Only for presentation.</p>
    <p class="copyright">vladyslav.hanzha@gmail.com</p>
</div>
</body>
</html>
