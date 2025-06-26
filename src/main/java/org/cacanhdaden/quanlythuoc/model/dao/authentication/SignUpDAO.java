package org.cacanhdaden.quanlythuoc.model.dao.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cacanhdaden.quanlythuoc.model.dao.MySQLConnection;
import org.cacanhdaden.quanlythuoc.model.dto.SignUpDTO;
import org.cacanhdaden.quanlythuoc.model.model.Users;
import org.cacanhdaden.quanlythuoc.util.PasswordUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDAO {
    private SignUpDTO signUpDTO;

    public boolean handleSignUp() {
        boolean flag = false;
        String sql = "INSERT INTO `users` (\n" +
                "  `email`, `password_hash`, `full_name`, `date_of_birth`, `gender`, `phone_number`, `address`, `role`\n" +
                ") VALUES\n" +
                "(?, ?, ?, ?, ?, ?, ?, 'PATIENT');";
        try (
                Connection conn = MySQLConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, signUpDTO.getEmail());
            ps.setString(2, PasswordUtil.hashPassword(signUpDTO.getPassword()));
            ps.setString(3, signUpDTO.getFullName());
            ps.setString(4, signUpDTO.getDateOfBirth());
            ps.setString(5, signUpDTO.getGender());
            ps.setString(6, signUpDTO.getPhoneNumber());
            ps.setString(7, signUpDTO.getAddress());
            ps.executeUpdate();
            flag = true;
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
