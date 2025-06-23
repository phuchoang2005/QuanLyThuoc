package org.cacanhdaden.quanlythuoc.control.doctor;

import org.cacanhdaden.quanlythuoc.view.doctor.features.FormPatientList;
import org.cacanhdaden.quanlythuoc.view.doctor.features.FormMedicalRecord;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class PatientMedicalRecordController {
    private final FormPatientList patientListView;
    private final FormMedicalRecord medicalRecordView;
    private final Connection connection;
    private DefaultTableModel patientTableModel;

    // Câu lệnh SQL
    private static final String GET_ALL_PATIENTS =
            "SELECT u.id, u.full_name, TIMESTAMPDIFF(YEAR, u.date_of_birth, CURDATE()) as age, " +
                    "u.gender, MAX(p.issue_date) as last_visit_date " +
                    "FROM users u " +
                    "LEFT JOIN prescriptions p ON u.id = p.patient_id " +
                    "WHERE u.role = 'patient' " +
                    "GROUP BY u.id, u.full_name, u.date_of_birth, u.gender " +
                    "ORDER BY last_visit_date DESC";

    private static final String GET_PATIENT_MEDICAL_RECORD =
            "SELECT p.id, p.diagnosis, p.notes, p.issue_date, " +
                    "GROUP_CONCAT(CONCAT(d.name, ' (', pd.dosage, ') - ', pd.frequency, ' - SL: ', pd.quantity) " +
                    "SEPARATOR '\n') as medications " +
                    "FROM prescriptions p " +
                    "LEFT JOIN prescription_details pd ON p.id = pd.prescription_id " +
                    "LEFT JOIN drugs d ON pd.drug_id = d.id " +
                    "WHERE p.patient_id = ? " +
                    "GROUP BY p.id, p.diagnosis, p.notes, p.issue_date " +
                    "ORDER BY p.issue_date DESC";

    public PatientMedicalRecordController(FormPatientList patientListView, FormMedicalRecord medicalRecordView) {
        this.patientListView = patientListView;
        this.medicalRecordView = medicalRecordView;

        try {
            // Khởi tạo kết nối đến cơ sở dữ liệu
            String url = "jdbc:mysql://localhost:3306/QLT";
            String username = "root";
            String password = "012345678";
            connection = DriverManager.getConnection(url, username, password);

            // Thiết lập model cho bảng và nạp dữ liệu
            setupTableModel();
            loadPatients();

            // Đăng ký sự kiện khi chọn bệnh nhân
            registerEventHandlers();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(patientListView,
                    "Không thể kết nối đến cơ sở dữ liệu: " + e.getMessage(),
                    "Lỗi kết nối", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }

    private void setupTableModel() {
        String[] columnNames = {"Mã bệnh nhân", "Họ tên", "Tuổi", "Giới tính", "Ngày khám gần nhất"};
        patientTableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        patientListView.getTblPatients().setModel(patientTableModel);
    }

    private void loadPatients() {
        patientTableModel.setRowCount(0); // Xóa dữ liệu cũ

        try (PreparedStatement stmt = connection.prepareStatement(GET_ALL_PATIENTS)) {
            try (ResultSet rs = stmt.executeQuery()) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                while (rs.next()) {
                    Vector<Object> row = new Vector<>();
                    row.add(rs.getLong("id"));
                    row.add(rs.getString("full_name"));
                    row.add(rs.getInt("age"));
                    row.add(rs.getString("gender"));

                    // Định dạng ngày tháng
                    Date lastVisitDate = rs.getDate("last_visit_date");
                    row.add(lastVisitDate != null ? dateFormat.format(lastVisitDate) : "Chưa có lần khám");

                    patientTableModel.addRow(row);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(patientListView,
                    "Lỗi khi tải danh sách bệnh nhân: " + e.getMessage(),
                    "Lỗi truy vấn", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registerEventHandlers() {
        patientListView.getTblPatients().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) { // Xử lý click đơn
                    handlePatientSelection();
                }
            }
        });
    }

    private void handlePatientSelection() {
        int selectedRow = patientListView.getTblPatients().getSelectedRow();
        if (selectedRow < 0) {
            return;
        }

        // Lấy ID của bệnh nhân được chọn
        long patientId = (long) patientTableModel.getValueAt(selectedRow, 0);
        String patientName = (String) patientTableModel.getValueAt(selectedRow, 1);

        // Tải và hiển thị hồ sơ bệnh án
        loadMedicalRecord(patientId, patientName);
    }

    private void loadMedicalRecord(long patientId, String patientName) {
        StringBuilder recordText = new StringBuilder();
        recordText.append("THÔNG TIN BỆNH ÁN CỦA BỆNH NHÂN: ").append(patientName).append("\n\n");

        try (PreparedStatement stmt = connection.prepareStatement(GET_PATIENT_MEDICAL_RECORD)) {
            stmt.setLong(1, patientId);

            try (ResultSet rs = stmt.executeQuery()) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                boolean hasRecords = false;

                while (rs.next()) {
                    hasRecords = true;
                    recordText.append("--------------------------------------------------\n");
                    recordText.append("Ngày khám: ").append(dateFormat.format(rs.getDate("issue_date"))).append("\n");
                    recordText.append("Mã đơn thuốc: ").append(rs.getLong("id")).append("\n\n");
                    recordText.append("CHUẨN ĐOÁN:\n").append(rs.getString("diagnosis")).append("\n\n");

                    // Hiển thị danh sách thuốc
                    String medications = rs.getString("medications");
                    if (medications != null && !medications.isEmpty()) {
                        recordText.append("THUỐC ĐÃ KÊ:\n").append(medications).append("\n\n");
                    } else {
                        recordText.append("THUỐC ĐÃ KÊ: Không có\n\n");
                    }

                    // Hiển thị ghi chú nếu có
                    String notes = rs.getString("notes");
                    if (notes != null && !notes.isEmpty()) {
                        recordText.append("GHI CHÚ:\n").append(notes).append("\n");
                    }

                    recordText.append("--------------------------------------------------\n\n");
                }

                if (!hasRecords) {
                    recordText.append("Bệnh nhân chưa có hồ sơ bệnh án nào.");
                }
            }

            // Cập nhật nội dung vào FormMedicalRecord
            medicalRecordView.setMedicalRecordText(recordText.toString());

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(patientListView,
                    "Lỗi khi tải hồ sơ bệnh án: " + e.getMessage(),
                    "Lỗi truy vấn", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Cập nhật giao diện FormPatientList
    public void updatePatientListView(FormPatientList newView) {
        // Gán view mới và thiết lập lại model cũng như các event handler
    }

    // Cập nhật giao diện FormMedicalRecord
    public void updateMedicalRecordView(FormMedicalRecord newView) {
        // Gán view mới
    }

    // Đảm bảo giải phóng tài nguyên khi đóng ứng dụng
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}