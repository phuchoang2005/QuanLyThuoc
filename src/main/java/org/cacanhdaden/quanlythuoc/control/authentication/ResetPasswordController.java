package org.cacanhdaden.quanlythuoc.control.authentication;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cacanhdaden.quanlythuoc.model.dao.authentication.ResetPasswordDAO;
import org.cacanhdaden.quanlythuoc.model.dto.ResetPasswordDTO;
import org.cacanhdaden.quanlythuoc.model.model.Users;
import org.cacanhdaden.quanlythuoc.services.ResetPasswordService.Implement.ResetPasswordServiceImp;
import org.cacanhdaden.quanlythuoc.services.ResetPasswordService.ResetPasswordServiceInterface;
import org.cacanhdaden.quanlythuoc.util.PasswordUtil;
import org.cacanhdaden.quanlythuoc.view.authentication.Launch;
import org.cacanhdaden.quanlythuoc.view.authentication.form.ResetPasswordForm;

import javax.swing.*;

public class ResetPasswordController {
    private final ResetPasswordServiceInterface resetPasswordService;

    public ResetPasswordController(ResetPasswordForm resetPasswordForm) {
        this.resetPasswordService = new ResetPasswordServiceImp(resetPasswordForm);

        ResetPasswordDTO resetPasswordDTO = new ResetPasswordDTO(
            resetPasswordForm.getEmail(),
            new String(resetPasswordForm.getTxtPass().getPassword()),
            PasswordUtil.hashPassword(new String(resetPasswordForm.getTxtPass().getPassword()))
        );

        this.resetPasswordService.resetPassword(resetPasswordDTO);
    }
}
