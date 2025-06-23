package org.cacanhdaden.quanlythuoc.util.validator;

import lombok.AllArgsConstructor;
import org.cacanhdaden.quanlythuoc.util.Exception.InvalidInformationException;

@AllArgsConstructor
public class EmailValidatorImp implements ValidatorInterface {
    private final String email;

    private static void checkValidEmail(String email) throws InvalidInformationException {
        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        try{
            if (!email.matches(regex)){
                throw new InvalidInformationException("Email không hợp lệ");
            }
        }catch(InvalidInformationException exception){
            throw new InvalidInformationException(exception.getMessage());
        }
    }

    @Override
    public void checkValid() throws InvalidInformationException {
       checkValidEmail(this.email);
    }
}
