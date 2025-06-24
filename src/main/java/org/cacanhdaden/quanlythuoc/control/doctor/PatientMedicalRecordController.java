package org.cacanhdaden.quanlythuoc.control.doctor;

import lombok.Data;
import lombok.Getter;
import org.cacanhdaden.quanlythuoc.model.dao.MySQLConnection;
import org.cacanhdaden.quanlythuoc.view.doctor.features.FormPatientList;
import org.cacanhdaden.quanlythuoc.view.doctor.features.FormMedicalRecord;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Vector;

@Data
public class PatientMedicalRecordController {

    // Constants
    private static final String GET_ALL_PATIENTS =
            "SELECT u.user_id, u.full_name, u.age, u.gender, " +
                    "MAX(p.created_at) as last_visit_date " +
                    "FROM users u " +
                    "LEFT JOIN prescriptions p ON u.user_id = p.patient_id " +
                    "WHERE u.role = 'patient' " +
                    "GROUP BY u.user_id, u.full_name, u.age, u.gender " +
                    "ORDER BY last_visit_date DESC";

    private static final String GET_PATIENT_MEDICAL_RECORD =
            "SELECT mr.record_id, mr.diagnosis, mr.symptoms, mr.treatment, " +
                    "mr.visit_date, mr.notes " +
                    "FROM medical_records mr " +
                    "WHERE mr.patient_id = ? " +
                    "ORDER BY mr.visit_date DESC";

    private static final String DATE_PATTERN = "dd/MM/yyyy";
    private static final String DATE_TIME_PATTERN = "dd/MM/yyyy HH:mm";
    private static final String[] PATIENT_COLUMNS = {
            "Mã bệnh nhân", "Họ tên", "Tuổi", "Giới tính", "Ngày khám gần nhất"
    };

    // Components
    @Getter private final FormPatientList patientListView;
    @Getter private final FormMedicalRecord medicalRecordView;
    @Getter private Connection connection;
    @Getter private DefaultTableModel patientTableModel;

    public PatientMedicalRecordController(FormPatientList patientListView, FormMedicalRecord medicalRecordView) {
        this.patientListView = patientListView;
        this.medicalRecordView = medicalRecordView;

        initializeConnection();
        setupComponents();
    }

    private void initializeConnection() {
        try {
            connection = MySQLConnection.getConnection();
        } catch (SQLException e) {
            handleConnectionError(e);
            throw new RuntimeException("Không thể khởi tạo kết nối database", e);
        }
    }

    private void setupComponents() {
        setupTableModel();
        loadPatients();
        registerEventHandlers();
    }

    private void setupTableModel() {
        patientTableModel = new DefaultTableModel(PATIENT_COLUMNS, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        if (patientListView != null && patientListView.getTblPatients() != null) {
            patientListView.updateTableModel(patientTableModel);
        }
    }

    private void loadPatients() {
        if (patientTableModel == null) return;

        patientTableModel.setRowCount(0);

        try (PreparedStatement stmt = connection.prepareStatement(GET_ALL_PATIENTS);
             ResultSet rs = stmt.executeQuery()) {

            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);

            while (rs.next()) {
                Vector<Object> row = createPatientRow(rs, dateFormat);
                patientTableModel.addRow(row);
            }

        } catch (SQLException e) {
            handleDatabaseError("tải danh sách bệnh nhân", e);
        }
    }

    private Vector<Object> createPatientRow(ResultSet rs, SimpleDateFormat dateFormat) throws SQLException {
        Vector<Object> row = new Vector<>();
        row.add(rs.getLong("user_id"));
        row.add(rs.getString("full_name"));
        row.add(rs.getInt("age"));
        row.add(rs.getString("gender"));

        Timestamp lastVisitDate = rs.getTimestamp("last_visit_date");
        String formattedDate = lastVisitDate != null ?
                dateFormat.format(lastVisitDate) :
                "Chưa có lần khám";
        row.add(formattedDate);

        return row;
    }

    private void registerEventHandlers() {
        if (patientListView != null && patientListView.getTblPatients() != null) {
            patientListView.getTblPatients().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 1) {
                        handlePatientSelection();
                    }
                }
            });
        }
    }

    private void handlePatientSelection() {
        if (patientListView == null) return;

        int selectedRow = patientListView.getSelectedPatientRow();
        if (selectedRow < 0) return;

        long patientId = (Long) patientListView.getValueAt(selectedRow, 0);
        String patientName = (String) patientListView.getValueAt(selectedRow, 1);

        loadMedicalRecord(patientId, patientName);
    }

    private void loadMedicalRecord(long patientId, String patientName) {
        StringBuilder recordText = new StringBuilder();
        recordText.append("THÔNG TIN BỆNH ÁN CỦA BỆNH NHÂN: ").append(patientName).append("\n\n");

        try (PreparedStatement stmt = connection.prepareStatement(GET_PATIENT_MEDICAL_RECORD)) {
            stmt.setLong(1, patientId);

            try (ResultSet rs = stmt.executeQuery()) {
                SimpleDateFormat dateTimeFormat = new SimpleDateFormat(DATE_TIME_PATTERN);
                boolean hasRecords = false;

                while (rs.next()) {
                    hasRecords = true;
                    appendMedicalRecordEntry(recordText, rs, dateTimeFormat);
                }

                if (!hasRecords) {
                    recordText.append("Bệnh nhân chưa có hồ sơ bệnh án nào.");
                }
            }

            updateMedicalRecordDisplay(recordText.toString());

        } catch (SQLException e) {
            handleDatabaseError("tải hồ sơ bệnh án", e);
        }
    }

    private void appendMedicalRecordEntry(StringBuilder recordText, ResultSet rs,
                                          SimpleDateFormat dateTimeFormat) throws SQLException {
        recordText.append("==================================================\n");

        Timestamp visitDate = rs.getTimestamp("visit_date");
        if (visitDate != null) {
            recordText.append("Ngày khám: ").append(dateTimeFormat.format(visitDate)).append("\n");
        }

        recordText.append("Mã hồ sơ: ").append(rs.getLong("record_id")).append("\n\n");

        String symptoms = rs.getString("symptoms");
        if (symptoms != null && !symptoms.trim().isEmpty()) {
            recordText.append("TRIỆU CHỨNG:\n").append(symptoms).append("\n\n");
        }

        String diagnosis = rs.getString("diagnosis");
        if (diagnosis != null && !diagnosis.trim().isEmpty()) {
            recordText.append("CHUẨN ĐOÁN:\n").append(diagnosis).append("\n\n");
        }

        String treatment = rs.getString("treatment");
        if (treatment != null && !treatment.trim().isEmpty()) {
            recordText.append("ĐIỀU TRỊ:\n").append(treatment).append("\n\n");
        }

        String notes = rs.getString("notes");
        if (notes != null && !notes.trim().isEmpty()) {
            recordText.append("GHI CHÚ:\n").append(notes).append("\n");
        }

        recordText.append("==================================================\n\n");
    }

    private void updateMedicalRecordDisplay(String recordText) {
        if (medicalRecordView != null) {
            SwingUtilities.invokeLater(() -> {
                try {
                    java.lang.reflect.Method method = medicalRecordView.getClass()
                            .getDeclaredMethod("setMedicalRecordText", String.class);
                    method.invoke(medicalRecordView, recordText);
                } catch (Exception e) {
                    updateTextAreaDirectly(recordText);
                }
            });
        }
    }

    private void updateTextAreaDirectly(String recordText) {
        java.awt.Component[] components = medicalRecordView.getComponents();
        for (java.awt.Component comp : components) {
            if (comp instanceof JScrollPane) {
                JScrollPane scrollPane = (JScrollPane) comp;
                if (scrollPane.getViewport().getView() instanceof JTextArea) {
                    JTextArea textArea = (JTextArea) scrollPane.getViewport().getView();
                    textArea.setText(recordText);
                    textArea.setCaretPosition(0);
                    break;
                }
            }
        }
    }

    private void handleConnectionError(SQLException e) {
        String message = "Không thể kết nối đến cơ sở dữ liệu: " + e.getMessage();
        JOptionPane.showMessageDialog(
                patientListView,
                message,
                "Lỗi kết nối",
                JOptionPane.ERROR_MESSAGE
        );
        e.printStackTrace();
    }

    private void handleDatabaseError(String operation, SQLException e) {
        String message = "Lỗi khi " + operation + ": " + e.getMessage();
        JOptionPane.showMessageDialog(
                patientListView,
                message,
                "Lỗi truy vấn",
                JOptionPane.ERROR_MESSAGE
        );
        e.printStackTrace();
    }

    // Public methods for external access
    public void refreshPatientList() {
        loadPatients();
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}