package org.cacanhdaden.quanlythuoc.view.patient.features.PrescriptionManager.features;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ViewActivePrescriptionPanel extends JPanel {
    private JComboBox<String> prescriptionSelector;
    private JLabel doctorLabel, diagnosisLabel, issueDateLabel, notesLabel;
    private JTable detailsTable;
    private DefaultTableModel tableModel;

    private Map<String, Object[]> prescriptionInfoMap = new HashMap<>();

    public ViewActivePrescriptionPanel() {
        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // 🌟 Title moved inside center body
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Theo dõi đơn thuốc khả dụng");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(title);
        contentPanel.add(Box.createVerticalStrut(10));

        // Dropdown to select prescription
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.add(new JLabel("Chọn đơn thuốc:"), BorderLayout.WEST);
        prescriptionSelector = new JComboBox<>();
        prescriptionSelector.addItem("Dr. Smith - 2025-06-21");
        prescriptionSelector.addItem("Dr. Johnson - 2025-06-15");
        topPanel.add(prescriptionSelector, BorderLayout.CENTER);
        contentPanel.add(topPanel);
        contentPanel.add(Box.createVerticalStrut(10));

        // Diagnosis info
        doctorLabel = new JLabel("Bác sĩ: ");
        diagnosisLabel = new JLabel("Chẩn đoán: ");
        issueDateLabel = new JLabel("Ngày mắc bệnh: ");
        notesLabel = new JLabel("Ghi chú: ");
        contentPanel.add(doctorLabel);
        contentPanel.add(diagnosisLabel);
        contentPanel.add(issueDateLabel);
        contentPanel.add(notesLabel);
        contentPanel.add(Box.createVerticalStrut(10));

        add(contentPanel, BorderLayout.NORTH);

        // Table section
        String[] columns = {"Thuốc", "Liều lượng", "Tần suất sử dụng", "Số lượng", "Lặp lại", "Ghi chú"};
        tableModel = new DefaultTableModel(columns, 0);
        detailsTable = new JTable(tableModel);
        detailsTable.setRowHeight(26);
        JScrollPane scrollPane = new JScrollPane(detailsTable);
        add(scrollPane, BorderLayout.CENTER);

        // Mock data
        loadMockData();
        prescriptionSelector.addActionListener(e -> onPrescriptionSelected());
        prescriptionSelector.setSelectedIndex(0);
    }

    private void loadMockData() {
        prescriptionInfoMap.put("Dr. Smith - 2025-06-21", new Object[]{
                "Dr. Smith", "Hypertension", "2025-06-21", "Monitor blood pressure regularly",
                new Object[][]{
                        {"Amlodipine", "5mg", "1/day", "30", "30 days", ""},
                        {"Lisinopril", "10mg", "1/day", "30", "30 days", "Take with food"}
                }
        });

        prescriptionInfoMap.put("Dr. Johnson - 2025-06-15", new Object[]{
                "Dr. Johnson", "Seasonal Allergy", "2025-06-15", "Avoid pollen exposure",
                new Object[][]{
                        {"Cetirizine", "10mg", "1/day", "15", "15 days", ""},
                        {"Fluticasone", "1 spray", "2/day", "1", "15 days", "Nasal spray"}
                }
        });
    }

    private void onPrescriptionSelected() {
        String key = (String) prescriptionSelector.getSelectedItem();
        if (key == null || !prescriptionInfoMap.containsKey(key)) return;

        Object[] info = prescriptionInfoMap.get(key);
        doctorLabel.setText("Bác sĩ: " + info[0]);
        diagnosisLabel.setText("Chẩn đoán: " + info[1]);
        issueDateLabel.setText("Ngày mắc bệnh: " + info[2]);
        notesLabel.setText("Ghi chú: " + info[3]);

        tableModel.setRowCount(0);
        Object[][] details = (Object[][]) info[4];
        for (Object[] row : details) {
            tableModel.addRow(row);
        }
    }
}


