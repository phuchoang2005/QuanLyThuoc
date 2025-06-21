package org.cacanhdaden.quanlythuoc.model.dao;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.cacanhdaden.quanlythuoc.model.model.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@AllArgsConstructor
@NoArgsConstructor
public class LoginDAO {
    private Users user;

    public boolean handleLogin() {
        String sql = null;
        if (user.getEmail() != null) {
            sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        } else {
            sql = "SELECT COUNT(*) FROM users WHERE phone_number = ?";
        }

        try (
            Connection conn = MySQLConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, user.getEmail());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
