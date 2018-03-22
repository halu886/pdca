$(function () {

})

function toRegister() {
    $('form.form-signin').addClass('my-none-display');
    $('form.form-register').removeClass('my-none-display');
}

function toLogin() {
    $('form.form-signin').attr('display', '');
    $('form.form-register').attr('display', 'none');
}

function register() {
    var user = $('form.form-register').serializeObject();
    $.post({
        type: "POST",
        url: '/user/register',
        data: JSON.stringify(user),
        dataType: 'json',
        contentType: "application/json",
        success:function (data) {
            if(data.status){
                toLogin();
            }else {
                alert('注册失败');
            }
        }

    })
}