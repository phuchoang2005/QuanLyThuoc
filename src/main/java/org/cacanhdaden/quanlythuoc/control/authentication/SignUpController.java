package org.cacanhdaden.quanlythuoc.control.authentication;

import org.cacanhdaden.quanlythuoc.model.dto.SignUpDTO;
import org.cacanhdaden.quanlythuoc.services.SignUpService.Implement.SignUpServiceImp;
import org.cacanhdaden.quanlythuoc.services.SignUpService.SignUpServiceInterface;
import org.cacanhdaden.quanlythuoc.view.authentication.form.SignUpForm;

import java.util.Objects;

public class SignUpController {
    private final SignUpServiceInterface signUpService;

    public SignUpController(SignUpForm signUpForm) {
        this.signUpService = new SignUpServiceImp(signUpForm);

        SignUpDTO signUpDTO = new SignUpDTO(
            signUpForm.getTxtEmail().getText(),
            new String(signUpForm.getTxtPass().getPassword()),
            signUpForm.getTxtFullName().getText(),
            signUpForm.getDatePickerDob().getSelectedDate().toString(),
            Objects.requireNonNull(signUpForm.getCbGender().getSelectedItem()).toString(),
            signUpForm.getTxtPhone().getText(),
            signUpForm.getTxtAddress().getText()
        );

        this.signUpService.signUp(signUpDTO);
    }
}
