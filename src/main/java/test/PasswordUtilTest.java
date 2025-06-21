package test;

import org.cacanhdaden.quanlythuoc.util.PasswordUtil;

public class PasswordUtilTest {
    public static void main(String[] args) {
        // Test cases for PasswordUtil
        String password = "qwerty";
        String hashedPassword = PasswordUtil.hashPassword(password);

        System.out.println("Original Password: " + password);
        System.out.println("Hashed Password: " + hashedPassword);

        // Verify the password
        boolean isMatch = PasswordUtil.checkPassword(password, hashedPassword);
        System.out.println("Password matches: " + isMatch);
    }
}
