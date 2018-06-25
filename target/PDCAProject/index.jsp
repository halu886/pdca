<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="登入页面">
    <meta name="author" content="halu">

    <title>PDCA Login</title>

    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="/assert/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <link href="./assert/css/common.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/assert/css/signin.css" rel="stylesheet">

    <![endif]-->
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
</head>

<body>

<div class="container">

    <form class="form-signin" method="post" action="/login">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label  class="sr-only">User ID</label>
        <input type="text" name="username" class="form-control" placeholder="User Name" required autofocus>
        <label  class="sr-only">Password</label>
        <input type="password"   name="password" class="form-control" placeholder="Password" required>
        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> Remember me
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block login" type="submit">登录</button>
        <button class="btn btn-lg btn-primary btn-block to-register" type="button" onclick="toRegister()">注册</button>
    </form>
    <form class="form-register my-none-display" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label  class="sr-only">User ID</label>
        <input type="text" name="username" class="form-control" placeholder="User Name" required autofocus>
        <label  class="sr-only">Password</label>
        <input type="password"   name="password" class="form-control" placeholder="Password" required>
        <button class="btn btn-lg btn-primary btn-block register" type="button" onclick="register() " >注册</button>
    </form>


</div>
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="assert/js/common.js"></script>
<script src="assert/js/login.js"></script>

</body>
</html>
