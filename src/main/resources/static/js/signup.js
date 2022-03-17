$(document).ready(function() {
    $("#btn").click(function() {
        // console.log("Hello");
        // console.log($("#urlinput").val());
        $.ajax({
            type : 'POST',
            url : 'http://localhost:8080/signup',
            data : JSON.stringify({
                "firstName" : $("#firstname").val(),
                "lastName" : $("#lastname").val(),
                "email" : $("#email").val(),
                "password" : $("#password").val()
            }),
            contentType : "application/json; charset=utf-8",
            success : function(data) {
                window.location.replace("http://localhost:8080/")
                // $("#fullURL").html($("#urlinput").val());
                // $("#shorturltext").html(data.short_url);
                // $("#shorturltext").attr("href", data.short_url);
                //
                // $("#lab").css({display:"none"});
                // $("#urlinput").css({'background-image':"none"});
                // if($(".shortend").is(':hidden')) {
                //     $(".shortend").css({display: "flex"});
                //     console.log("work");
                // }
                // if($("#copy").html() === "Copied") {
                //     $("#copy").html("Copy");
                // }
            },
            error : function () {
                $("#sign").html("error")
                // $("#lab").css({display:"block"});
                // $("#urlinput").css({'background-image':"url(images/icon-error.svg)"});
                // if(($(".shortend").css('display') !== 'none')) {
                //     $(".shortend").css({display: "none"});
                // }
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
