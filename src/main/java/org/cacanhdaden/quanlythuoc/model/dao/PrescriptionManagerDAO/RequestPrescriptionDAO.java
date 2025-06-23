package org.cacanhdaden.quanlythuoc.model.dao.PrescriptionManagerDAO;

import lombok.AllArgsConstructor;
import org.cacanhdaden.quanlythuoc.model.dao.MySQLConnection;
import org.cacanhdaden.quanlythuoc.model.model.PrescriptionRequest;
import org.cacanhdaden.quanlythuoc.model.model.Users;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.time.LocalDate;

@AllArgsConstructor
public class RequestPrescriptionDAO {
    private final PrescriptionRequest request;
    private final Users doctor;
    public void update(){
        try{
            String sql = "insert into prescription_requests (patient_id, doctor_id, reason, updated_at) values (?, ?, ?, ?)";
            Connection conn = MySQLConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(2, doctor.getId());
            ps.setString(1, request.getPatient_id());
            ps.setString(3, request.getReason());
            ps.setString(4, String.valueOf(Date.valueOf(LocalDate.now())));

            ps.execute();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
