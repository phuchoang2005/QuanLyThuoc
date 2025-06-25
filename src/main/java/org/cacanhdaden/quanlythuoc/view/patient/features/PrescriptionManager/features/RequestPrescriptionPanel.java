package org.cacanhdaden.quanlythuoc.view.patient.features.PrescriptionManager.features;
import lombok.Getter;
import net.miginfocom.swing.MigLayout;
import org.cacanhdaden.quanlythuoc.control.patient.PrescriptionManagerController.RequestPrescriptionController;
import org.cacanhdaden.quanlythuoc.model.dto.LoadDoctorDTO;
import org.cacanhdaden.quanlythuoc.model.model.Users;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
@Getter
public class RequestPrescriptionPanel extends JPanel {
    private final JComboBox<LoadDoctorDTO> doctorComboBox;
    private final JTextArea reasonTextArea;
    private final JButton submitButton;
    private final JLabel patientIdLabel;
    private final JLabel feedbackLabel;
    private final JTextField patientIdTextField;
    public RequestPrescriptionPanel() {
        // Set layout
        setLayout(new MigLayout("wrap 2, insets 30 80 30 80", "[right]20[grow,fill]", "[]15[]"));

        // Add Title
        JLabel titleLabel = new JLabel("Yêu cầu đơn thuốc mới");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, "span, align center, wrap");

        patientIdLabel = new JLabel("Nhập vào mã bệnh nhân:");
        patientIdTextField = new JTextField();
        add(patientIdLabel);
        add(patientIdTextField, "growx, wrap");

        // Doctor selection
        JLabel doctorLabel = new JLabel("Chọn bác sĩ:");
        add(doctorLabel);

        doctorComboBox = new JComboBox<>();

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
        add(submitButton, "span, align center, wrap");

        // Feedback label
        feedbackLabel = new JLabel("");
        feedbackLabel.setForeground(Color.RED);
        add(feedbackLabel, "span, align center");

        new RequestPrescriptionController(this);
    }

}
