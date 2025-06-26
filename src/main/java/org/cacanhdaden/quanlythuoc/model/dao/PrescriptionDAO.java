package org.cacanhdaden.quanlythuoc.model.dao;

import lombok.Data;
import org.cacanhdaden.quanlythuoc.model.model.Prescription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Data
public class PrescriptionDAO {

    public List<Prescription> getAllPrescriptions() {
        List<Prescription> prescriptions = new ArrayList<>();
        String sql = "SELECT p.prescription_id, p.patient_id, p.doctor_id, p.created_at, p.status, u.full_name as patient_name " +
                "FROM prescriptions p " +
                "JOIN users u ON p.patient_id = u.user_id " +
                "ORDER BY p.created_at DESC";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Prescription prescription = new Prescription();
                prescription.setPrescriptionId(rs.getLong("prescription_id"));
                prescription.setPatientId(rs.getLong("patient_id"));
                prescription.setDoctorId(rs.getLong("doctor_id"));
                prescription.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                prescription.setStatus(rs.getString("status"));
                prescription.setPatientName(rs.getString("patient_name"));

                prescriptions.add(prescription);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prescriptions;
    }

    public Prescription getPrescriptionById(long prescriptionId) {
        String sql = "SELECT p.*, u.full_name as patient_name " +
                "FROM prescriptions p " +
                "JOIN users u ON p.patient_id = u.user_id " +
                "WHERE p.prescription_id = ?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, prescriptionId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Prescription prescription = new Prescription();
                    prescription.setPrescriptionId(rs.getLong("prescription_id"));
                    prescription.setPatientId(rs.getLong("patient_id"));
                    prescription.setDoctorId(rs.getLong("doctor_id"));
                    prescription.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                    prescription.setStatus(rs.getString("status"));
                    prescription.setPatientName(rs.getString("patient_name"));
                    return prescription;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}