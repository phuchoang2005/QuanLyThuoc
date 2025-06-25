package org.cacanhdaden.quanlythuoc.control.authentication;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cacanhdaden.quanlythuoc.model.dao.authentication.ResetPasswordDAO;
import org.cacanhdaden.quanlythuoc.model.object.Users;
import org.cacanhdaden.quanlythuoc.view.login.Launch;
import org.cacanhdaden.quanlythuoc.view.login.form.ResetPasswordForm;

import javax.swing.*;

@Getter
@Setter
@NoArgsConstructor
public class ResetPasswordController {
    private ResetPasswordForm resetPasswordForm;

    public ResetPasswordController(ResetPasswordForm resetPasswordForm) {
        this.resetPasswordForm = resetPasswordForm;
        handleResetButtonClick();
    }

    private void handleResetButtonClick() {
        String email = resetPasswordForm.getEmail();
        String password = new String(resetPasswordForm.getTxtPass().getPassword());
        String confirmPassword = new String(resetPasswordForm.getTxtConfirmPass().getPassword());

        if (
            password.isEmpty() ||
            confirmPassword.isEmpty()
        ) {
            JOptionPane.showMessageDialog(
                    resetPasswordForm,
                    "Vui lòng nhập đầy đủ thông tin",
                    "Lỗi nhập thông tin",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        if (!password.contentEquals(confirmPassword)) {
            JOptionPane.showMessageDialog(
                    resetPasswordForm,
                    "Mật khẩu và xác nhận mật khẩu không khớp",
                    "Lỗi nhập thông tin",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        Users user = Users.EmailUsers(
            email,
            password
        );

        if (resetPassword(user)) {
            JOptionPane.showMessageDialog(
                    resetPasswordForm,
                    "Đặt lại mật khẩu thành công",
                    "Thành công",
                    JOptionPane.INFORMATION_MESSAGE
            );
            Launch.showLoginForm();
        } else {
            JOptionPane.showMessageDialog(
                    resetPasswordForm,
                    "Đặt lại mật khẩu không thành công. Vui lòng kiểm tra lại thông tin.",
                    "Lỗi đặt lại mật khẩu",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private boolean resetPassword(Users user) {
        ResetPasswordDAO resetPasswordDAO = new ResetPasswordDAO(user);
        return resetPasswordDAO.handleResetPassword();
    }
}
