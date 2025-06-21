package org.cacanhdaden.quanlythuoc.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IllegalCharacterUtil {
    public static boolean checkIllegalCharacter(String inp) {
        String charset = "<>?/.,:\";'{}|\\[\\]\\\\()!@#$%^&*\\-_+=~`";
        Pattern patt = Pattern.compile("[" + charset + "]");
        Matcher match = patt.matcher(inp);
        return match.find();
    }

    public static boolean checkIllegalName(String inp) {
        String charset = "<>?/.,:\";'{}|\\[\\]\\\\()!@#$%^&*\\-_+=~`";
        Pattern patt = Pattern.compile("[" + charset + "]");
        Matcher match = patt.matcher(inp);
        return match.find();
    }

    public static boolean checkIllegalEmail(String inp) {
        final Pattern EMAIL_REGEX = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);
        return !EMAIL_REGEX.matcher(inp).matches();
    }

    public static boolean checkIllegalPhoneNumber(String inp) {
        String charset = "<>?/.,:\";'{}|\\[\\]\\\\()!@#$%^&*\\-_+=~`";
        Pattern patt = Pattern.compile("[" + charset + "]");
        Matcher match = patt.matcher(inp);
        return match.find() || inp.length() < 10;
    }
}
