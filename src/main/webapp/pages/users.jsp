<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Users</title>
    <meta charset="utf-8">
    <link type="text/css" rel="stylesheet" href="/resources/styles/style.css" />
    <link type="text/css" rel="stylesheet" href="/resources/styles/skin.css" />
    <link type="text/css" rel="stylesheet" href="/resources/styles/mystyle.css" />
    <link type="text/css" rel="stylesheet" href="/resources/styles/users styles/style.css" />

</head>
<body class="home">
<div id="wrap">
    <div id="header"> <img src="/resources/images/logo.png" />
        <div id="nav">
            <ul class="menu">
                <li><a href="home">Home</a></li>
                <li class="current_page_item"><a href="users">Users</a></li>
                <li><a href="messages">Messages</a></li>
                <li><a href="/logout">Exit</a></li>
            </ul>
        </div>
        <!--end nav-->
    </div>
    <!--end header-->
    <c:forEach items="${users}" var="item">
        <div class="userblock">
            <img class="img" src=${item.photopath} width=60 height=60>
            <p class="username name"><a href="user?id=${item.id}">${item.name} ${item.surname}</a></p>
            <p class="writemessage"><a href="messages?id=${item.id}">Write a message</a></p>
        </div>
    </c:forEach>
    <div id="footer">
        <p class="copyright">Made by <b>Lisonn</b>. Not a commercial project. Only for presentation.</p>
        <p class="copyright">vladyslav.hanzha@gmail.com</p>
    </div>
    <!--end footer-->
</div>
<!--end wrap-->
</body>