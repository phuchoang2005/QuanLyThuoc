package org.cacanhdaden.quanlythuoc.model.dao.authentication;

import lombok.Getter;
import lombok.Setter;
import org.cacanhdaden.quanlythuoc.model.dao.MySQLConnection;
import org.cacanhdaden.quanlythuoc.model.object.Users;
import org.cacanhdaden.quanlythuoc.util.GenderPassingUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDAO {
    private Users user;

    public boolean handleSignUp() {
        boolean flag = false;
        String sql = "INSERT INTO `users` (\n" +
                "  `email`, `password_hash`, `full_name`, `date_of_birth`, `gender`, `phone_number`, `address`, `role`\n" +
                ") VALUES\n" +
                "(?, ?, ?, ?, ?, ?, ?, 'patient');";
        try (
                Connection conn = MySQLConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getHashedPassword());
            ps.setString(3, user.getFullName());
            ps.setString(4, user.getDateOfBirth());
            ps.setString(5, GenderPassingUtil.UserEnumToLoadData(user.getGender()));
            ps.setString(6, user.getPhoneNumber());
            ps.setString(7, user.getAddress());
            ps.executeUpdate();
            flag = true;
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
