package org.cacanhdaden.quanlythuoc.model.dao;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.cacanhdaden.quanlythuoc.model.object.Users;
import org.cacanhdaden.quanlythuoc.util.PasswordUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@AllArgsConstructor
@NoArgsConstructor
public class LoginDAO {
    private Users user;

    public boolean handleLogin() {
        String sql1 = null;
        String sql2 = null;
        if (user.getEmail() != null) {
            sql1 = "SELECT COUNT(*) FROM users WHERE email = ?";
            sql2 = "SELECT password_hash FROM users WHERE email = ?";
        } else {
            sql1 = "SELECT COUNT(*) FROM users WHERE phone_number = ?";
            sql2 = "SELECT password_hash FROM users WHERE phone_number = ?";
        }

        try (
            Connection conn = MySQLConnection.getConnection();
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            PreparedStatement ps2 = conn.prepareStatement(sql2);
        ) {
            if (user.getEmail() != null) {
                ps1.setString(1, user.getEmail());
                ps2.setString(1, user.getEmail());
            } else {
                ps1.setString(1, user.getPhoneNumber());
                ps2.setString(1, user.getPhoneNumber());
            }
            try (
                ResultSet rs1 = ps1.executeQuery();
                ResultSet rs2 = ps2.executeQuery();
            ) {
                if (rs1.next() && rs2.next()) {
                    return (rs1.getInt(1) > 0) &&
                        PasswordUtil.checkPassword(user.getPassword(), rs2.getString("password_hash"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
