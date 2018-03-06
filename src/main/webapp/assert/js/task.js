$(function () {

    function init() {
        $('.sidebar .active').removeClass('active');
        $('.sidebar .nav-sidebar:nth-child(1) li:nth-child(3)').addClass('active');
        $('.my-datetimepicker').datetimepicker();
        $.get('tree', function (data) {
            if (data.status == true) {
                $('#taskTree').treeview({
                    data: data.data,
                    tags: ['aasd'],
                    onNodeSelected: function (e, node) {
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

function renderTask(task) {
    $('input[name=taskName]').val(task.name);
    $('input[name=description]').val(task.description);
    $('input[name=taskId]').val(task.id);
    $('input[name=taskName]').removeAttr('readonly');
    $('input[name=description]').removeAttr('readonly');
    $('.task-add-t button.update').attr('disabled', false);
    $('.task-add-t button.clear').attr('disabled',false)
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
                if (data.status != 'true') {
                    console.error(data.message);
                } else {
                    window.location.href = "task/index";
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

addTaskHandler =function (event) {
    var nodeId = $(this).parent().attr("data-nodeid");
    var node = $('#taskTree').treeview('getNode', nodeId);;
    renderAddForm.call(this,node.t);
    event.stopPropagation();
}

function validTypeTask(task) {
    var typeTaskList = ['a','b','c','d'];
    var progressValid ={
        name:'TypeTaskProgress',
        value: 100,
    };
    try {
        var nodeId = $(this).parent().attr("data-nodeid");
        var  itemSelected = $('#taskTree').treeview('getNode',nodeId);
        var preEndDate = itemSelected.t.startDate;
        var progressTotal = 0;
        for (var type in typeTaskList){
            progressTotal += parseInt(task[type+progressValid.name]);//to do
            var startDate = task[type+'TypeStartDate'];
            var endDate = task[type + 'TypeEndDate'];
            if(preEndDate>= startDate)
                return false;
            preEndDate = endDate;
        }
        if(progressTotal !== 100||(itemSelected.t.endDate !=null && preEndDate>=itemSelected.t.endDate)){
            return false;
        }
        return true;
    }catch (e){
        console.error(e.stack);
        return false;
    }
}

function addTask() {
    var taskTType = $('.task-add-t').serializeObject();
    if(!validTypeTask.call(this,taskTType)){
        alert('参数异常');
    }
    $.ajax(
        {
            type: "POST",
            url: 'add/t',
            data: taskTType,
            datatype: 'text',
            contentType: "application/x-www-form-urlencoded",
            success: function (data) {
                if (data.status != 'true') {
                    console.error(data.message);
                } else {
                    window.location.href = "task/index";
                }
            }
        }
    )
}

function submitTask() {
}