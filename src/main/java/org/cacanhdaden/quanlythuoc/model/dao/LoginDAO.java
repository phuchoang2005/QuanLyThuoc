package org.cacanhdaden.quanlythuoc.model.dao;
import org.cacanhdaden.quanlythuoc.model.dto.UserLoginDTO;
import org.cacanhdaden.quanlythuoc.util.PasswordUtil;
import lombok.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@AllArgsConstructor
public class LoginDAO {
    private UserLoginDTO dto;
    public boolean isLoginSuccess() {
        String sql = "SELECT user_name, password FROM users WHERE username = ?";
        try (Connection conn = MySQLConnection.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
        if (dto.getUsername().equals(rs.getString("user_name")) && PasswordUtil.checkPassword(dto.getPassword_no_hash(), rs.getString("password"))) {
            return true;
        }else{
            return false;
        }
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            return false;
        }
    }
}
