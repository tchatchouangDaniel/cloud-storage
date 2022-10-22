export const pswdCheck = (text, passwordError) => {
    let condition1 = /(?=.*\d)/; //should contain atleast 1 digit
    let condition2 = /(?=.*[a-z])/; //should contain atleast 1 lowercase
    let condition3 = /(?=.*[A-Z])/; //should contain atleast 1 uppercase
    let condition4 = /[a-zA-Z0-9]{8,}/; //should contain atleast 8 characters

    passwordError.style.color = "red";

    if (!text.match(condition1)) {
        passwordError.style.display = "block";
        passwordError.innerText = "Password should contain atleast 1 digit";
        return;
    }

    if (!text.match(condition2)) {
        passwordError.style.display = "block";
        passwordError.innerText = "Password should contain atleast 1 lowercase";
        return;
    }

    if (!text.match(condition3)) {
        passwordError.style.display = "block";
        passwordError.innerText = "Password should contain atleast 1 uppercase";
        return;
    }

    if (!text.match(condition4)) {
        passwordError.style.display = "block";
        passwordError.innerText = "Password should contain atleast 8 characters";
        return;
    }

    if(text.length === 0) passwordError.style.display = "none";
    passwordError.style.display = "none";
    return;
}

export const nameCheck = (text, nameError, label) => {
    nameError.style.color = "red";

    if (text.length < 2) {
        nameError.style.display = "block";
        nameError.innerText = label + " should contain at least 2 characters";
        return;
    }

    if (text.length > 50) {
        nameError.style.display = "block";
        nameError.innerText = label + " should contain at most 50 characters";
        return;
    }



    if(text.length === 0) nameError.style.display = "none";
    nameError.style.display = "none";
    return;
}