<%--
  Created by IntelliJ IDEA.
  User: yangy
  Date: 2017/4/9
  Time: 19:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="css/default.css">
    <link rel="stylesheet" type="text/css" href="css/login.css">
</head>

<body>
<div class="container">
    <div id="particles-js">
        <div class="wrapper">
            <div class="containerchild">
                <h1>登录</h1>
                <form class="form" method="GET" id="login_form"action="login.php">
                    <input name="userName" placeholder="userName" type="text" placeholder="Username">
                    <input name="password" type="password" placeholder="password">
                    <input type="submit" value="登录" >
                </form>
                </form>
            </div>
            <ul class="bg-bubbles">
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
            </ul>
        </div>
    </div>
</div>
<script src="js/particles.js"></script>
<script src="js/app.js"></script>
</body>

</html>
