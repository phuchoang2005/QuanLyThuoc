package org.cacanhdaden.quanlythuoc.model.dao.authentication;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.cacanhdaden.quanlythuoc.model.dao.MySQLConnection;
import org.cacanhdaden.quanlythuoc.model.dto.LoginDTO;
import org.cacanhdaden.quanlythuoc.model.model.Users;
import org.cacanhdaden.quanlythuoc.util.Exception.InvalidInformationException;
import org.cacanhdaden.quanlythuoc.util.PasswordUtil;
import org.cacanhdaden.quanlythuoc.util.validator.EmailValidatorImp;
import org.cacanhdaden.quanlythuoc.util.validator.PhoneNumberValidatorImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@AllArgsConstructor
public class LoginDAO {
    private LoginDTO loginDTO;

    public boolean checkLogin() throws SQLException, InvalidInformationException {
        String qry1 = null;
        String qry2 = null;
        boolean result = false;

        if (EmailValidatorImp.isEmail(loginDTO.getUserInput())) {
            qry1 = "SELECT COUNT(*) FROM users WHERE email = ?";
            qry2 = "SELECT password_hash FROM users WHERE email = ?";
        } else
        if (PhoneNumberValidatorImp.isPhoneNumber(loginDTO.getUserInput())) {
            qry1 = "SELECT COUNT(*) FROM users WHERE phone_number = ?";
            qry2 = "SELECT password_hash FROM users WHERE phone_number = ?";
        } else {
            throw new InvalidInformationException("Invalid email or phone number format");
        }

        try {
            boolean isEmail = EmailValidatorImp.isEmail(loginDTO.getUserInput());
            boolean isPhoneNumber = PhoneNumberValidatorImp.isPhoneNumber(loginDTO.getUserInput());
            if (!isEmail && !isPhoneNumber) {
                throw new InvalidInformationException("Invalid email or phone number format");
            }
        } catch (InvalidInformationException e) {
            throw new InvalidInformationException("Invalid email or phone number format");
        }

        try (
            Connection conn = MySQLConnection.getConnection();
            PreparedStatement ps1 = conn.prepareStatement(qry1);
            PreparedStatement ps2 = conn.prepareStatement(qry2);
        ) {
            ps1.setString(1, loginDTO.getUserInput());
            ps2.setString(1, loginDTO.getUserInput());
            try (
                ResultSet rs1 = ps1.executeQuery();
                ResultSet rs2 = ps2.executeQuery();
            ) {
                if (rs1.next() && rs2.next()) {
                    result = (rs1.getInt(1) > 0) &&
                        PasswordUtil.checkPassword(
                            loginDTO.getPassword(),
                            rs2.getString("password_hash")
                        );
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Database error occurred during login", e);
        }
        return result;
    }
}
