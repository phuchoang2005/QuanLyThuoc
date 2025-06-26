package org.cacanhdaden.quanlythuoc.services.SignUpService.Implement;

import org.cacanhdaden.quanlythuoc.view.authentication.form.SignUpForm;

public class SignUpServiceHandler {
    private final SignUpForm signUpForm;

    public SignUpServiceHandler(final SignUpForm signUpForm) {
        this.signUpForm = signUpForm;
    }

    void checkNullOrEmptyFields() {
        if (signUpForm.getTxtEmail().getText().isEmpty() ||
            signUpForm.getTxtPass().getPassword().length == 0 ||
            signUpForm.getTxtFullName().getText().isEmpty() ||
            signUpForm.getDatePickerDob().getSelectedDate() == null ||
            signUpForm.getCbGender().getSelectedItem() == null ||
            signUpForm.getTxtPhone().getText().isEmpty() ||
            signUpForm.getTxtAddress().getText().isEmpty()) {
            throw new IllegalArgumentException("All fields must be filled out.");
        }
    }
}
