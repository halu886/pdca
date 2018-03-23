var _this = {};
$(function () {

    function init() {
        $('.sidebar .active').removeClass('active');
        $('.sidebar .nav-sidebar:nth-child(1) li:nth-child(3)').addClass('active');
        $('.my-datetimepicker').datetimepicker();
        $.get('tree', function (data) {
            if (data.status == true) {
                $('#taskTree').treeview({
                    data: data.data,
                    showTags: true,
                    onNodeSelected: function (e, node) {
                        _this.item = node;
                        renderTask(node.t);
                    }
                });
                var iconAdd = '<span class="glyphicon glyphicon-plus-sign task-add-icon"></span>'
                $('li.list-group-item.node-taskTree').append(iconAdd);
                $('li.node-taskTree span.task-add-icon').click(addTaskHandler);
            } else {
                console.error(data.message)
            }
        });
    }

    init()
});

//更新任务表单格式
function changeFormStatus(status) {
    switch (status) {
        case 'update':
            $('form.task input').attr('readonly', true)
            $('form.task input:not(.modify-unable)').attr('readonly', false);
            $('form.task button.update').attr('disabled', true);
            $('form.task button.submit').attr('disabled', false);
    }
    return true;
}

function renderTask(task) {
    $('input[name=taskName]').val(task.name);
    $('input[name=description]').val(task.description);
    $('input[name=taskId]').val(task.id);
    $('.task button.update').attr('disabled', false);
}

function overTask() {
    var itemSelected = $.extend(true, {}, _this.item);
    $.get('overTask/'+itemSelected.t.id,function (data) {
        if(data.status){
            window.location.reload();
        } else{
            alert(data.message);
        }
    })
}

function updateTask() {
    var formData = $('form.task').serializeArray();
    $.ajax(
        {
            type: "POST",
            url: 'update',
            data: formData,
            datatype: 'text',
            contentType: "application/x-www-form-urlencoded",
            success: function (data) {
                if (data.status != true) {
                    console.error(data.message);
                } else {
                    window.location.href = "index";
                }
            }
        }
    )
}

function renderAddForm(t) {
    $('form').addClass('my-none-display');
    if (t.taskType === 'T') {
        $('form.task-add-t').removeClass('my-none-display');
    } else {
        $('form.task-add-tChild').removeClass('my-none-display');
    }
}

addTaskHandler = function (event) {
    var nodeId = $(this).parent().attr("data-nodeid");
    var node = $('#taskTree').treeview('getNode', nodeId);
    _this.item = node;
    renderAddForm.call(this, node.t);
    event.stopPropagation();
}

function validTypeTask(task) {
    var typeTaskList = ['a', 'b', 'c', 'd'];
    var progressValid = {
        name: 'TypeTaskProgress',
        value: 100,
    };
    try {
        var itemSelected = $.extend(true, {}, _this.item);
        var preEndDate = new Date(itemSelected.t.startDate);
        preEndDate = preEndDate.valueOf();
        var progressTotal = 0;
        for (var index in typeTaskList) {
            var type = typeTaskList[index];
            progressTotal += parseInt(task[type + progressValid.name]);//to do
            var startDate = task[type + 'TypeStartDate'];
            var endDate = task[type + 'TypeEndDate'];
            if (Date.parse(preEndDate) >= Date.parse(startDate))
                return false;
            preEndDate = endDate;
        }
        endDate = (new Date(itemSelected.t.endDate)).valueOf();
        if (progressTotal !== 100 || (itemSelected.t.endDate != null && Date.parse(preEndDate) >= Date.parse(startDate))) {
            return false;
        }
        return true;
    } catch (e) {
        console.error(e.stack);
        return false;
    }
}

function validTask(task) {
    var selectItem = $.extend(true, {}, _this.item);
    if (Date.parse(task.startDate) > Date.parse(task.endDate) || selectItem.t.startDate > Date.parse(task.startDate) || selectItem.t.endDate < Date.parse(task.endDate)) {
        return false;
    }
    return true;
}

function addTask(button) {
    var task = $(button).parents('form').serializeObject();
    var itemSelected = $.extend(true, {}, _this.item);
    if (itemSelected.t.taskType === 'T') {
        if (!validTypeTask(task)) {
            alert('参数异常');
        }
        if (itemSelected.nodes instanceof Array && itemSelected.nodes.length != 0) {
            alert('该任务已不允许添加类型任务')
            return;
        }
        // var task = JSON.parse('{"pTypeTaskName":"1","pTypeTaskDescription":"1","pTypeTaskProgress":"20","pTypeStartDate":"2018-03-08 09:03:44","pTypeEndDate":"2018-03-09 09:03:44","dTypeTaskName":"2","dTypeTaskDescription":"2","dTypeTaskProgress":"30","dTypeStartDate":"2018-03-11 09:03:45","dTypeEndDate":"2018-03-12 09:03:45","cTypeTaskName":"3","cTypeTaskDescription":"3","cTypeTaskProgress":"30","cTypeStartDate":"2018-03-14 09:03:45","cTypeEndDate":"2018-03-15 09:03:45","aTypeTaskName":"4","aTypeTaskDescription":"4","aTypeTaskProgress":"20","aTypeStartDate":"2018-03-16 09:03:45","aTypeEndDate":"2018-03-17 09:03:45"}');
        task.pTaskId = itemSelected.t.id;
        $.ajax(
            {
                type: "POST",
                url: 'add/t',
                data: JSON.stringify(task),
                dataType: 'json',
                contentType: "application/json",
                success: function (data) {
                    if (data.status != true) {
                        console.error(data.message);
                    } else {
                        window.location.href = "index";
                    }
                }
            }
        )
    } else {
        if (!validTask(task)) {
            alert("任务校验失败");
            return;
        }
        task.pTaskId = itemSelected.t.id;
        $.ajax(
            {
                type: "POST",
                url: 'add/' + itemSelected.t.taskType,
                data: JSON.stringify(task),
                dataType: 'json',
                contentType: "application/json",
                success: function (data) {
                    if (data.status != true) {
                        console.error(data.message);
                    } else {
                        // window.location.href = "index";
                    }
                }
            }
        )

    }
}

//任务提交
function submitTask(button) {
    var form = $(button).parents('form');
    var object = form.serializeObject();
    $.ajax({
        type: "POST",
        url: 'update',
        data: JSON.stringify(object),
        dataType: 'json',
        contentType: "application/json",
        success:function (data) {
            if(data.status === true){
                changeFormStatus('updateEnd');
            }else {
                console.error(data.message);
                alert('提交失败');
            }
        }
    })
}

