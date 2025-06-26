package org.cacanhdaden.quanlythuoc.view.doctor.features;

import com.formdev.flatlaf.FlatClientProperties;
import org.cacanhdaden.quanlythuoc.model.dao.MedicalRecordDAO;
import org.cacanhdaden.quanlythuoc.view.authentication.Launch;

import javax.swing.*;
import java.awt.*;

public class FormMedicalRecord extends JPanel {
    private final JTextArea txtRecord;
    private final JButton btnBack;
    private final MedicalRecordDAO recordDAO;

    public FormMedicalRecord(long patientId) {
        this.recordDAO = new MedicalRecordDAO();
        setLayout(new BorderLayout(10,10));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel title = new JLabel("Hồ sơ bệnh án - Bệnh nhân #" + patientId, SwingConstants.CENTER);
        title.putClientProperty(FlatClientProperties.STYLE, "font:$h1.font");
        add(title, BorderLayout.NORTH);

        txtRecord = new JTextArea();
        txtRecord.setEditable(false);
        txtRecord.setLineWrap(true);
        txtRecord.setWrapStyleWord(true);
        add(new JScrollPane(txtRecord), BorderLayout.CENTER);

        JPanel bot = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnBack = new JButton("Quay lại");
        btnBack.addActionListener(e -> Launch.showForm(new FormPatientList()));
        bot.add(btnBack);
        add(bot, BorderLayout.SOUTH);

        loadMedicalRecord(patientId);
    }

    private void loadMedicalRecord(long patientId) {
        String recordText = recordDAO.getFormattedRecordsByPatientId(patientId);
        txtRecord.setText(recordText);
        txtRecord.setCaretPosition(0);
    }
}