package org.cacanhdaden.quanlythuoc.model.dao.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cacanhdaden.quanlythuoc.model.dao.MySQLConnection;
import org.cacanhdaden.quanlythuoc.model.dto.ResetPasswordDTO;
import org.cacanhdaden.quanlythuoc.util.Exception.InvalidInformationException;
import org.cacanhdaden.quanlythuoc.util.validator.EmailValidatorImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordDAO {
    private ResetPasswordDTO resetPasswordDTO;

    public boolean handleResetPassword() throws InvalidInformationException {
        if (!EmailValidatorImp.isEmail(resetPasswordDTO.getEmail())) {
            throw new InvalidInformationException("Invalid email or phone number format");
        }

        String sql = "UPDATE users SET password_hash = ? WHERE email = ?";

        try (
            Connection conn = MySQLConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, resetPasswordDTO.getHashPassword());
            ps.setString(2, resetPasswordDTO.getEmail());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
