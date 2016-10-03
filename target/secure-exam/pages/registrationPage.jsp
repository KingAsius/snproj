<%--
  Created by IntelliJ IDEA.
  User: Vladislav
  Date: 9/11/2016
  Time: 8:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link type="text/css" rel="stylesheet" href="/resources/styles/style.css" />
    <link type="text/css" rel="stylesheet" href="/resources/styles/skin.css" />
    <link type="text/css" rel="stylesheet" href="/resources/styles/reg/style.css" />
</head>
<body class="home">
<div id="wrap">
    <div id="header"> <img src="/resources/images/logo.png" />
        <div id="nav">
        </div>
        <!--end nav-->
    </div>
    <!--end header-->
    </div>
<div class="main">
<h1>Registration Page</h1>
<form method="post">
    <input type="text" name="login" id="login" placeholder="Login" required minlength="5" maxlength="20">
    <br>
    <input type="password" name="password" id="password" placeholder="Password" required minlength="5" maxlength="20">
    <br>
    <input type="text" name="name" id="name" placeholder="Name" required minlength="3" maxlength="20">
    <br>
    <input type="text" name="surname" id="surname" placeholder="Surname" required minlength="3" maxlength="20">
    <br>
    <select size="1" name="birthday" id="birthday" required>
        <option value="1">1</option> <option value="2">2</option> <option value="3">3</option> <option value="4">4</option> <option value="5">5</option> <option value="6">6</option>
        <option value="7">7</option> <option value="8">8</option> <option value="9">9</option> <option value="10">10</option> <option value="11">11</option> <option value="12">12</option>
        <option value="13">13</option> <option value="14">14</option> <option value="15">15</option> <option value="16">16</option> <option value="17">17</option> <option value="18">18</option>
        <option value="19">19</option> <option value="20">20</option> <option value="21">21</option> <option value="22">22</option> <option value="23">23</option> <option value="24">24</option>
        <option value="25">25</option> <option value="26">26</option> <option value="27">27</option> <option value="28">28</option> <option value="29">29</option> <option value="30">30</option>
        <option value="31">31</option>
        </select>
    <br>
    <select size="1" name="birthmonth" required>
        <option value="January">January</option> <option value="February">February</option> <option value="March">March</option> <option value="April">April</option>
        <option value="May">May</option> <option value="June">June</option> <option value="July">July</option> <option value="August">August</option>
        <option value="September">September</option> <option value="October">October</option> <option value="November">November</option> <option value="December">December</option>
        </select>
    <br>
    <input type="number" name="birthyear" id="birthyear" placeholder="Year" required min="1950" max="2020">
    <br>
    <input type="text" name="city" id="city" placeholder="City" required minlength="3" maxlength="20">
    <br>
    <textarea type="text" placeholder="About you" name="info" class="infoblock" id="info" maxlength="1000" cols="100"></textarea>
    <br>
    <input class="button" type="submit" value="Registrate">
</form>
</div>
</body>
</html>
