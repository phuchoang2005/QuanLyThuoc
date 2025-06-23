package org.cacanhdaden.quanlythuoc.control.authentication;

import lombok.Getter;
import lombok.Setter;
import org.cacanhdaden.quanlythuoc.model.dao.SignUpDAO;
import org.cacanhdaden.quanlythuoc.util.EmailSendingUtil;
import org.cacanhdaden.quanlythuoc.view.login.Launch;
import org.cacanhdaden.quanlythuoc.view.login.form.OTPSignUpForm;

import javax.swing.*;

@Getter
@Setter
public class OTPSignUpController {
    private OTPSignUpForm otpSignUpForm;

    public OTPSignUpController(OTPSignUpForm otpSignUpForm) {
        this.otpSignUpForm = otpSignUpForm;
    }

    public OTPSignUpController(OTPSignUpForm otpSignUpForm, Object sourcePerformer) {
        this.otpSignUpForm = otpSignUpForm;
        if (((JButton) sourcePerformer).getName() == "btnFinish") {
            handleFinishButtonClick();
        } else if (((JButton) sourcePerformer).getName() == "btnResend") {
            handleResendButtonClick();
        } else {
            throw new IllegalArgumentException("Unknown source performer: " + sourcePerformer);
        }
    }

    private void handleFinishButtonClick() {
        if (otpSignUpForm.getTxtOTP().getText().isEmpty()) {
            JOptionPane.showMessageDialog(
                    otpSignUpForm,
                "Vui lòng nhập mã OTP",
                "Lỗi nhập mã OTP",
                JOptionPane.ERROR_MESSAGE
            );
        } else if (!otpSignUpForm.getTxtOTP().getText().equals(otpSignUpForm.getOTPCode())) {
            JOptionPane.showMessageDialog(
                    otpSignUpForm,
                "Mã OTP không chính xác",
                "Lỗi mã OTP",
                JOptionPane.ERROR_MESSAGE
            );
        } else {
            JOptionPane.showMessageDialog(
                    otpSignUpForm,
                "Xác thực thành công",
                "Thành công",
                JOptionPane.INFORMATION_MESSAGE
            );

            SignUpDAO signupDAO = new SignUpDAO(otpSignUpForm.getUser());

            if (signupDAO.handleSignUp()) {
                Launch.showLoginForm();
            } else {
                JOptionPane.showMessageDialog(
                        otpSignUpForm,
                    "Đăng ký không thành công, vui lòng thử lại sau",
                    "Lỗi đăng ký",
                    JOptionPane.ERROR_MESSAGE
                );
                Launch.showSignUpForm();
            }
        }
    }

    private void handleResendButtonClick() {
        String OTPCode = EmailSendingUtil.sendAuthOTPEmail(otpSignUpForm.getUser().getEmail());

        if (OTPCode == null) {
            JOptionPane.showMessageDialog(
                    otpSignUpForm,
                "Gửi mã OTP không thành công, vui lòng thử lại sau",
                "Lỗi gửi mã OTP",
                JOptionPane.ERROR_MESSAGE
            );
        } else {
            otpSignUpForm.setOTPCode(
                OTPCode
            );
            JOptionPane.showMessageDialog(
                    otpSignUpForm,
                "Mã OTP đã được gửi lại đến email của bạn",
                "Thành công",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
}
