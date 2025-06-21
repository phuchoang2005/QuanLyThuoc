package org.cacanhdaden.quanlythuoc.control.authentication;

import org.cacanhdaden.quanlythuoc.model.dao.SignUpDAO;
import org.cacanhdaden.quanlythuoc.model.model.Users;
import org.cacanhdaden.quanlythuoc.util.GenderPassingUtil;
import org.cacanhdaden.quanlythuoc.view.login.Launch;
import org.cacanhdaden.quanlythuoc.view.login.form.SignUpForm;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Objects;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class SignUpController {
    private SignUpForm signUpForm;

    public SignUpController(SignUpForm signUpForm) {
        this.signUpForm = signUpForm;
        handleSignUpButtonClick();
    }

    public void handleSignUpButtonClick() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Users user = new Users(
            this.signUpForm.getTxtEmail().getText(),
            new String(this.signUpForm.getTxtPass().getPassword()),
            this.signUpForm.getTxtFullName().getText(),
            this.signUpForm.getDatePickerDob().getSelectedDate().toString(),
            Objects.requireNonNull(
                GenderPassingUtil.StringToUserEnum(
                    this.signUpForm.getCbGender().getSelectedItem().toString()
                )
            ).toString(),
            this.signUpForm.getTxtPhone().getText(),
            this.signUpForm.getTxtAddress().getText()
        );

        if (signup(user)) {
            Launch.showLoginForm();
        } else {
            JOptionPane.showMessageDialog(
                signUpForm,
                "Đăng ký không thành công. Vui lòng kiểm tra lại thông tin.",
                "Lỗi đăng ký",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private boolean signup(Users user) {
        if (!isValid(user)) {
            return true;
        }
        SignUpDAO signupDAO = new SignUpDAO(user);
        return signupDAO.handleSignUp();
    }

    private boolean isValid(Users user) {
        return user.getEmail() != null && !user.getEmail().isEmpty() &&
            user.getHashedPassword() != null && !user.getHashedPassword().isEmpty() &&
            user.getFullName() != null && !user.getFullName().isEmpty() &&
            user.getDateOfBirth() != null && !user.getDateOfBirth().isEmpty() &&
            user.getGender().toString() != null && !user.getGender().toString().isEmpty() &&
            user.getPhoneNumber() != null && !user.getPhoneNumber().isEmpty() &&
            user.getAddress() != null && !user.getAddress().isEmpty();
    }
}
