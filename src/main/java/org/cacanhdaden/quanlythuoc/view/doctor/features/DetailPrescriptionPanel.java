package org.cacanhdaden.quanlythuoc.view.doctor.features;

import com.formdev.flatlaf.FlatClientProperties;
import lombok.Data;
import lombok.Getter;
import org.cacanhdaden.quanlythuoc.view.authentication.Launch;

import javax.swing.*;
import java.awt.*;
@Data
public class DetailPrescriptionPanel extends JPanel {
    // Getter cho table để controller có thể truy cập
    @Getter
    private final JTable tblDetails;
    private final JButton btnBack;
    private final long prescriptionId;

    public DetailPrescriptionPanel(long prescriptionId) {
        this.prescriptionId = prescriptionId;

        setLayout(new BorderLayout(10,10));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel title = new JLabel("Chi tiết đơn thuốc #" + prescriptionId, SwingConstants.CENTER);
        title.putClientProperty(FlatClientProperties.STYLE, "font:$h1.font");
        add(title, BorderLayout.NORTH);

        String[] cols = {"Mã thuốc","Tên thuốc","Liều lượng","Tần suất","Số lượng","Thời gian","Ghi chú"};
        Object[][] data = {};
        tblDetails = new JTable(data, cols);
        tblDetails.setFillsViewportHeight(true);
        add(new JScrollPane(tblDetails), BorderLayout.CENTER);

        JPanel bot = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnBack = new JButton("Quay lại");
        btnBack.addActionListener(e -> Launch.showForm(new FormManagePrescription()));
        bot.add(btnBack);
        add(bot, BorderLayout.SOUTH);
    }

    public long getPrescriptionId() {
        return prescriptionId;
    }
}