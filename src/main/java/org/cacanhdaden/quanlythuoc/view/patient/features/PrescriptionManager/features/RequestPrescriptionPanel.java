package org.cacanhdaden.quanlythuoc.view.patient.features.PrescriptionManager.features;
import lombok.Getter;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
@Getter
public class RequestPrescriptionPanel extends JPanel {
    private final JComboBox<String> doctorComboBox;
    private final JTextArea reasonTextArea;
    private final JButton submitButton;
    private final JLabel feedbackLabel;

    public RequestPrescriptionPanel() {
        // Set layout
        setLayout(new MigLayout("wrap 2, insets 30 80 30 80", "[right]20[grow,fill]", "[]15[]"));

        // Add Title
        JLabel titleLabel = new JLabel("Yêu cầu đơn thuốc mới");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, "span, align center, wrap");

        // Doctor selection
        JLabel doctorLabel = new JLabel("Chọn bác sĩ:");
        add(doctorLabel);

        doctorComboBox = new JComboBox<>();
        doctorComboBox.addItem("Dr. Smith");
        doctorComboBox.addItem("Dr. Johnson");
        doctorComboBox.addItem("Dr. Brown");
        add(doctorComboBox, "growx, wrap");

        // Reason input
        JLabel reasonLabel = new JLabel("Lý do yêu cầu đơn thuốc:");
        add(reasonLabel);

        reasonTextArea = new JTextArea(4, 20);
        reasonTextArea.setLineWrap(true);
        reasonTextArea.setWrapStyleWord(true);
        JScrollPane reasonScrollPane = new JScrollPane(reasonTextArea);
        add(reasonScrollPane, "span, growx, wrap");

        // Submit button
        submitButton = new JButton("Gửi yêu cầu");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitPrescriptionRequest();
            }
        });
        add(submitButton, "span, align center, wrap");

        // Feedback label
        feedbackLabel = new JLabel("");
        feedbackLabel.setForeground(Color.RED);
        add(feedbackLabel, "span, align center");
    }

    private void submitPrescriptionRequest() {
        String selectedDoctor = (String) doctorComboBox.getSelectedItem();
        String reason = reasonTextArea.getText().trim();

        if (reason.isEmpty()) {
            feedbackLabel.setText("Yêu cầu không thể rỗng");
            return;
        }

        // Placeholder logic to handle request submission
        // In reality, you would send this to your backend for processing
        feedbackLabel.setForeground(Color.GREEN);
        feedbackLabel.setText("Yêu cầu đơn thuốc đã đến tay bác sĩ");

        // Clear the fields after submission
        reasonTextArea.setText("");
        doctorComboBox.setSelectedIndex(0); // Reset doctor selection
    }
}
