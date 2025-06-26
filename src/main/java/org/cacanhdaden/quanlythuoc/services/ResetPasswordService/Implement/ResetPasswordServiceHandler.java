package org.cacanhdaden.quanlythuoc.services.ResetPasswordService.Implement;

import org.cacanhdaden.quanlythuoc.view.authentication.form.ResetPasswordForm;

public class ResetPasswordServiceHandler {
    private final ResetPasswordForm resetPasswordForm;

    public ResetPasswordServiceHandler(final ResetPasswordForm resetPasswordForm) {
        this.resetPasswordForm = resetPasswordForm;
    }
}
