package org.cacanhdaden.quanlythuoc.util;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    // Hash mật khẩu
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(12)); // 12 là độ phức tạp (workload)
    }

    // Kiểm tra mật khẩu nhập vào có khớp với hash đã lưu không
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
