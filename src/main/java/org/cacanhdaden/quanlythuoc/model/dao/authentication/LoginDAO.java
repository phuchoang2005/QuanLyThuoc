package org.cacanhdaden.quanlythuoc.model.dao.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.cacanhdaden.quanlythuoc.model.dao.MySQLConnection;
import org.cacanhdaden.quanlythuoc.model.dto.LoginDTO;
import org.cacanhdaden.quanlythuoc.model.model.Users;
import org.cacanhdaden.quanlythuoc.util.Exception.InvalidInformationException;
import org.cacanhdaden.quanlythuoc.util.PasswordUtil;
import org.cacanhdaden.quanlythuoc.util.validator.EmailValidatorImp;
import org.cacanhdaden.quanlythuoc.util.validator.PhoneNumberValidatorImp;
import org.cacanhdaden.quanlythuoc.view.authentication.Launch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
public class LoginDAO {
    private LoginDTO loginDTO;
    private Users user;

    public LoginDAO(LoginDTO loginDTO) {
        this.loginDTO = loginDTO;
    }

    public boolean handleLogin() throws SQLException, InvalidInformationException {
        String qry1 = null;
        String qry2 = null;
        String qry3 = null;
        boolean result = false;

        if (EmailValidatorImp.isEmail(loginDTO.getUserInput())) {
            qry1 = "SELECT COUNT(*) FROM users WHERE email = ?";
            qry2 = "SELECT password_hash FROM users WHERE email = ?";
            qry3 = "SELECT * FROM users WHERE email = ?";
        } else
        if (PhoneNumberValidatorImp.isPhoneNumber(loginDTO.getUserInput())) {
            qry1 = "SELECT COUNT(*) FROM users WHERE phone_number = ?";
            qry2 = "SELECT password_hash FROM users WHERE phone_number = ?";
            qry3 = "SELECT * FROM users WHERE phone_number = ?";
        } else {
            throw new InvalidInformationException("Invalid email or phone number format");
        }

        try (
            Connection conn = MySQLConnection.getConnection();
            PreparedStatement ps1 = conn.prepareStatement(qry1);
            PreparedStatement ps2 = conn.prepareStatement(qry2);
            PreparedStatement ps3 = conn.prepareStatement(qry3);
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

                    ps3.setString(1, loginDTO.getUserInput());
                    try (
                        ResultSet rs3 = ps3.executeQuery();
                    ) {
                        if (rs3.next()) {
                            user = new Users(
                                rs3.getString("id"),
                                rs3.getString("email"),
                                rs3.getString("password_hash"),
                                rs3.getString("full_name"),
                                rs3.getString("date_of_birth"),
                                rs3.getString("gender"),
                                rs3.getString("phone_number"),
                                rs3.getString("address"),
                                Users.RoleStatusEnum.valueOf(rs3.getString("role")),
                                rs3.getString("created_at"),
                                rs3.getString("updated_at")
                            );
                            Launch.loadUser(user);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Database error occurred during login", e);
        }
        return result;
    }
}
