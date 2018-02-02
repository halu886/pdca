$(function () {

   function init(){
       $('.sidebar .active').removeClass('active');
       $('.sidebar .nav-sidebar:nth-child(1) li:nth-child(3)').addClass('active');
       $.get('tree',function (data) {
           $('#taskTree').treeview({data:data});
       });
   }

   init()
});