package org.cacanhdaden.quanlythuoc.control.authentication;

import org.cacanhdaden.quanlythuoc.view.login.CreateAccountView;
import org.cacanhdaden.quanlythuoc.view.login.ForgotPasswordView;
import org.cacanhdaden.quanlythuoc.view.login.LoginView;

public class LoginController {
    private LoginView login;
    public LoginController(LoginView login) {
        this.login = login;
        handleButtonForget();
        handleButtonCreateAccount();
    }
    void handleButtonCreateAccount(){
        login.getBtnRegister().addActionListener(
                e -> {
                    login.setVisible(false);
                    CreateAccountView create = new CreateAccountView();
                    CreateAccountController controller = new CreateAccountController(create);
                    create.setVisible(true);
                }
        );
    }
    void handleButtonForget(){
        login.getBtnForgot().addActionListener(e -> {
            login.setVisible(false);
            ForgotPasswordView fp = new ForgotPasswordView();
            ForgotPasswordController controller = new ForgotPasswordController(fp);
            fp.setVisible(true);
        });
    }
}
