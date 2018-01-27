$(function () {

    var getTree = function(){
        $.get("/project/list",function (data) {
            data = JSON.parse(data)
            $('#tree').treeview({data:data});
        })
    }
    getTree()

});