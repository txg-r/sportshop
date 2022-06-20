$("#login").click(() => {
    let params = new URLSearchParams();
    let user_name = $("#user_name").val();
    let user_pwd = $("#user_pwd").val();
    axios.post("login",`action=login&user_name=${user_name}&user_pwd=${user_pwd}`).then((response) => {
        if (response.data.loginFlag==='true'){
            location.reload()
        }else {
            $("#login_failure").text("账号或密码错误");
        }
    },()=>{
        alert("登陆失败");
    })
})

$("#register").click(()=>{
    location.href = "login?action=toRegister"
})