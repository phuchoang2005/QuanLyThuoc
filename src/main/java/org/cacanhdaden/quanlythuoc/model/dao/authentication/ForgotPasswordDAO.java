package org.cacanhdaden.quanlythuoc.model.dao.authentication;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cacanhdaden.quanlythuoc.model.dao.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@NoArgsConstructor
public class ForgotPasswordDAO {
    private String emailInput;

    public ForgotPasswordDAO(String emailInput) {
        this.emailInput = emailInput;
    }

    public boolean handleCheckInfo() {
        String qry = "SELECT COUNT(*) FROM users WHERE email = ?";
        try (
                Connection conn = MySQLConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(qry);
        ) {
            ps.setString(1, this.emailInput);
            try (
                    ResultSet rs = ps.executeQuery();
            ) {
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
