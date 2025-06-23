package org.cacanhdaden.quanlythuoc.view.doctor.features;
import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.*;
import java.awt.*;

public class FormMedicalRecord extends JPanel {
    private final JTextArea txtRecord;

    public FormMedicalRecord() {
        setLayout(new BorderLayout(10,10));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel title = new JLabel("Hồ sơ bệnh án", SwingConstants.CENTER);
        title.putClientProperty(FlatClientProperties.STYLE, "font:$h1.font");
        add(title, BorderLayout.NORTH);

        txtRecord = new JTextArea();
        txtRecord.setEditable(false);
        txtRecord.setLineWrap(true);
        txtRecord.setWrapStyleWord(true);
        add(new JScrollPane(txtRecord), BorderLayout.CENTER);
    }

    /** Gán nội dung hồ sơ để hiển thị */
    public void setMedicalRecordText(String record) {
        txtRecord.setText(record);
        txtRecord.setCaretPosition(0);
    }
}