package org.cacanhdaden.quanlythuoc.services.LoginService.Implement;

import org.cacanhdaden.quanlythuoc.model.dao.authentication.LoginDAO;
import org.cacanhdaden.quanlythuoc.model.dto.LoginDTO;
import org.cacanhdaden.quanlythuoc.services.LoginService.LoginServiceInterface;
import org.cacanhdaden.quanlythuoc.util.Exception.InvalidInformationException;
import org.cacanhdaden.quanlythuoc.util.validator.EmailValidatorImp;
import org.cacanhdaden.quanlythuoc.view.authentication.form.LoginForm;

import javax.swing.*;
import java.sql.SQLException;

public class LoginServiceImp implements LoginServiceInterface {
    private final LoginForm loginForm;
    private LoginServiceHandler loginServiceHandler;

    public LoginServiceImp(LoginForm loginForm) {
        this.loginForm = loginForm;
        this.loginServiceHandler = new LoginServiceHandler(loginForm);
    }

    @Override
    public void login(LoginDTO loginDTO) {
        this.loginForm.getBtnLogin().addActionListener(e -> {
            try {
                LoginDAO loginDAO = new LoginDAO(loginDTO);
                if (loginDAO.checkLogin()) {
                    JOptionPane.showMessageDialog(
                            loginForm,
                            "Đăng nhập thành công",
                            "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    JOptionPane.showMessageDialog(
                            loginForm,
                            "Đăng nhập thất bại. Vui lòng kiểm tra lại thông tin.",
                            "Lỗi đăng nhập",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            } catch (InvalidInformationException iie) {
                iie.printStackTrace();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        });
    }
}
