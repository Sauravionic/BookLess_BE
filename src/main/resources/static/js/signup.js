$(document).ready(function() {
    jQuery.validator.addMethod("pwcheck", function(value) {
        return /^[A-Za-z0-9\d=!\-@._*]*$/.test(value) // consists of only these
            && /[a-z]/.test(value) // has a lowercase letter
            && /[A-Z]/.test(value) // has an uppercase letter
            && /\d/.test(value) // has a digit
    });
    $("#frm").validate({
        // Specify rules
        rules: {
            firstname: "required",
            lastname: "required",
            email: {
                required: true,
                // Specify that email should be validated
                // by the built-in "email" rule
                email: true
            },
            password: {
                required: true,
                minlength: 7,
                pwcheck:true
            }
        },
        // Specify validation error messages
        messages: {
            firstname: "Please enter your firstname",
            lastname: "Please enter your lastname",
            password: {
                required: "Please provide a password",
                minlength: "Your password must be at least 7 characters long",
                pwcheck: "Password must contain a lowercase, an uppercase and a digit"
            },
            email: "Please enter a valid email address"
        }
    });
    $("#btn").click(function() {
        $("#error_span").html("");
        if($("#frm").valid()) {
            $("#btn").attr("disabled", true);
            $("#containerA").removeClass("hide");
            // console.log("Hello");
            // console.log($("#urlinput").val());
            $.ajax({
                type: 'POST',
                url: 'http://localhost:8080/register',
                data: JSON.stringify({
                    "firstName": $("#firstname").val(),
                    "lastName": $("#lastname").val(),
                    "email": $("#email").val(),
                    "password": $("#password").val()
                }),
                contentType: "application/json; charset=utf-8",
                success: function (data) {
                    $("#btn").attr("disabled", false);
                    $("#containerA").addClass("hide");
                    $("#error_span").html("Verification Link Sent");
                    // window.location.replace("http://localhost:8080/signin")
                },
                error: function (xhr, status, error) {
                    $("#btn").attr("disabled", false);
                    $("#containerA").addClass("hide");
                    if (xhr.status == 400) {
                        $("#error_span").html("User Already Exists")
                    } else if (xhr.status == 409) {
                        $("#error_span").html("USER NOT VERIFIED.<br>CHECK YOUR MAIL FOR VERIFICATION LINK");
                    }
                }
            });
        }
    });
});
