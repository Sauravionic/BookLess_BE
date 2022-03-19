$(document).ready(function() {
    $("#btn").click(function() {
        // console.log("Hello");
        // console.log($("#urlinput").val());
        $.ajax({
            type : 'POST',
            url : 'http://localhost:8080/login',
            data : JSON.stringify({
                "email" : $("#email").val(),
                "password" : $("#password").val()
            }),
            contentType : "application/json; charset=utf-8",
            success : function(data) {
                alert("Welcome")
                window.location.replace("http://localhost:8080/")
            },
            error : function (xhr, status, error) {
                if(xhr.status == 400) {
                    $("#error_span").html("NO USER EXISTS")
                    setTimeout($("#error_span").html("NO USER EXISTS.<br>TRY SIGNUP"),2000)
                }
                else if(xhr.status == 409) {
                    $("#error_span").html("USER NOT VERIFIED.<br>CLICK ON LINK TO RESEND VERIFICATION EMAIL");

                }
            }
        });
    });
});
