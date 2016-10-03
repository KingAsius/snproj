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
    <link type="text/css" rel="stylesheet" href="/resources/styles/dialogues/style.css" />
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
<h1>Dialogues</h1>
<c:forEach items="${usersAndDialogues}" var="item">
    <br>
    <div class="userblock" onclick="location.href='messages?id=${item.value.id}'">
        <img class="img" src=${item.value.photopath} width=60 height=60>
        <p class="username name">${item.value.name} ${item.value.surname}</p>
    </div>
</c:forEach>
    <div id="footer">
        <p class="copyright">Made by <b>Lisonn</b>. Not a commercial project. Only for presentation.</p>
        <p class="copyright">vladyslav.hanzha@gmail.com</p>
    </div>
</body>
</html>
