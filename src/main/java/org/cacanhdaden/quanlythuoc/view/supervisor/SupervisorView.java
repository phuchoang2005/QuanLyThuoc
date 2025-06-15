package org.cacanhdaden.quanlythuoc.view.supervisor;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SupervisorView extends JFrame {

    public SupervisorView() {
        setTitle("Quản lý dược phẩm cá nhân");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        // Set layout chính
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Tabs chức năng
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Thống kê thông tin người dùng", new JPanel());
        tabbedPane.addTab("Thống kê thói quen người dùng", new JPanel());
        tabbedPane.addTab("Tra cứu thông tin người dùng", new JPanel());
        tabbedPane.addTab("Danh sách phản hồi", new JPanel());
        mainPanel.add(tabbedPane, BorderLayout.NORTH);

        // Bảng bên trái
        String[] columnNames = {"Họ và tên", "Tiêu chí thống kê"};
        JTable table = new JTable(new DefaultTableModel(columnNames, 0));
        JScrollPane tableScrollPane = new JScrollPane(table);

        // Panel biểu đồ bên phải (placeholder)
        JPanel chartPanel = new JPanel();
        chartPanel.setBackground(Color.LIGHT_GRAY);
        chartPanel.setPreferredSize(new Dimension(400, 400));
        chartPanel.setBorder(BorderFactory.createTitledBorder("Biểu đồ"));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tableScrollPane, chartPanel);
        splitPane.setResizeWeight(0.5);
        mainPanel.add(splitPane, BorderLayout.CENTER);

        // Panel chọn biểu đồ
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JComboBox<String> chartTypeCombo = new JComboBox<>(new String[]{"Biểu đồ cột", "Biểu đồ tròn"});
        JComboBox<String> criteriaCombo = new JComboBox<>(new String[]{"Tuổi", "Giới tính", "Thuốc đã dùng"});

        controlPanel.add(new JLabel("Loại biểu đồ:"));
        controlPanel.add(chartTypeCombo);
        controlPanel.add(new JLabel("Tiêu chí thống kê:"));
        controlPanel.add(criteriaCombo);

        JButton okButton = new JButton("OK");
        controlPanel.add(okButton);

        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf()); // Cần thư viện FlatLaf
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new SupervisorView().setVisible(true);
        });
    }
}
