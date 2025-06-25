package org.cacanhdaden.quanlythuoc.control.authentication;

import lombok.Getter;
import lombok.Setter;
import org.cacanhdaden.quanlythuoc.model.dao.authentication.ForgotPasswordDAO;
import org.cacanhdaden.quanlythuoc.util.EmailSendingUtil;
import org.cacanhdaden.quanlythuoc.view.authentication.Launch;
import org.cacanhdaden.quanlythuoc.view.authentication.form.ForgotPasswordForm;

import javax.swing.*;

@Getter
@Setter
public class ForgotPasswordController {
    private ForgotPasswordForm forgotPasswordForm;

    public ForgotPasswordController(ForgotPasswordForm forgotPasswordForm) {
        this.forgotPasswordForm = forgotPasswordForm;
        handleConfirmButtonClick();
    }

    private void handleConfirmButtonClick() {
        String userInput = this.forgotPasswordForm.getTxtUser().getText();

        if (checkInfo(userInput)) {
            JOptionPane.showMessageDialog(
                this.forgotPasswordForm,
                "Thông tin hợp lệ. Đang gửi mã OTP đến Email của bạn...",
                "Xác nhận thông tin",
                JOptionPane.INFORMATION_MESSAGE
            );
            String OTPcode = EmailSendingUtil.sendAuthOTPEmail(userInput);
            Launch.showOTPForgotPasswordForm();
//            Launch.loadOTPForgotPasswordInfo(userInput, OTPcode);
            return;
        } else {
            JOptionPane.showMessageDialog(
                this.forgotPasswordForm,
                "Thông tin điền vào không tồn tại hoặc không hợp lệ. Vui lòng kiểm tra lại.",
                "Lỗi nhập thông tin",
                JOptionPane.ERROR_MESSAGE
            );

        }
    }

    private boolean checkInfo(String userInput) {
        if (!isValid(userInput)) {
            JOptionPane.showMessageDialog(
                this.forgotPasswordForm,
                "Vui lòng nhập Email",
                "Lỗi nhập thông tin",
                JOptionPane.ERROR_MESSAGE
            );
        }
        ForgotPasswordDAO forgotPasswordDAO = new ForgotPasswordDAO(userInput);
        return forgotPasswordDAO.handleCheckInfo();
    }

    private boolean isValid(String userInput) {
        return !userInput.isEmpty();
    }
}
