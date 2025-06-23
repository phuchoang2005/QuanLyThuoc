package org.cacanhdaden.quanlythuoc.view.doctor.features;
import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.*;
import java.awt.*;

public class FormManagePrescription extends JPanel {
    private final JTable tblPrescriptions;
    private final JButton btnAdd, btnEdit, btnDelete;

    public FormManagePrescription() {
        setLayout(new BorderLayout(10,10));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel title = new JLabel("Quản lý đơn thuốc", SwingConstants.CENTER);
        title.putClientProperty(FlatClientProperties.STYLE, "font:$h1.font");
        add(title, BorderLayout.NORTH);

        String[] cols = {"Mã đơn","Mã bệnh nhân","Ngày tạo","Trạng thái"};
        Object[][] data = {};
        tblPrescriptions = new JTable(data, cols);
        tblPrescriptions.setFillsViewportHeight(true);
        add(new JScrollPane(tblPrescriptions), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,0));
        btnAdd = new JButton("Thêm");
        btnEdit = new JButton("Sửa");
        btnDelete = new JButton("Xóa");
        btnPanel.add(btnAdd);
        btnPanel.add(btnEdit);
        btnPanel.add(btnDelete);
        add(btnPanel, BorderLayout.SOUTH);
    }
}