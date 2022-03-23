$(document).ready(function() {
    $("#frm").validate({
        // Specify rules
        rules: {
            email: {
                required: true,
                // Specify that email should be validated
                // by the built-in "email" rule
                email: true
            },
            password: {
                required: true,
            }
        },
        // Specify validation error messages
        messages: {
            password: {
                required: "Please provide a password",
            },
            email: "Please enter a valid email address"
        }
    });
    $("#btn").click(function(event) {
        event.preventDefault();
        $("#error_span").html("");
        if($("#frm").valid()) {
            $("#btn").attr("disabled", true);
            $("#containerA").removeClass("hide");
            $("#frm").submit();

            // $.ajax({
            //     type: 'POST',
            //     url: 'http://localhost:8080/signin',
            //     data: JSON.stringify({
            //         "email": $("#email").val(),
            //         "password": $("#password").val()
            //     }),
            //     contentType: "application/json; charset=utf-8",
            //     success: function (data) {
            //         $("#btn").attr("disabled", false);
            //         $("#error_span").css("color","green");
            //         $("#error_span").html("Welcome");
            //         $("#frm").submit();
            //         // alert("Welcome")
            //         // setTimeout(window.location.replace("http://localhost:8080/home"),1500);
            //     },
            //     error: function (xhr, status, error) {
            //         $("#btn").attr("disabled", false);
            //         $("#error_span").css("color","red");
            //         if (xhr.status == 400) {
            //             $("#error_span").html("NO USER EXISTS")
            //             setTimeout($("#error_span").html("NO USER EXISTS.<br>TRY SIGNUP"), 2000)
            //         } else if (xhr.status == 409) {
            //             $("#error_span").html("USER NOT VERIFIED.<br>CHECK YOUR MAIL FOR VERIFICATION LINK");
            //         }
            //         else {
            //             $("#error_span").html("Email or Password not correct");
            //         }
            //     }
            // });
        }
    });
    $("#password").keypress(function(event) {
        if (event.keyCode === 13) {
            $("#btn").click();
        }
    });
});
