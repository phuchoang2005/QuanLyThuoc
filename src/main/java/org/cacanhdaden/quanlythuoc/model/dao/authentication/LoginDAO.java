package org.cacanhdaden.quanlythuoc.model.dao.authentication;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.cacanhdaden.quanlythuoc.model.dao.MySQLConnection;
import org.cacanhdaden.quanlythuoc.model.model.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@AllArgsConstructor
public class LoginDAO {
    private Users users;

    public boolean checkLogin() {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ? AND password_hash = ?";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, users.getEmail());
            ps.setString(2, users.getHashedPassword());
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
