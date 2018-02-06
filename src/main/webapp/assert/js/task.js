$(function () {

    function init() {
        $('.sidebar .active').removeClass('active');
        $('.sidebar .nav-sidebar:nth-child(1) li:nth-child(3)').addClass('active');
        $.get('tree', function (data) {
            if (data.status == true) {
                $('#taskTree').treeview({
                    data: data.data,
                    onNodeSelected:function (e,node) {
                        renderTask(node.t);
                    }
                });
                // $('#taskTree li').on('click',updateTask);
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

function submitTask() {

}