package org.cacanhdaden.quanlythuoc.util;

import java.util.regex.Pattern;

public class StringMatcherUtil {
    public static boolean isEmail(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        final Pattern EMAIL_REGEX = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);
        return EMAIL_REGEX.matcher(input).matches();
    }
}
