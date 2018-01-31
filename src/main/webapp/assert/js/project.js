$(function () {
    $('.sidebar .active').removeClass('active');
    $('.sidebar .nav-sidebar:nth-child(1) li:nth-child(2)').addClass('active')

    parseProjectForm = function (projectId) {
        $.get("/project/findById", {projectId: projectId}, function (data) {
            $('input#projectId').val(data.projectID);
            $('input#projectName').val(data.name);
            $('input#createDate').val(data.createDate);
            $('button.update').removeAttr('disabled');
            $('button.manage').removeAttr('disabled');
        })
    }

    updateProject = function () {
        $('input[name=name]').removeAttr('readonly');
        $('input[name=createDate]').removeAttr('readonly');
        $('button.manage').removeAttr('disabled');
        $('button.update').attr('disabled', true);
        $('button.submit').attr('disabled', false);
    }

    showAddProject = function () {
        $('form .project-id-group').addClass("my-none-display");
        $('input[name=name]').removeAttr('readonly');
        $('input[name=CreateDate]').removeAttr('readonly');
        $('form .form-group .modify').addClass('my-none-display');
        $('form .form-group .insert').removeClass('my-none-display');
    }

    $('#CreateDate').datetimepicker();

    add = function () {
        var formData = $('form.project').serializeArray();
        $.ajax(
            {
                type:"POST",
                url:"add",
                data:formData,
                datatype:'text',
                contentType:"application/x-www-form-urlencoded",
                success:function (data) {
                    if(data.status){
                        console.error(data.message);
                    }
                }
            }
        )
    }

    submitProject = function () {
        var formData = $('form.project').serializeArray();
        $.ajax(
            {
                type:"POST",
                url: 'update',
                data: formData,
                datatype:'text',
                contentType:"application/x-www-form-urlencoded",
                success: function (data) {
                    if(data.status!='true'){
                        console.error(data.message);
                    }
                }
            }
        )
    }

});