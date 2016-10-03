<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <sec:authorize access="isAuthenticated()">
        <meta http-equiv="refresh" content="0;URL=/sn.com/home" />
    </sec:authorize>
    <link type="text/css" rel="stylesheet" href="/resources/styles/style.css" />
    <link type="text/css" rel="stylesheet" href="/resources/styles/skin.css" />
    <link type="text/css" rel="stylesheet" href="/resources/styles/index/style.css" />
</head>
<body class="home">
<div id="wrap">
    <div id="header"> <img src="/resources/images/logo.png" />
        <form class="form_button">
            <input type="button reg_button" class="button" value="Registrate" onClick='location.href="reg"'>
        </form>
    </div>
    <div class="login" style="width: 600px;">
        <c:url value="/j_spring_security_check" var="loginUrl" />
        <form action="${loginUrl}" method="post">
            <h2 class="form-signin-heading">Please write your login and password</h2>
            <input type="text" class="form-control" name="j_username" placeholder="Login" required autofocus>
            <br>
            <input type="password" class="form-control" name="j_password" placeholder="Password" required>
            <br>
            <button class="button login_button" type="submit">Log in</button>
            <br>
        </form>

    </div>

    <div id="footer">
        <p class="copyright">Made by <b>Lisonn</b>. Not a commercial project. Only for presentation.</p>
        <p class="copyright">vladyslav.hanzha@gmail.com</p>
    </div>
</body>
</html>
