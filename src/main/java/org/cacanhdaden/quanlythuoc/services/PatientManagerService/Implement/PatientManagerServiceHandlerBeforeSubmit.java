package org.cacanhdaden.quanlythuoc.services.PatientManagerService.Implement;

import lombok.AllArgsConstructor;
import org.cacanhdaden.quanlythuoc.util.Exception.InvalidInformationException;
import org.cacanhdaden.quanlythuoc.util.validator.DateValidatorImp;
import org.cacanhdaden.quanlythuoc.util.validator.EmailValidatorImp;
import org.cacanhdaden.quanlythuoc.util.validator.ValidatorInterface;
import org.cacanhdaden.quanlythuoc.view.patient.features.PatientManager.PatientManagerPanel;
@AllArgsConstructor
class PatientManagerServiceHandlerBeforeSubmit{
    private final PatientManagerPanel panel;
    void checkDateValid() throws InvalidInformationException {
        final String dobString = panel.getDobTextField().getText();
        try {
            ValidatorInterface validator = new DateValidatorImp(dobString);
            validator.checkValid();
        }catch(InvalidInformationException exception){
            throw new InvalidInformationException(exception.getMessage());
        }
    }

    void checkEmailValid() throws InvalidInformationException {
        final String emailString = panel.getEmailTextField().getText();
        try{
            ValidatorInterface validator = new EmailValidatorImp(emailString);
            validator.checkValid();
        }catch(InvalidInformationException exception){
            throw new InvalidInformationException(exception.getMessage());
        }
    }
}
