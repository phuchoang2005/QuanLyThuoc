package org.cacanhdaden.quanlythuoc.control.doctor;

import org.cacanhdaden.quanlythuoc.view.doctor.features.DetailPrescriptionPanel;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Vector;

public class PrescriptionDetailController {
    private final DetailPrescriptionPanel view;
    private final long prescriptionId;
    private final Connection connection;
    private DefaultTableModel tableModel;

    private static final String GET_PRESCRIPTION_DETAILS =
            "SELECT pd.id, d.id as drug_id, d.name as drug_name, pd.dosage, pd.frequency, " +
                    "pd.quantity, pd.duration, pd.notes " +
                    "FROM prescription_details pd " +
                    "JOIN drugs d ON pd.drug_id = d.id " +
                    "WHERE pd.prescription_id = ?";

    public PrescriptionDetailController(DetailPrescriptionPanel view, long prescriptionId, Connection connection) {
        this.view = view;
        this.prescriptionId = prescriptionId;
        this.connection = connection;

        setupTableModel();
        loadPrescriptionDetails();

        // Đăng ký sự kiện cho nút quay lại (đã được xử lý trong DetailPrescriptionPanel)
    }

    private void setupTableModel() {
        String[] columnNames = {"Mã thuốc", "Tên thuốc", "Liều lượng", "Tần suất", "Số lượng", "Thời gian", "Ghi chú"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho phép chỉnh sửa trực tiếp trên bảng
            }
        };
        view.getTblDetails().setModel(tableModel);
    }

    private void loadPrescriptionDetails() {
        tableModel.setRowCount(0); // Xóa dữ liệu cũ

        try (PreparedStatement stmt = connection.prepareStatement(GET_PRESCRIPTION_DETAILS)) {
            stmt.setLong(1, prescriptionId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Vector<Object> row = new Vector<>();
                    row.add(rs.getLong("drug_id"));
                    row.add(rs.getString("drug_name"));
                    row.add(rs.getString("dosage"));
                    row.add(rs.getString("frequency"));
                    row.add(rs.getInt("quantity"));
                    row.add(rs.getString("duration"));
                    row.add(rs.getString("notes"));
                    tableModel.addRow(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}