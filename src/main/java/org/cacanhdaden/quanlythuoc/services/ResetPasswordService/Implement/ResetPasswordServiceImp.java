package org.cacanhdaden.quanlythuoc.services.ResetPasswordService.Implement;

import org.cacanhdaden.quanlythuoc.model.dao.authentication.ResetPasswordDAO;
import org.cacanhdaden.quanlythuoc.model.dto.ResetPasswordDTO;
import org.cacanhdaden.quanlythuoc.services.ResetPasswordService.ResetPasswordServiceInterface;
import org.cacanhdaden.quanlythuoc.util.Exception.InvalidInformationException;
import org.cacanhdaden.quanlythuoc.view.authentication.form.ResetPasswordForm;

import javax.swing.*;

public class ResetPasswordServiceImp implements ResetPasswordServiceInterface {
    private final ResetPasswordForm resetPasswordForm;
    private ResetPasswordServiceHandler resetPasswordServiceHandler;

    public ResetPasswordServiceImp(final ResetPasswordForm resetPasswordForm) {
        this.resetPasswordForm = resetPasswordForm;
        this.resetPasswordServiceHandler = new ResetPasswordServiceHandler(resetPasswordForm);
    }

    @Override
    public void resetPassword(ResetPasswordDTO resetPasswordDTO) {
        this.resetPasswordForm.getBtnConfirm().addActionListener(e -> {
            try {
                ResetPasswordDAO resetPasswordDAO = new ResetPasswordDAO(resetPasswordDTO);

                if (resetPasswordDAO.handleResetPassword()) {
                    JOptionPane.showMessageDialog(
                            resetPasswordForm,
                            "Đặt lại mật khẩu thành công",
                            "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    JOptionPane.showMessageDialog(
                            resetPasswordForm,
                            "Đặt lại mật khẩu thất bại. Vui lòng kiểm tra lại thông tin.",
                            "Lỗi đặt lại mật khẩu",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            } catch (InvalidInformationException iie) {
                iie.printStackTrace();
            }
        });
    }
}
