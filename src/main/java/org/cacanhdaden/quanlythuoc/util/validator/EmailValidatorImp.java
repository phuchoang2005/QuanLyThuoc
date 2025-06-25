package org.cacanhdaden.quanlythuoc.util.validator;

import lombok.AllArgsConstructor;
import org.cacanhdaden.quanlythuoc.util.Exception.InvalidInformationException;

import java.util.regex.Pattern;

@AllArgsConstructor
public class EmailValidatorImp implements ValidatorInterface {
    private final String email;

    private static void checkValidEmail(String email) throws InvalidInformationException {
        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        if (!email.matches(regex)){
            throw new InvalidInformationException("Email không hợp lệ");
        }
    }

    public static boolean isEmail(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        final Pattern EMAIL_REGEX = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);
        return EMAIL_REGEX.matcher(input).matches();
    }

    @Override
    public void checkValid() throws InvalidInformationException {
       checkValidEmail(this.email);
    }
}
