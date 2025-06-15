package org.cacanhdaden.quanlythuoc.view.user;

import com.formdev.flatlaf.FlatIntelliJLaf;
import javax.swing.*;
import java.awt.*;

public class DashboardView extends JFrame {

    public DashboardView() {
        setTitle("Quản lý dược phẩm cá nhân");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        JTabbedPane tabs = new JTabbedPane();

        tabs.addTab("Hôm nay", createTodayPanel());
        tabs.addTab("Danh mục thuốc", new JLabel("Chưa có nội dung"));
        tabs.addTab("Lịch sử", new JLabel("Chưa có nội dung"));
        tabs.addTab("Tra cứu trực tuyến", new JLabel("Chưa có nội dung"));

        // Nút chức năng phía dưới
        JButton btnSkip = new JButton("Bỏ qua");
        JButton btnUseOnce = new JButton("Sử dụng 1 lần");
        JButton btnConfirm = new JButton("Xác nhận");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        buttonPanel.add(btnSkip);
        buttonPanel.add(btnUseOnce);
        buttonPanel.add(btnConfirm);

        // Layout chính
        setLayout(new BorderLayout());
        add(tabs, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createTodayPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Lịch sử dụng thuốc hôm nay");
        title.setFont(new Font("SansSerif", Font.BOLD, 18));

        JTextArea area = new JTextArea("- Paracetamol: 8h sáng\n- Vitamin C: 12h trưa\n- Thuốc ho: 20h tối");
        area.setFont(new Font("Monospaced", Font.PLAIN, 14));
        area.setEditable(false);

        panel.add(title, BorderLayout.NORTH);
        panel.add(new JScrollPane(area), BorderLayout.CENTER);
        return panel;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new DashboardView().setVisible(true));
    }
}

