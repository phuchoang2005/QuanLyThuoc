package org.cacanhdaden.quanlythuoc.model.dao;

import org.cacanhdaden.quanlythuoc.model.model.PrescriptionDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionDetailDAO {

    public List<PrescriptionDetail> getDetailsByPrescriptionId(long prescriptionId) {
        List<PrescriptionDetail> details = new ArrayList<>();
        String sql = "SELECT pd.*, m.name as medicine_name " +
                "FROM prescription_details pd " +
                "JOIN medicines m ON pd.medicine_id = m.medicine_id " +
                "WHERE pd.prescription_id = ?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, prescriptionId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    PrescriptionDetail detail = new PrescriptionDetail();
                    detail.setDetailId(rs.getLong("detail_id"));
                    detail.setPrescriptionId(rs.getLong("prescription_id"));
                    detail.setMedicineId(rs.getLong("medicine_id"));
                    detail.setMedicineName(rs.getString("medicine_name"));
                    detail.setDosage(rs.getString("dosage"));
                    detail.setFrequency(rs.getString("frequency"));
                    detail.setQuantity(rs.getInt("quantity"));
                    detail.setDuration(rs.getString("duration"));
                    detail.setNotes(rs.getString("notes"));

                    details.add(detail);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return details;
    }
}