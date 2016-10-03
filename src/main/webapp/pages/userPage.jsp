<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>${user.name} ${user.surname}</title>
    <meta charset="utf-8">
    <link type="text/css" rel="stylesheet" href="/resources/styles/style.css" />
    <link type="text/css" rel="stylesheet" href="/resources/styles/skin.css" />
    <link type="text/css" rel="stylesheet" href="/resources/styles/mystyle.css" />
</head>
<body class="home">
<div id="wrap">
    <div id="header"> <img src="/resources/images/logo.png" />
        <div id="nav">
            <ul class="menu">
                <li><a href="home">Home</a></li>
                <li><a href="users">Users</a></li>
                <li><a href="messages">Messages</a></li>
                <li><a href="/logout">Exit</a></li>
            </ul>
        </div>
        <!--end nav-->
    </div>
    <!--end header-->

    <div class="main">
        <p class="avblock"><img class="avimage" src=${user.photopath} height=300 width="280"></p>
        </div>
        <div class="nameblock">
            <p class="name">${user.name} ${user.surname}</p>
            <hr>
            <p class="birth"><b>Birthday:</b> ${user.birthDay} ${user.birthMonth} ${user.birthYear}</p>
            <p class="city"><b>City:</b> ${user.city}</p>
        </div>
        <div class="infoblock">
            <p class="info">${user.info}</p>
        </div>
        <form class="form_button">
            <input type="button" class="message" value="Write message" onClick='location.href="messages?id=${user.id}"'>
        </form>
    </div>
    <div id="footer">
        <p class="copyright">Made by <b>Lisonn</b>. Not a commercial project. Only for presentation.</p>
        <p class="copyright">vladyslav.hanzha@gmail.com</p>
    </div>
    <!--end footer-->
</div>
<!--end wrap-->
</body>
