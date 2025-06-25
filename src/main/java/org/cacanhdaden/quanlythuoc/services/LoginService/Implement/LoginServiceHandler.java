package org.cacanhdaden.quanlythuoc.services.LoginService.Implement;

import org.cacanhdaden.quanlythuoc.util.Exception.InvalidInformationException;
import org.cacanhdaden.quanlythuoc.util.validator.EmailValidatorImp;
import org.cacanhdaden.quanlythuoc.util.validator.PhoneNumberValidatorImp;
import org.cacanhdaden.quanlythuoc.view.authentication.form.LoginForm;

public class LoginServiceHandler {
    private final LoginForm loginForm;

    public LoginServiceHandler(final LoginForm loginForm) {
        this.loginForm = loginForm;
    }
}
