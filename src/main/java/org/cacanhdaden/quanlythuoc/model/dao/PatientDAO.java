
package org.cacanhdaden.quanlythuoc.model.dao;

import lombok.Data;
import org.cacanhdaden.quanlythuoc.model.model.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class PatientDAO {

    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT u.id, u.full_name, " +
                "YEAR(CURDATE()) - YEAR(u.date_of_birth) - (RIGHT(CURDATE(), 5) < RIGHT(u.date_of_birth, 5)) as age, " +
                "u.gender, u.email, u.phone_number, " +
                "MAX(p.created_at) as last_visit " +
                "FROM users u " +
                "LEFT JOIN prescriptions p ON u.id = p.patient_id " +
                "WHERE u.role = 'PATIENT' " +
                "GROUP BY u.id, u.full_name, u.date_of_birth, u.gender, u.email, u.phone_number";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Patient patient = new Patient();
                patient.setPatientId(rs.getLong("id"));
                patient.setFullName(rs.getString("full_name"));
                patient.setAge(rs.getInt("age"));
                patient.setGender(rs.getString("gender"));
                patient.setEmail(rs.getString("email"));
                patient.setPhone(rs.getString("phone_number"));

                Timestamp lastVisit = rs.getTimestamp("last_visit");
                if (lastVisit != null) {
                    patient.setLastVisit(lastVisit.toLocalDateTime().toLocalDate());
                }

                patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }

    public Patient getPatientById(long patientId) {
        String sql = "SELECT u.*, " +
                "YEAR(CURDATE()) - YEAR(u.date_of_birth) - (RIGHT(CURDATE(), 5) < RIGHT(u.date_of_birth, 5)) as age " +
                "FROM users u WHERE u.id = ? AND u.role = 'PATIENT'";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, patientId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Patient patient = new Patient();
                    patient.setPatientId(rs.getLong("id"));
                    patient.setFullName(rs.getString("full_name"));
                    patient.setAge(rs.getInt("age"));
                    patient.setGender(rs.getString("gender"));
                    patient.setEmail(rs.getString("email"));
                    patient.setPhone(rs.getString("phone_number"));
                    return patient;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}