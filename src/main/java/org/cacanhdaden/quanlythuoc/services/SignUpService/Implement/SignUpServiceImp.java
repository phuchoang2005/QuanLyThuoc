package org.cacanhdaden.quanlythuoc.services.SignUpService.Implement;

import org.cacanhdaden.quanlythuoc.model.dao.authentication.SignUpDAO;
import org.cacanhdaden.quanlythuoc.model.dto.SignUpDTO;
import org.cacanhdaden.quanlythuoc.services.SignUpService.SignUpServiceInterface;
import org.cacanhdaden.quanlythuoc.view.authentication.Launch;
import org.cacanhdaden.quanlythuoc.view.authentication.form.SignUpForm;

import javax.swing.*;

public class SignUpServiceImp implements SignUpServiceInterface {
    private final SignUpForm signUpForm;
    private SignUpServiceHandler signUpServiceHandler;

    public SignUpServiceImp(SignUpForm signUpForm) {
        this.signUpForm = signUpForm;
        this.signUpServiceHandler = new SignUpServiceHandler(signUpForm);
    }

    @Override
    public void signUp(SignUpDTO signUpDTO) {
        try {
            signUpServiceHandler.checkNullOrEmptyFields();
            SignUpDAO signUpDAO = new SignUpDAO(signUpDTO);
            if  (signUpDAO.handleSignUp()) {
                JOptionPane.showMessageDialog(
                        signUpForm,
                        "Đăng ký thành công! Vui lòng đăng nhập.",
                        "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE
                );
                Launch.showLoginForm();
            } else {
                JOptionPane.showMessageDialog(
                        signUpForm,
                        "Đăng ký thất bại. Vui lòng kiểm tra lại thông tin.",
                        "Lỗi đăng ký",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
        }
    }
}
