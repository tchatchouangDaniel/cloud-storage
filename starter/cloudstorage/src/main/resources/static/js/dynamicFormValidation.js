import { pswdCheck, nameCheck } from "./helpers/controller.js";

// This object will keep all the form field DOM references
const signupForm = {
     firstname : document.querySelector("#inputFirstName"),
     firstnameError : document.querySelector("#inputFirstnameError"),
     lastname : document.querySelector("#inputLastName"),
     lastnameError : document.querySelector("#inputLastnameError"),
     username : document.querySelector("#inputUsername"),
     usernameError : document.querySelector("#inputUsernameError"),
     password : document.querySelector("#inputPassword"),
     passwordError : document.querySelector("#inputPasswordError")
}


// We hide all the error elements here
Object.keys(signupForm).forEach(input => {
    if(input.includes("Error") && signupForm[input]) signupForm[input].style.display = "none";
})

// for each field we check if it exist before adding event listener
// this is for reusability because we are going to use it for all form validations
signupForm.firstname && signupForm.firstname.addEventListener('keypress', event => {
    let text = signupForm.firstname.value + `${event.key}`;
    nameCheck(text, signupForm.firstnameError, "firstname");
})

signupForm.lastname && signupForm.lastname.addEventListener('keypress', event => {
    let text = signupForm.lastname.value + `${event.key}`;
    nameCheck(text, signupForm.lastnameError, "lastname");
})

signupForm.username && signupForm.username.addEventListener('keypress', event => {
    let text = signupForm.username.value + `${event.key}`;
    nameCheck(text, signupForm.usernameError, "username");
})

signupForm.password && signupForm.password.addEventListener('keypress', event => {
    let text = signupForm.password.value + `${event.key}`;
    pswdCheck(text, signupForm.passwordError);
})

