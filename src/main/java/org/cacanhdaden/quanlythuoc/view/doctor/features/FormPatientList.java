package org.cacanhdaden.quanlythuoc.view.doctor.features;
import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.*;
import java.awt.*;

public class FormPatientList extends JPanel {
    private final JTable tblPatients;

    public FormPatientList() {
        setLayout(new BorderLayout(10,10));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel title = new JLabel("Danh sách bệnh nhân", SwingConstants.CENTER);
        title.putClientProperty(FlatClientProperties.STYLE, "font:$h1.font");
        add(title, BorderLayout.NORTH);

        String[] cols = {"Mã bệnh nhân","Họ tên","Tuổi","Giới tính","Ngày khám"};
        Object[][] data = {};
        tblPatients = new JTable(data, cols);
        tblPatients.setFillsViewportHeight(true);

        add(new JScrollPane(tblPatients), BorderLayout.CENTER);
    }
}