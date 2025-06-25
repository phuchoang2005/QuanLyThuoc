package org.cacanhdaden.quanlythuoc.util.validator;

import lombok.AllArgsConstructor;
import org.cacanhdaden.quanlythuoc.util.Exception.InvalidInformationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
public class GenericValidatorImp implements ValidatorInterface {
    private final String input;

    private static void checkValidInput(String input) throws InvalidInformationException {
        if (input == null || input.isEmpty()) {
            throw new InvalidInformationException("Input cannot be null or empty");
        }

        String charset = "<>?/.,:\";'{}|\\[\\]\\\\()!@#$%^&*\\-_+=~`";
        Pattern patt = Pattern.compile("[" + charset + "]");
        Matcher match = patt.matcher(input);

        if (match.find()) {
            throw new InvalidInformationException("Input contains illegal characters");
        }
    }

    @Override
    public void checkValid() throws InvalidInformationException {
        checkValidInput(this.input);
    }
}
