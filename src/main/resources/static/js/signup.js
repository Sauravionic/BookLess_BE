$(document).ready(function() {
    $("#btn").click(function() {
        // console.log("Hello");
        // console.log($("#urlinput").val());
        $.ajax({
            type : 'POST',
            url : 'http://localhost:8080/register',
            data : JSON.stringify({
                "firstName" : $("#firstname").val(),
                "lastName" : $("#lastname").val(),
                "email" : $("#email").val(),
                "password" : $("#password").val()
            }),
            contentType : "application/json; charset=utf-8",
            success : function(data) {
                alert("Verification Link Sent")
                window.location.replace("http://localhost:8080/signin")
            },
            error : function (xhr, status, error) {
                if(xhr.status == 400) {
                    $("#error_span").html("User Already Exists")
                }
            }
        });
    });
});


















//
//
// const firstName = document.getElementById("firstname").value;
// const lastName = document.getElementById("lastname").value;
// const email = document.getElementById("email").value;
// const password = document.getElementById("password").value;
//
// function frmSubmit() {
//
//     let body = {
//         "firstName" : firstName,
//         "lastName" : lastName,
//         "email" : email,
//         "password" : password
//     }
//     console.log({firstName})
//
//     fetch("http://localhost:8080/signup", {
//         method: "POST",
//         body: JSON.stringify(body),
//         headers: {
//             "Content-type": "application/json"
//         }
//     })
//     .then(function (res) {
//         console.log(res)
//     })
//     .then(function (res) {
//         console.log(res)
//     })
//     .catch(function (err) {
//         console.log(err)
//     })
// }
