$("#togglePassword").on("click", function () {
    let passwordField = $("#pw");
    let type = passwordField.attr("type") === "password" ? "text" : "password";
    passwordField.attr("type", type);
});

//------
let passwordField = $("#pw"); //pw인풋창
let type = passwordField.attr("type"); //pw인풋창 타입 설정

if (type == "password") {
    passwordField.attr("type", "text");  //타입 password면 text로 변경
} else {
    passwordField.attr("type", "password"); //text면 password로 변경
}

// let type이게 인풋창의 type 값을 가져오는거고
// 그 타입이 패스워드랑 텍스트일때에 따라 변환해준다고 생각하면 될거같아