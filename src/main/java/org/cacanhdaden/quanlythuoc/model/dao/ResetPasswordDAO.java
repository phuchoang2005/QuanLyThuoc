package org.cacanhdaden.quanlythuoc.model.dao;

import org.cacanhdaden.quanlythuoc.model.object.Users;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.cacanhdaden.quanlythuoc.util.PasswordUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordDAO {
    private Users user;

    public boolean handleResetPassword() {
        String sql = "UPDATE users SET password_hash = ? WHERE email = ?";

        try (
            Connection conn = MySQLConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, PasswordUtil.hashPassword(user.getPassword()));
            ps.setString(2, user.getEmail());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
