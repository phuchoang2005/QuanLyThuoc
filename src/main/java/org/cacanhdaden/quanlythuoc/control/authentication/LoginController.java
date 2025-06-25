package org.cacanhdaden.quanlythuoc.control.authentication;

import org.cacanhdaden.quanlythuoc.model.dto.LoginDTO;
import org.cacanhdaden.quanlythuoc.model.model.Users;
import org.cacanhdaden.quanlythuoc.services.LoginService.Implement.LoginServiceImp;
import org.cacanhdaden.quanlythuoc.services.LoginService.LoginServiceInterface;
import org.cacanhdaden.quanlythuoc.view.authentication.Launch;
import org.cacanhdaden.quanlythuoc.view.authentication.form.LoginForm;

import javax.swing.*;
public class LoginController {
    private final LoginServiceInterface loginService;

    public LoginController(LoginForm loginForm) {
        this.loginService = new LoginServiceImp(loginForm);

        LoginDTO loginDTO = new LoginDTO(
            loginForm.getTxtUser().getText(),
            new String(loginForm.getTxtPass().getPassword())
        );

        this.loginService.login(loginDTO);
    }
}
