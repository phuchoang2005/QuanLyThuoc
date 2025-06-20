package org.cacanhdaden.quanlythuoc.control.authentication;

import org.cacanhdaden.quanlythuoc.view.login.form.SignUpForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpController {
    private SignUpForm signUpForm;

    public SignUpController() {

    }

    public SignUpController(SignUpForm signUpForm) {
        this.signUpForm = signUpForm;
        handleSignUp();
    }

    public void handleSignUp() {
        // Implement sign-up logic here
        // For example, validate input fields, send data to the server, etc.
        System.out.println("Handling sign-up");
    }
}
