package org.cacanhdaden.quanlythuoc.model.dao;

import lombok.*;
import org.cacanhdaden.quanlythuoc.model.dto.NewPatientDTO;
import org.cacanhdaden.quanlythuoc.model.model.User.Role;

import java.sql.Connection;
import org.cacanhdaden.quanlythuoc.util.PasswordUtil;
import java.sql.PreparedStatement;

@AllArgsConstructor
public class CreateNewPatientDAO {
    private NewPatientDTO  newPatient;

    public boolean  Register(){
        String sql = "INSERT INTO users (id, full_name, email, password, phone, age, user_name, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection conn = MySQLConnection.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, newPatient.getId());
            stmt.setString(2, newPatient.getFullName());
            stmt.setString(3, newPatient.getEmail());
            stmt.setString(4, PasswordUtil.hashPassword(newPatient.getPassword_no_hash()));
            stmt.setString(5, newPatient.getPhone());
            stmt.setInt(6, newPatient.getAge());
            stmt.setString(7, newPatient.getUserName());
            stmt.setInt(8, newPatient.getRole().ordinal());
            stmt.executeUpdate();
            return true;
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            return false;
        }
    }
}
