package org.cacanhdaden.quanlythuoc.control.authentication;

import org.cacanhdaden.quanlythuoc.model.dto.UserLoginDTO;
import org.cacanhdaden.quanlythuoc.view.login.CreateAccountView;
import org.cacanhdaden.quanlythuoc.view.login.ForgotPasswordView;
import org.cacanhdaden.quanlythuoc.view.login.LoginView;
import org.cacanhdaden.quanlythuoc.model.dao.LoginDAO;

import javax.swing.*;

public class LoginController {
    private LoginView login;
    public LoginController(LoginView login) {
        this.login = login;
        handleButtonForget();
        handleButtonCreateAccount();
    }
    void handleButtonSubmit(){
        login.getBtnLogin().addActionListener(e ->{
            UserLoginDTO dto = new UserLoginDTO();
            dto.setUsername(login.getTxtUsername());
            dto.setPassword_no_hash(login.getTxtPassword());
            LoginDAO loginCheck = new LoginDAO(dto);
            if (loginCheck.isLoginSuccess()){
                //
            }else{
                JOptionPane.showMessageDialog(login, "Bạn đã nhập sai Username/Password", "Mời bạn nhập lại", JOptionPane.ERROR_MESSAGE);
            }
        });
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
