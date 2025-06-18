package org.cacanhdaden.quanlythuoc.control.authentication;

import org.cacanhdaden.quanlythuoc.model.dao.LoginDAO;
import org.cacanhdaden.quanlythuoc.model.dto.LoginDTO;
import org.cacanhdaden.quanlythuoc.view.login.application.Application;
import org.cacanhdaden.quanlythuoc.view.login.application.form.LoginForm;
import lombok.Getter;
import lombok.Setter;
import raven.toast.Notifications;

import javax.swing.*;

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
            Application.showMainForm();
        } else {
            // Show error message
            JOptionPane.showMessageDialog(
                loginForm,
                "Invalid email or password. Please try again.",
                "Login Error",
                JOptionPane.ERROR_MESSAGE
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
