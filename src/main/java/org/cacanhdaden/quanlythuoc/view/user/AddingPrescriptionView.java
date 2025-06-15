package org.cacanhdaden.quanlythuoc.view.user;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;

public class AddingPrescriptionView extends JFrame {

    public AddingPrescriptionView() {
        setTitle("Thêm đơn thuốc mới");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        JButton btnCancel = new JButton("Hủy");
        JLabel title = new JLabel("Thêm đơn thuốc mới", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        JButton btnAdd = new JButton("Thêm");

        headerPanel.add(btnCancel, BorderLayout.WEST);
        headerPanel.add(title, BorderLayout.CENTER);
        headerPanel.add(btnAdd, BorderLayout.EAST);
        mainPanel.add(headerPanel);

        mainPanel.add(Box.createVerticalStrut(15));

        // Form grid
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;
        addRow(formPanel, gbc, y++, "Tên:", new JTextField(20));
        addRow(formPanel, gbc, y++, "Đơn vị:", new JTextField(20));
        addRow(formPanel, gbc, y++, "Ghi chú:", new JTextField(20));
        addRow(formPanel, gbc, y++, "Tần suất sử dụng thuốc:", new JComboBox<>(new String[]{"Mỗi ngày", "Cách ngày", "Tuần"}));
        addRow(formPanel, gbc, y++, "Đặt mức độ ưu tiên thông báo cao hơn:", new JTextField("Y/N", 5));
        addRow(formPanel, gbc, y++, "Tự động xác nhận sử dụng thuốc:", new JTextField("Y/N", 5));
        addRow(formPanel, gbc, y++, "Khoảng thời gian lặp thông báo:", new JSpinner(new SpinnerNumberModel(5, 1, 24, 1)));

        // Quản lý số lượng thuốc
        JLabel label = new JLabel("Quản lý số lượng thuốc:");
        JPanel amountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        amountPanel.add(new JLabel("Hiện có"));
        amountPanel.add(new JSpinner(new SpinnerNumberModel(1, 0, 100, 1)));
        amountPanel.add(new JLabel("Nhắc nhở"));
        amountPanel.add(new JSpinner(new SpinnerNumberModel(1, 0, 100, 1)));

        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        formPanel.add(label, gbc);

        gbc.gridx = 1;
        gbc.gridy = y++;
        formPanel.add(amountPanel, gbc);

        // Thêm liều
        JPanel addDosePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        addDosePanel.add(new JButton("Thêm"));
        addDosePanel.add(new JSpinner(new SpinnerNumberModel(0, 0, 12, 1)));
        addDosePanel.add(new JLabel(":"));
        addDosePanel.add(new JSpinner(new SpinnerNumberModel(0, 0, 59, 1)));
        addDosePanel.add(new JComboBox<>(new String[]{"AM", "PM"}));
        addDosePanel.add(new JLabel("Liều sử dụng"));
        addDosePanel.add(new JSpinner(new SpinnerNumberModel(1, 1, 10, 1)));

        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 2;
        formPanel.add(addDosePanel, gbc);

        mainPanel.add(formPanel);
        add(mainPanel);
    }

    private void addRow(JPanel panel, GridBagConstraints gbc, int y, String label, JComponent comp) {
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.gridy = y;
        panel.add(comp, gbc);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new AddingPrescriptionView().setVisible(true));
    }
}

