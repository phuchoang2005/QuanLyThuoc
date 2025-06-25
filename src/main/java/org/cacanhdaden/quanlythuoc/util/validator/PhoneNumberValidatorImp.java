package org.cacanhdaden.quanlythuoc.util.validator;

import org.cacanhdaden.quanlythuoc.util.Exception.InvalidInformationException;

import java.util.regex.Pattern;

public class PhoneNumberValidatorImp implements ValidatorInterface {
    private final String phoneNumber;

    public PhoneNumberValidatorImp(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private static void checkValidPhoneNumber(String phoneNumber) throws InvalidInformationException {
        String regex = "^(\\+84|0)\\d{9,10}$";
        if (!phoneNumber.matches(regex)) {
            throw new InvalidInformationException("Số điện thoại không hợp lệ");
        }
    }

    public static boolean isPhoneNumber(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        final Pattern PHONE_REGEX = Pattern.compile("^\\+?[0-9]{10,15}$");
        return PHONE_REGEX.matcher(input).matches();
    }

    @Override
    public void checkValid() throws InvalidInformationException {
        checkValidPhoneNumber(this.phoneNumber);
    }
}
