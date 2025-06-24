package org.cacanhdaden.quanlythuoc.control.authentication;

import org.cacanhdaden.quanlythuoc.model.dao.authentication.LoginDAO;
import org.cacanhdaden.quanlythuoc.model.object.Users;
import org.cacanhdaden.quanlythuoc.util.StringMatcherUtil;
import org.cacanhdaden.quanlythuoc.view.login.Launch;
import org.cacanhdaden.quanlythuoc.view.login.form.LoginForm;
import javax.swing.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class LoginController {
    private LoginForm loginForm;

    public LoginController(LoginForm loginForm) {
        this.loginForm = loginForm;
        handleLoginButtonClick();
    }

    private void handleLoginButtonClick() {
        Users user = new Users();

        if (
            StringMatcherUtil.isEmail(this.loginForm.getTxtUser().getText())
        ) {
            user = Users.EmailUsers(
                this.loginForm.getTxtUser().getText(),
                new String(this.loginForm.getTxtPass().getPassword())
            );
        } else {
            user = Users.PhoneNumberUsers(
                this.loginForm.getTxtUser().getText(),
                new String(this.loginForm.getTxtPass().getPassword())
            );
        }

        if (login(user)) {
            Launch.showMainForm();
        } else {
            JOptionPane.showMessageDialog(
                loginForm,
                "Đăng nhập không thành công. Vui lòng kiểm tra lại thông tin.",
                "Lỗi đăng nhập",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private boolean login(Users user) {
        if (!isValid(user)) {
            return false;
        }
        LoginDAO loginDAO = new LoginDAO(user);
        return loginDAO.handleLogin();
    }

    private boolean isValid(Users user) {
        if (StringMatcherUtil.isEmail(this.loginForm.getTxtUser().getText())) {
            return user.getHashedPassword() != null && !user.getHashedPassword().isEmpty();
        } else {
            return user.getPhoneNumber() != null && !user.getPhoneNumber().isEmpty() &&
                user.getHashedPassword() != null && !user.getHashedPassword().isEmpty();
        }
    }
}
