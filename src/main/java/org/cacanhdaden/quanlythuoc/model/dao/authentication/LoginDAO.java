package org.cacanhdaden.quanlythuoc.model.dao.authentication;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.cacanhdaden.quanlythuoc.model.dao.MySQLConnection;
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
        String qry1 = null;
        String qry2 = null;
        if (user.getEmail() != null) {
            qry1 = "SELECT COUNT(*) FROM users WHERE email = ?";
            qry2 = "SELECT password_hash FROM users WHERE email = ?";
        } else {
            qry1 = "SELECT COUNT(*) FROM users WHERE phone_number = ?";
            qry2 = "SELECT password_hash FROM users WHERE phone_number = ?";
        }

        try (
                Connection conn = MySQLConnection.getConnection();
                PreparedStatement ps1 = conn.prepareStatement(qry1);
                PreparedStatement ps2 = conn.prepareStatement(qry2);
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

    public Users.RoleEnum getUserRole() {
        String qry = null;
        Users.RoleEnum role = Users.RoleEnum.PATIENT; // Default role
        if (user.getEmail() != null) {
            qry = "SELECT role FROM users WHERE email = ?";
        } else {
            qry = "SELECT role FROM users WHERE phone_number = ?";
        }

        try (
            Connection conn = MySQLConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(qry);
        ) {
            if (user.getEmail() != null) {
                ps.setString(1, user.getEmail());
            } else {
                ps.setString(1, user.getPhoneNumber());
            }
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    role = Users.RoleEnum.valueOf(rs.getString("role").toUpperCase());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return role;
    }
}
