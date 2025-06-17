package org.cacanhdaden.quanlythuoc.model.dao;

import org.cacanhdaden.quanlythuoc.model.dto.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
public class LoginDAO {
    private LoginDTO loginDTO;
    public LoginDAO() {

    }

    public LoginDAO(LoginDTO loginDTO) {
        this.loginDTO = loginDTO;
    }

    public boolean checkLogin() {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ? AND password_hash = ?";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, loginDTO.getEmail());
            ps.setString(2, loginDTO.getHashedPassword());
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
