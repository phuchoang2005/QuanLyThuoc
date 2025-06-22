package org.cacanhdaden.quanlythuoc.view.patient.features.PrescriptionManager.features;
import lombok.Getter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
@Getter
public class TrackPrescriptionRequestPanel extends JPanel {
    private final JTable requestTable;
    private final DefaultTableModel tableModel;

    public TrackPrescriptionRequestPanel() {
        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel titleLabel = new JLabel("Truy vấn lịch sử dùng thuốc");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Table model with columns
        String[] columns = {
                "Bác sĩ", "Nguyên nhân", "Trạng thái", "Ghi chú bác sĩ", "Thời gian"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            // Make cells non-editable
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Mock data (you’ll load real data from DB later)
        tableModel.addRow(new Object[]{"Dr. Smith", "Refill blood pressure meds", "Pending", "", "2025-06-22"});
        tableModel.addRow(new Object[]{"Dr. Johnson", "New prescription for flu", "Approved", "Take rest and fluids", "2025-06-20"});
        tableModel.addRow(new Object[]{"Dr. Brown", "Skin rash treatment", "Rejected", "Needs in-person visit", "2025-06-18"});

        requestTable = new JTable(tableModel);
        requestTable.setRowHeight(28);
        requestTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(requestTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    // This can be called later when loading from database
    public void updateRequests(Object[][] data) {
        tableModel.setRowCount(0); // Clear existing
        for (Object[] row : data) {
            tableModel.addRow(row);
        }
    }
}

