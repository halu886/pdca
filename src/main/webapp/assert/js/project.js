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
        $('input[name=projectName]').removeAttr('readonly');
        $('button.manage').removeAttr('disabled');
        $('button.update').attr('disabled', true);
        $('button.submit').attr('disabled', false);
    }

    var serialiceObject =function(data){
        var obj=new Object();
        $.each(data,function(index,param){
            if(!(param.name in obj)){
                obj[param.name]=param.value;
            }
        });
        return obj;
    };

    submitProject = function () {
        // var formData = serialiceObject($('form.project').serializeArray());
        var formData = $('form.project').serializeArray();
        $.ajax(
            {
                type:"POST",
                url: 'update',
                data: formData,
                datatype:'text',
                contentType:"application/x-www-form-urlencoded",
                success: function (data) {
                    debugger
                }
            }
        )
    }

});