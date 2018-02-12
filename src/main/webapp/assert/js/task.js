$(function () {

    function init() {
        $('.sidebar .active').removeClass('active');
        $('.sidebar .nav-sidebar:nth-child(1) li:nth-child(3)').addClass('active');
        $.get('tree', function (data) {
            if (data.status == true) {
                $('#taskTree').treeview({
                    data: data.data,
                    tags: ['aasd'],
                    onNodeSelected: function (e, node) {
                        debugger
                        renderTask(node.t);
                    }
                });
                var iconAdd = '<span class="glyphicon glyphicon-plus-sign task-add-icon"></span>'
                $('li.list-group-item.node-taskTree').append(iconAdd);
                $('li.node-taskTree span.task-add-icon').click(addTask);
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
    $('button.update').attr('disabled', false);
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

addTask =function (event) {
    var nodeId = $(this).parent().attr("data-nodeid");
    var node = $('#taskTree').treeview('getNode', nodeId);;
    renderAddForm(node.t);
    event.stopPropagation();
}

function submitTask() {

}