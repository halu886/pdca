<%@ page import="com.jxufe.halu.service.IProjectService" %>
<%@ page import="com.jxufe.halu.service.ProjectServiceImpl" %>
<%@ page import="com.jxufe.halu.service.UserServiceImpl" %>
<%@ page import="com.jxufe.halu.service.IUserService" %><%--
  Created by IntelliJ IDEA.
  User: xdz
  Date: 2018/1/23
  Time: 20:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! private IUserService service = new UserServiceImpl();%>
<html lang="zh-CH">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">

    <title>PDCA</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="../../assert/css/bootstrap-treeview.css">
    <link href="../../assert/css/starter-template.css" rel="stylesheet">
    <link href="../../assert/css/index.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">PDCA</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="#about">About</a></li>
                <li><a href="#contant">contact</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li class="active"><a href="#">Overview<span class="sr-only">(current)</span></a></li>
                <li><a href="#">Reports</a></li>
                <li><a href="#">Analytics</a></li>
                <li><a href="#">Export</a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <li><a href="">Nav item</a></li>
                <li><a href="">Nav item again</a></li>
                <li><a href="">One more nav</a></li>
                <li><a href="">Another nav item</a></li>
                <li><a href="">More navigation</a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <li><a href="">Nav item again</a></li>
                <li><a href="">One more nav</a></li>
                <li><a href="">Another nav item</a></li>
            </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="container">
                <div class="row">
                    <div class="col-sm-3 col-md-2 ">
                        <h2><%=service.getProjectsOfUser((String)request.getAttribute("userID")).get(0).getName()%><h2/>
                        <div class="tree">
                        </div>
                    </div>
                    <div id="dashboard" class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2">
                        <div  class="starter-template ">
                            <h1>Dashboard</h1>
                            <p class="lead">对总结检查的结果进行处理，成功的经验加以肯定并适当推广、标准化；失败的教训加以总结，以免重现，未解决的问题放到下一个PDCA循环。PDCA循环的四个过程不是运行一次就完结，而是周而复始地进行。一个循环结束了，解决了一部分问题，可能还有问题没有解决，或者又出现了新的问题，再进行下一个PDCA循环，依此类推。</p>
                        </div>
                    </div>
                    </div>
                </div>
            </div>

        </div>
</div>
</body>
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="../../assert/js/bootstrap-treeview.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<%--<script src="assert/js/index.js"></script>--%>
</html>
