package org.cacanhdaden.quanlythuoc.view.doctor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AddingPrescriptionView extends JFrame {

    private JTextField txtPatientName;
    private JTextField txtMedicineName;
    private JTextField txtDosage;
    private JTextField txtDays;
    private JTable table;
    private DefaultTableModel tableModel;

    public AddingPrescriptionView() {
        setTitle("📝 Medical Prescription Form");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(15, 20, 15, 20));
        add(mainPanel);

        // Patient Info Panel
        JPanel patientPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        patientPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "👤 Patient Information", TitledBorder.LEFT, TitledBorder.TOP));
        txtPatientName = new JTextField(25);
        patientPanel.add(new JLabel("Name:"));
        patientPanel.add(txtPatientName);
        mainPanel.add(patientPanel);

        // Medicine Input Panel
        JPanel medicineInputPanel = new JPanel(new GridLayout(2, 4, 15, 5));
        medicineInputPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "💊 Add Medicine", TitledBorder.LEFT, TitledBorder.TOP));
        txtMedicineName = new JTextField();
        txtDosage = new JTextField();
        txtDays = new JTextField();
        JButton btnAdd = new JButton("➕ Add");

        medicineInputPanel.add(new JLabel("Medicine Name:"));
        medicineInputPanel.add(new JLabel("Dosage:"));
        medicineInputPanel.add(new JLabel("Days:"));
        medicineInputPanel.add(new JLabel(""));

        medicineInputPanel.add(txtMedicineName);
        medicineInputPanel.add(txtDosage);
        medicineInputPanel.add(txtDays);
        medicineInputPanel.add(btnAdd);

        mainPanel.add(medicineInputPanel);

        // Table Panel
        tableModel = new DefaultTableModel(new String[]{"Medicine", "Dosage", "Days"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "📄 Prescription List", TitledBorder.LEFT, TitledBorder.TOP));
        scrollPane.setPreferredSize(new Dimension(700, 200));
        mainPanel.add(scrollPane);

        // Save Button
        JPanel btnPanel = new JPanel();
        JButton btnSave = new JButton("💾 Save Prescription");
        btnSave.setPreferredSize(new Dimension(200, 40));
        btnPanel.add(btnSave);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(btnPanel);

        // Action listeners
        btnAdd.addActionListener(this::onAdd);
        btnSave.addActionListener(this::onSave);
    }

    private void onAdd(ActionEvent e) {
        String med = txtMedicineName.getText().trim();
        String dosage = txtDosage.getText().trim();
        String days = txtDays.getText().trim();

        if (med.isEmpty() || dosage.isEmpty() || days.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all medicine fields.", "⚠️ Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        tableModel.addRow(new Object[]{med, dosage, days});
        txtMedicineName.setText("");
        txtDosage.setText("");
        txtDays.setText("");
    }

    private void onSave(ActionEvent e) {
        String patientName = txtPatientName.getText().trim();

        if (patientName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter patient name.", "⚠️ Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (tableModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Prescription list is empty.", "⚠️ Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // In ra đơn thuốc
        System.out.println("🧾 Prescription for: " + patientName);
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String med = (String) tableModel.getValueAt(i, 0);
            String dosage = (String) tableModel.getValueAt(i, 1);
            String days = (String) tableModel.getValueAt(i, 2);
            System.out.printf("  - %s | %s | %s days%n", med, dosage, days);
        }

        JOptionPane.showMessageDialog(this, "✅ Prescription saved (see console).");

        // Reset
        txtPatientName.setText("");
        tableModel.setRowCount(0);
    }

}


