package org.cacanhdaden.quanlythuoc.view.doctor.features;

import com.formdev.flatlaf.FlatClientProperties;
import lombok.Data;
import org.cacanhdaden.quanlythuoc.control.doctor.PrescriptionManagerController;

import javax.swing.*;
import java.awt.*;
@Data
public class FormManagePrescription extends JPanel {
    // Getter cho các thành phần UI để controller có thể truy cập
    private final JTable tblPrescriptions;
    private final JButton btnAdd, btnEdit, btnDelete, btnView;
    private PrescriptionManagerController controller;

    public FormManagePrescription() {
        setLayout(new BorderLayout(10,10));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel title = new JLabel("Quản lý đơn thuốc", SwingConstants.CENTER);
        title.putClientProperty(FlatClientProperties.STYLE, "font:$h1.font");
        add(title, BorderLayout.NORTH);

        String[] cols = {"Mã đơn","Mã bệnh nhân","Tên bệnh nhân","Ngày tạo","Trạng thái"};
        Object[][] data = {};
        tblPrescriptions = new JTable(data, cols);
        tblPrescriptions.setFillsViewportHeight(true);
        add(new JScrollPane(tblPrescriptions), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,0));
        btnAdd    = new JButton("Thêm");
        btnEdit   = new JButton("Sửa");
        btnDelete = new JButton("Xóa");
        btnView   = new JButton("Xem thông tin");

        btnPanel.add(btnAdd);
        btnPanel.add(btnEdit);
        btnPanel.add(btnDelete);
        btnPanel.add(btnView);
        add(btnPanel, BorderLayout.SOUTH);

        // Khởi tạo controller (sẽ tự động đăng ký sự kiện và nạp dữ liệu)
        controller = new PrescriptionManagerController(this);
    }

}