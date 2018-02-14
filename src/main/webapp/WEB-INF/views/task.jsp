<%--
  Created by IntelliJ IDEA.
  User: xdz
  Date: 2018/2/2
  Time: 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CH">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">

    <title>PDCA</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="../../assert/css/bootstrap-treeview.css">
    <link href="../../assert/css/starter-template.css" rel="stylesheet">
    <link rel="stylesheet" href="../../assert/css/common.css">
</head>
<body>
<%@include file="head.html" %>
<div class="container-fluid no-padding-col">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <%@include file="toolbar.html" %>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 no-left-padding-col">
            <div class="row">
                <div class="col-sm-3 col-md-3 no-left-padding-col">
                    <div id="taskTree"></div>
                </div>
                <div class="col-sm-9 col-md-9 no-left-padding-col">
                    <form class="form-horizontal task">
                        <div class="form-group">
                            <label for="TaskID" class="col-sm-2  control-lable">taskId</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="TaskID" name="taskId" readonly>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="TaskName" class=" col-sm-2 control-lable">taskName</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="TaskName" name="taskName" readonly>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="Description" class="col-sm-2  control-lable">Description</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="Description" name="description" readonly>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10 modify">
                                <button type="button" class="update btn btn-default" onclick="updateTask()" disabled>
                                    修改
                                </button>
                                <button type="button" class="submit btn btn-default" onclick="submitTask()" disabled>
                                    保存
                                </button>
                            </div>
                            <div class="col-sm-offset-2 col-sm-10 insert my-none-display">
                                <button type="button" class="update btn btn-default" onclick="add()">添加</button>
                            </div>
                        </div>
                    </form>
                    <form class="form-horizontal task-add-t my-none-display" >
                        <div class="form-group">
                            <label for="PTaskID" class="col-sm-2 constrol-lable">pTaskId</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="PTaskID" name="pTaskId" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="PTypeTaskName" class="col-sm-2 constrol-lable">pTypeTaskName</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="PTypeTaskName" name="pTypeTaskName"
                                       >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="PTypeTaskDescription" class="col-sm-2 constrol-lable">pTypeTaskName</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="PTypeTaskDescription" name="pTypeTaskDescription"
                                       >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="PTypeTaskProgress" class="col-sm-2 constrol-lable">pTypeTaskProgress</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="PTypeTaskProgress" name="pTypeTaskProgress"
                                       >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="DTypeTaskName" class="col-sm-2 constrol-lable">dTypeTaskName</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="DTypeTaskName" name="dTypeTaskName"
                                       >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="DTypeTaskDescription" class="col-sm-2 constrol-lable">dTypeTaskName</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="DTypeTaskDescription" name="dTypeTaskName"
                                       >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="DTypeTaskProgress" class="col-sm-2 constrol-lable">dTypeTaskProgress</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="DTypeTaskProgress" name="dTypeTaskProgress"
                                       >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="CTypeTaskName" class="col-sm-2 constrol-lable">cTypeTaskName</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="CTypeTaskName" name="cTypeTaskName"
                                       >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="CTypeTaskDescription" class="col-sm-2 constrol-lable">cTypeTaskName</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="CTypeTaskDescription" name="cTypeTaskName"
                                       >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="CTypeTaskProgress" class="col-sm-2 constrol-lable">cTypeTaskProgress</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="CTypeTaskProgress" name="cTypeTaskProgress"
                                       >
                            </div>
                        </div>
                        <%--<div class="form-group">--%>
                            <%--<label for="CTypeTaskProgress" class="col-sm-2 constrol-lable">cTypeTaskProgress</label>--%>
                            <%--<div class="col-sm-10">--%>
                                <%--<input type="text" class="form-control" id="CTypeTaskProgress" name="cTypeTaskProgress"--%>
                                       <%-->--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10 modify">
                                <button type="button" class="update btn btn-default" onclick="submitTaskAdd()" disabled>
                                    提交
                                </button>
                                <button type="button" class="update btn btn-default" onclick="updateTask()" disabled>
                                    清空
                                </button>
                            </div>
                            <div class="col-sm-offset-2 col-sm-10 insert my-none-display">
                                <button type="button" class="update btn btn-default" onclick="add()">添加</button>
                            </div>
                        </div>
                    </form>
                    <form class="form-horizontal task-add-tChild my-none-display">
                        <div class="form-group">
                            <label for="PTaskName" class=" col-sm-2 control-lable">taskName</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="PTaskName" name="pTaskName" readonly>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="TaskName" class=" col-sm-2 control-lable">taskName</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="TaskName" name="taskName" readonly>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="Description" class="col-sm-2  control-lable">Description</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="Description" name="description" readonly>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10 insert my-none-display">
                                <button type="button" class="update btn btn-default" onclick="add()">添加</button>
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
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="../../assert/js/bootstrap-treeview.js"></script>
<script src="../../assert/js/task.js"></script>
<script src="../../assert/js/bootstrap-datetimepicker.min.js"></script>
</html>
