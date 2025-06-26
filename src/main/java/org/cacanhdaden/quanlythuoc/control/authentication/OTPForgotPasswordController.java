package org.cacanhdaden.quanlythuoc.control.authentication;

import lombok.Getter;
import lombok.Setter;
import org.cacanhdaden.quanlythuoc.util.EmailSendingUtil;
import org.cacanhdaden.quanlythuoc.view.authentication.Launch;
import org.cacanhdaden.quanlythuoc.view.authentication.form.OTPForgotPasswordForm;

import javax.swing.*;

@Getter
@Setter
public class OTPForgotPasswordController {
    private OTPForgotPasswordForm otpForgotPasswordForm;

    public OTPForgotPasswordController(OTPForgotPasswordForm otpForgotPasswordForm) {
        this.otpForgotPasswordForm = otpForgotPasswordForm;
    }

    public OTPForgotPasswordController(OTPForgotPasswordForm otpForgotPasswordForm, Object sourcePerformer) {
        this.otpForgotPasswordForm = otpForgotPasswordForm;
        if (((JButton) sourcePerformer).getName() == "btnFinish") {
            handleFinishButtonClick();
        } else if (((JButton) sourcePerformer).getName() == "btnResend") {
            handleResendButtonClick();
        } else {
            throw new IllegalArgumentException("Unknown source performer: " + sourcePerformer);
        }
    }

    private void handleFinishButtonClick() {
        if (otpForgotPasswordForm.getTxtOTP().getText().isEmpty()) {
            JOptionPane.showMessageDialog(
                    otpForgotPasswordForm,
                "Vui lòng nhập mã OTP",
                "Lỗi nhập mã OTP",
                JOptionPane.ERROR_MESSAGE
            );
        } else if (!otpForgotPasswordForm.getTxtOTP().getText().equals(otpForgotPasswordForm.getOTPCode())) {
            JOptionPane.showMessageDialog(
                    otpForgotPasswordForm,
                "Mã OTP không chính xác",
                "Lỗi mã OTP",
                JOptionPane.ERROR_MESSAGE
            );
        } else {
            JOptionPane.showMessageDialog(
                    otpForgotPasswordForm,
                "Xác thực thành công",
                "Thành công",
                JOptionPane.INFORMATION_MESSAGE
            );
            Launch.showResetPasswordForm();
            Launch.loadResetPasswordInfo(otpForgotPasswordForm.getEmail());
        }
    }

    private void handleResendButtonClick() {
        String OTPCode = EmailSendingUtil.sendAuthOTPEmail(otpForgotPasswordForm.getEmail());

        if (OTPCode == null) {
            JOptionPane.showMessageDialog(
                    otpForgotPasswordForm,
                "Gửi mã OTP không thành công, vui lòng thử lại sau",
                "Lỗi gửi mã OTP",
                JOptionPane.ERROR_MESSAGE
            );
        } else {
            otpForgotPasswordForm.setOTPCode(
                OTPCode
            );
            JOptionPane.showMessageDialog(
                    otpForgotPasswordForm,
                "Mã OTP đã được gửi lại đến email của bạn",
                "Thành công",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
}
