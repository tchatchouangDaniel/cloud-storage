package com.udacity.jwdnd.course1.cloudstorage.model.form;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class LoginForm {
    @Size(min=2, max=50, message = "username should have at least 2 characters")
    private String username;

    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message="At least 8 chars, one uppercase letter, a number and a special character.")
    private String password;

    public LoginForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
