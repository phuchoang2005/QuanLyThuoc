package org.cacanhdaden.quanlythuoc.util.validator;

import lombok.AllArgsConstructor;
import org.cacanhdaden.quanlythuoc.util.Exception.InvalidInformationException;

@AllArgsConstructor
public class EmailValidatorImp implements ValidatorInterface {
    private final String email;

    private static void checkValidEmail(String email) throws InvalidInformationException {
        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        if (!email.matches(regex)){
            throw new InvalidInformationException("Email không hợp lệ");
        }
    }

    @Override
    public void checkValid() throws InvalidInformationException {
       checkValidEmail(this.email);
    }
}
