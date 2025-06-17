package org.cacanhdaden.quanlythuoc.control.authentication;

import org.cacanhdaden.quanlythuoc.model.dao.LoginDAO;
import org.cacanhdaden.quanlythuoc.model.dto.LoginDTO;
import org.cacanhdaden.quanlythuoc.view.login.application.Application;
import org.cacanhdaden.quanlythuoc.view.login.application.form.LoginForm;
import lombok.Getter;
import lombok.Setter;
import raven.toast.Notifications;

@Getter
@Setter
public class LoginController {
    private LoginForm loginForm;
    public LoginController() {

    }

    public LoginController(LoginForm loginForm) {
        this.loginForm = loginForm;
        handleLoginButtonClick();
    }

    void handleLoginButtonClick() {
        String email = this.loginForm.getTxtUser().getText();
        String password = new String(this.loginForm.getTxtPass().getPassword());
        if (login(email, password)) {
            // Login successful, proceed to the main application
            Application.login();
        } else {
            // Show error message
            Notifications.getInstance().show(
                    Notifications.Type.ERROR,
                    "Login Failed"
            );
        }
    }

    public boolean login(String email, String password) {
        LoginDTO loginDTO = new LoginDTO(email, password);
        if (!loginDTO.isValid()) {
            return false;
        }
        LoginDAO loginDAO = new LoginDAO(loginDTO);
        return loginDAO.checkLogin();
    }
}
