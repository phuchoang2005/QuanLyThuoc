package org.cacanhdaden.quanlythuoc.control.doctor;

import org.cacanhdaden.quanlythuoc.view.authentication.Launch;
import org.cacanhdaden.quanlythuoc.view.doctor.features.DetailPrescriptionPanel;
import org.cacanhdaden.quanlythuoc.view.doctor.features.FormManagePrescription;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class PrescriptionManagerController {
    private final FormManagePrescription view;
    private final Connection connection;
    private DefaultTableModel tableModel;

    // Trạng thái hiện tại
    private long currentDoctorId = 1; // Giả sử ID của bác sĩ đang đăng nhập

    // Câu lệnh SQL
    private static final String GET_ALL_PRESCRIPTIONS =
            "SELECT p.id, p.patient_id, u.full_name, p.issue_date, " +
                    "CASE WHEN EXISTS (SELECT 1 FROM prescription_details WHERE prescription_id = p.id) " +
                    "THEN 'Đã có thuốc' ELSE 'Chưa có thuốc' END AS status " +
                    "FROM prescriptions p " +
                    "JOIN users u ON p.patient_id = u.id " +
                    "WHERE p.doctor_id = ? " +
                    "ORDER BY p.issue_date DESC";

    private static final String DELETE_PRESCRIPTION =
            "DELETE FROM prescriptions WHERE id = ?";

    public PrescriptionManagerController(FormManagePrescription view) {
        this.view = view;

        try {
            // Khởi tạo kết nối đến cơ sở dữ liệu
            String url = "jdbc:mysql://localhost:3306/QLT";
            String username = "root";
            String password = "012345678";
            connection = DriverManager.getConnection(url, username, password);

            // Thiết lập model cho bảng và nạp dữ liệu
            setupTableModel();
            loadPrescriptions();

            // Đăng ký các sự kiện cho các nút
            registerEventHandlers();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view,
                    "Không thể kết nối đến cơ sở dữ liệu: " + e.getMessage(),
                    "Lỗi kết nối", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }

    private void setupTableModel() {
        String[] columnNames = {"Mã đơn", "Mã BN", "Tên bệnh nhân", "Ngày tạo", "Trạng thái"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho phép chỉnh sửa trực tiếp trên bảng
            }
        };
        view.getTblPrescriptions().setModel(tableModel);
    }

    private void loadPrescriptions() {
        tableModel.setRowCount(0); // Xóa dữ liệu cũ

        try (PreparedStatement stmt = connection.prepareStatement(GET_ALL_PRESCRIPTIONS)) {
            stmt.setLong(1, currentDoctorId);

            try (ResultSet rs = stmt.executeQuery()) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                while (rs.next()) {
                    Vector<Object> row = new Vector<>();
                    row.add(rs.getLong("id"));
                    row.add(rs.getLong("patient_id"));
                    row.add(rs.getString("full_name"));

                    // Định dạng ngày tháng
                    Date issueDate = rs.getDate("issue_date");
                    row.add(dateFormat.format(issueDate));

                    row.add(rs.getString("status"));
                    tableModel.addRow(row);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view,
                    "Lỗi khi tải danh sách đơn thuốc: " + e.getMessage(),
                    "Lỗi truy vấn", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registerEventHandlers() {
        // Nút Thêm mới
        view.getBtnAdd().addActionListener(this::handleAddPrescription);

        // Nút Sửa
        view.getBtnEdit().addActionListener(this::handleEditPrescription);

        // Nút Xóa
        view.getBtnDelete().addActionListener(this::handleDeletePrescription);

        // Nút Xem chi tiết
        view.getBtnView().addActionListener(this::handleViewPrescription);
    }

    private void handleAddPrescription(ActionEvent e) {
        // Mở form thêm đơn thuốc mới (có thể làm dialog hoặc panel mới)
        JOptionPane.showMessageDialog(view,
                "Chức năng thêm đơn thuốc đang được phát triển.",
                "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

    private void handleEditPrescription(ActionEvent e) {
        int selectedRow = view.getTblPrescriptions().getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(view,
                    "Vui lòng chọn đơn thuốc cần sửa.",
                    "Chú ý", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Lấy ID của đơn thuốc được chọn
        long prescriptionId = (long) tableModel.getValueAt(selectedRow, 0);

        // Mở form sửa đơn thuốc
        JOptionPane.showMessageDialog(view,
                "Chức năng sửa đơn thuốc đang được phát triển.",
                "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

    private void handleDeletePrescription(ActionEvent e) {
        int selectedRow = view.getTblPrescriptions().getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(view,
                    "Vui lòng chọn đơn thuốc cần xóa.",
                    "Chú ý", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Lấy ID của đơn thuốc được chọn
        long prescriptionId = (long) tableModel.getValueAt(selectedRow, 0);

        // Xác nhận trước khi xóa
        int confirm = JOptionPane.showConfirmDialog(view,
                "Bạn có chắc chắn muốn xóa đơn thuốc này?",
                "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try (PreparedStatement stmt = connection.prepareStatement(DELETE_PRESCRIPTION)) {
                stmt.setLong(1, prescriptionId);
                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    // Xóa thành công, cập nhật lại bảng
                    loadPrescriptions();
                    JOptionPane.showMessageDialog(view,
                            "Đã xóa đơn thuốc thành công.",
                            "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(view,
                            "Không thể xóa đơn thuốc.",
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view,
                        "Lỗi khi xóa đơn thuốc: " + ex.getMessage(),
                        "Lỗi truy vấn", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleViewPrescription(ActionEvent e) {
        int selectedRow = view.getTblPrescriptions().getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(view,
                    "Vui lòng chọn đơn thuốc để xem chi tiết.",
                    "Chú ý", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Lấy ID của đơn thuốc được chọn
        long prescriptionId = (long) tableModel.getValueAt(selectedRow, 0);

        // Hiển thị form chi tiết đơn thuốc
        DetailPrescriptionPanel detailPanel = new DetailPrescriptionPanel(prescriptionId);
        new PrescriptionDetailController(detailPanel, prescriptionId, connection);
        Launch.showForm(detailPanel);
    }

    // Phương thức getter để truy cập trong FormManagePrescription
    public void refreshData() {
        loadPrescriptions();
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