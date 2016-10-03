<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>${user.name} ${user.surname}</title>
    <meta charset="utf-8">
    <link type="text/css" rel="stylesheet" href="/resources/styles/style.css" />
    <link type="text/css" rel="stylesheet" href="/resources/styles/skin.css" />
    <link type="text/css" rel="stylesheet" href="/resources/styles/home/style.css" />
</head>
<body class="home">
<div id="wrap">
    <div id="header"> <img src="/resources/images/logo.png" />
        <div id="nav">
            <ul class="menu">
                <li class="current_page_item"><a href="home">Home</a></li>
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
    <div class="form">
        <form enctype="multipart/form-data" method="post">
            <div class="fileform">
                <div id="fileformlabel"></div>
                <div class="selectbutton">Обзор</div>
                <input type="file" name="photo" id="photo" onchange="getName(this.value);" />
            </div>
                <input type="submit" class="button" value="Change photo">
            </form>
    </div>
    <div class="nameblock">
        <p class="name" id="name">${user.name} ${user.surname}</p>
        <hr>
        <p class="birth" id="birth"><b>Birthday:</b> ${user.birthDay} ${user.birthMonth} ${user.birthYear}</p>
        <p class="city" id="city"><b>City:</b> ${user.city}</p>
    </div>
    <div class="infoblock" id="infoblock">
        <p class="info" id="info">${user.info}</p>
    </div>
    </div>
    <form>
        <input type="button" class="button change_button" value="Change" onClick="location.href='update'">
    </form>
    <div id="footer">
        <p class="copyright">Made by <b>Lisonn</b>. Not a commercial project. Only for presentation.</p>
        <p class="copyright">vladyslav.hanzha@gmail.com</p>
    </div>
    <!--end footer-->
</div>
<!--end wrap-->
<script>
    function getName (str){
        if (str.lastIndexOf('\\')){
            var i = str.lastIndexOf('\\')+1;
        }
        else{
            var i = str.lastIndexOf('/')+1;
        }
        var filename = str.slice(i);
        var uploaded = document.getElementById("fileformlabel");
        uploaded.innerHTML = filename;
    }

</script>
</body>
