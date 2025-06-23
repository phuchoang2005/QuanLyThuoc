package org.cacanhdaden.quanlythuoc.control.authentication;

import lombok.Getter;
import lombok.Setter;
import org.cacanhdaden.quanlythuoc.model.dao.SignUpDAO;
import org.cacanhdaden.quanlythuoc.util.EmailSendingUtil;
import org.cacanhdaden.quanlythuoc.view.login.Launch;
import org.cacanhdaden.quanlythuoc.view.login.form.OTPFillInForm;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.Objects;

@Getter
@Setter
public class OTPFillInController {
    private OTPFillInForm otpFillInForm;

    public OTPFillInController(OTPFillInForm otpFillInForm) {
        this.otpFillInForm = otpFillInForm;
    }

    public OTPFillInController(OTPFillInForm otpFillInForm, Object sourcePerformer) {
        this.otpFillInForm = otpFillInForm;
        if (((JButton) sourcePerformer).getName() == "btnFinish") {
            handleFinishButtonClick();
        } else if (((JButton) sourcePerformer).getName() == "btnResend") {
            handleResendButtonClick();
        } else {
            throw new IllegalArgumentException("Unknown source performer: " + sourcePerformer);
        }
    }

    private void handleFinishButtonClick() {
        if (otpFillInForm.getTxtOTP().getText().isEmpty()) {
            JOptionPane.showMessageDialog(
                otpFillInForm,
                "Vui lòng nhập mã OTP",
                "Lỗi nhập mã OTP",
                JOptionPane.ERROR_MESSAGE
            );
        } else if (!otpFillInForm.getTxtOTP().getText().equals(otpFillInForm.getOTPCode())) {
            JOptionPane.showMessageDialog(
                otpFillInForm,
                "Mã OTP không chính xác",
                "Lỗi mã OTP",
                JOptionPane.ERROR_MESSAGE
            );
        } else {
            JOptionPane.showMessageDialog(
                otpFillInForm,
                "Xác thực thành công",
                "Thành công",
                JOptionPane.INFORMATION_MESSAGE
            );

            SignUpDAO signupDAO = new SignUpDAO(otpFillInForm.getUser());

            if (signupDAO.handleSignUp()) {
                Launch.showLoginForm();
            } else {
                JOptionPane.showMessageDialog(
                    otpFillInForm,
                    "Đăng ký không thành công, vui lòng thử lại sau",
                    "Lỗi đăng ký",
                    JOptionPane.ERROR_MESSAGE
                );
                Launch.showSignUpForm();
            }
        }
    }

    private void handleResendButtonClick() {
        String OTPCode = EmailSendingUtil.sendAuthOTPEmail(otpFillInForm.getUser().getEmail());

        if (OTPCode == null) {
            JOptionPane.showMessageDialog(
                otpFillInForm,
                "Gửi mã OTP không thành công, vui lòng thử lại sau",
                "Lỗi gửi mã OTP",
                JOptionPane.ERROR_MESSAGE
            );
        } else {
            otpFillInForm.setOTPCode(
                OTPCode
            );
            JOptionPane.showMessageDialog(
                otpFillInForm,
                "Mã OTP đã được gửi lại đến email của bạn",
                "Thành công",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
}
