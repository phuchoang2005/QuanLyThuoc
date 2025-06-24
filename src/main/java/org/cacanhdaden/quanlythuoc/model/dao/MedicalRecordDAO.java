package org.cacanhdaden.quanlythuoc.model.dao;

import org.cacanhdaden.quanlythuoc.model.model.MedicalRecord;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordDAO {

    public List<MedicalRecord> getRecordsByPatientId(long patientId) {
        List<MedicalRecord> records = new ArrayList<>();
        String sql = "SELECT mr.*, u.full_name as patient_name " +
                "FROM medical_records mr " +
                "JOIN users u ON mr.patient_id = u.user_id " +
                "WHERE mr.patient_id = ? " +
                "ORDER BY mr.visit_date DESC";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, patientId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    MedicalRecord record = new MedicalRecord();
                    record.setRecordId(rs.getLong("record_id"));
                    record.setPatientId(rs.getLong("patient_id"));
                    record.setPatientName(rs.getString("patient_name"));
                    record.setDiagnosis(rs.getString("diagnosis"));
                    record.setSymptoms(rs.getString("symptoms"));
                    record.setTreatment(rs.getString("treatment"));
                    record.setVisitDate(rs.getTimestamp("visit_date").toLocalDateTime());
                    record.setNotes(rs.getString("notes"));

                    records.add(record);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return records;
    }

    public String getFormattedRecordsByPatientId(long patientId) {
        List<MedicalRecord> records = getRecordsByPatientId(patientId);
        StringBuilder sb = new StringBuilder();

        for (MedicalRecord record : records) {
            sb.append("=== HỒ SƠ BỆNH ÁN ===\n");
            sb.append("Ngày khám: ").append(record.getVisitDate()).append("\n");
            sb.append("Bệnh nhân: ").append(record.getPatientName()).append("\n");
            sb.append("Triệu chứng: ").append(record.getSymptoms()).append("\n");
            sb.append("Chẩn đoán: ").append(record.getDiagnosis()).append("\n");
            sb.append("Điều trị: ").append(record.getTreatment()).append("\n");
            sb.append("Ghi chú: ").append(record.getNotes()).append("\n");
            sb.append("\n================================\n\n");
        }

        return sb.toString();
    }
}