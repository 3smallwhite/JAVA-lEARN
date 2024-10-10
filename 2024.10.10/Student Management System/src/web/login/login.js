
$("#enter").click(function() {
    var user = {"user": $("#user").val(), "password": $("#password").val()};
    if (user.user == "" || user.password == "") {
        console.log("请输入用户名、密码！");
        return;
    }
    $.ajax({
        url: "/Servlet/login",
        type: "GET",
        data: {"user": JSON.stringify(user)},
        dataType: "json",
        success: function (result) {
            console.log(result)
        },
        error: function () {
            console.log("error")
        }
    });
});