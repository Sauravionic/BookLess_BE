var firstName = document.getElementById('first_name');
var firstName_value = 0;
var lastName = document.getElementById('last_name');
var lastName_value = 1;
var email = document.getElementById('email');
var email_value = 2;
var password = document.getElementById('password');
var password_value = 3;
var label = document.querySelectorAll("label");
var button = document.getElementById('trial_button');



// var info = document.getElementById('info_card');
// var input = document.querySelectorAll(".input_field");

// input.forEach(input => input.addEventListener('click',e => {
//     input.style.borderColor = "red";
// }))


function validateForm(field, value) {
    if(field.value == "") {
        field.classList.remove("success");
        field.classList.add("error");
        label[value].classList.remove("input_error");
        return false;
    }
    else {
        field.classList.add("success");
        field.classList.remove("error");
        label[value].classList.add("input_error");
        return true;
    }
}
function ValidateEmail(field, value) {
     var mailformat = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
     if(field.value.match(mailformat)) { 
        field.classList.add("success");
        field.classList.remove("error");
        label[value].classList.add("input_error");
        return true;
     }
    else {
        field.classList.remove("success");
        field.classList.add("error");
        label[value].classList.remove("input_error");
        return false;
     }
}

button.addEventListener('click',e => {
    validateForm(firstName,0);
    validateForm(lastName,1);
    ValidateEmail(email,2);
    validateForm(password,3);
    if(validateForm(firstName,0) && validateForm(lastName,1) && ValidateEmail(email,2) && validateForm(password,3)) {
        firstName.value = "";
        lastName.value = "";
        email.value = "";
        password.value = "";
    }
    else {
        e.preventDefault();
    }
})


