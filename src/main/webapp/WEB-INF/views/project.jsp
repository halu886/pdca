<%@ page import="com.jxufe.halu.model.Project" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: xdz
  Date: 2018/1/30
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<%@include file="head.html"%>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2">
            <%@include file="toolbar.html"%>
        </div>
        <div class="col-sm-9 col-md-10">
            <div class="container">
                <div class="col-sm-3 col-md-2 ">
                    <ul class="list-group">
                        <% List<Project> projects = (List<Project>)request.getAttribute("projects");%>
                        <% for (Project project:
                                projects) {%>
                        <li class="list-group-item" onclick="parseProjectForm(<%=project.getProjectID()%>)"><%=project.getName()%></li>
                        <%}%>
                    </ul>
                </div>
                <div class="col-sm-9 col-md-10">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label for="projectId" class="col-sm-2 control-label">projectId</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="projectId" placeholder="projectId" disabled>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="projectName" class="col-sm-2 control-label">projectName</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="projectName" placeholder="projectName" disabled>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="createDate" class="col-sm-2 control-label">projectName</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="createDate" placeholder="createDate" disabled>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="button" class="btn btn-default" >Sign in</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="../../assert/js/bootstrap-treeview.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script src="assert/js/index.js"></script>
</html>
