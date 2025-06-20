package org.cacanhdaden.quanlythuoc.control.authentication;

import org.cacanhdaden.quanlythuoc.model.dao.LoginDAO;
import org.cacanhdaden.quanlythuoc.model.model.Users;
import org.cacanhdaden.quanlythuoc.view.login.application.Application;
import org.cacanhdaden.quanlythuoc.view.login.application.form.LoginForm;

import javax.swing.*;
public class LoginController {
    private LoginForm loginForm;

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

    private boolean login(String email, String password) {
        Users users = new Users(email, password);
        if (isValid(users)) {
            return true;
        }
        return false;
    }

    private boolean isValid(Users users) {
        return !users.getEmail().isEmpty() && !users.getHashedPassword().isEmpty();
    }
}
