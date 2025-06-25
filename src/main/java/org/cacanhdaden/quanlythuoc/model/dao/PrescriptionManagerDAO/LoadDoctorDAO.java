package org.cacanhdaden.quanlythuoc.model.dao.PrescriptionManagerDAO;

import lombok.AllArgsConstructor;
import org.cacanhdaden.quanlythuoc.model.dao.MySQLConnection;
import org.cacanhdaden.quanlythuoc.model.dto.LoadDoctorDTO;
import org.cacanhdaden.quanlythuoc.model.model.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@AllArgsConstructor
public class LoadDoctorDAO {
    public ArrayList<LoadDoctorDTO> load(){
        ArrayList<LoadDoctorDTO> doctors = new ArrayList<>();
        try{
            Connection conn = MySQLConnection.getConnection();
            String sql = "select id, full_name, role from users where role = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "DOCTOR");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                doctors.add(new LoadDoctorDTO(rs.getString("id"), rs.getString("full_name") ));
            }
        }catch(Exception ex){
            throw new IllegalStateException(ex);
        }finally {
            return doctors;
        }
    }
}
