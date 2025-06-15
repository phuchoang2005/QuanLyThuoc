package org.cacanhdaden.quanlythuoc.view.user;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;

public class CreateAccountView extends JFrame {

    public CreateAccountView() {
        setTitle("Tạo tài khoản");
        setSize(500, 520);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel lblTitle = new JLabel("TẠO TÀI KHOẢN");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblTitle);
        mainPanel.add(Box.createVerticalStrut(20));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField txtUsername = new JTextField(20);
        JPasswordField txtPassword = new JPasswordField(20);
        JPasswordField txtConfirmPassword = new JPasswordField(20);
        JTextField txtFullName = new JTextField(20);
        JTextField txtEmail = new JTextField(20);
        JSpinner spnAge = new JSpinner(new SpinnerNumberModel(18, 1, 120, 1));
        JTextArea txtMedicalHistory = new JTextArea(3, 20);
        txtMedicalHistory.setLineWrap(true);
        txtMedicalHistory.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(txtMedicalHistory);
        String[] regions = {"Miền Bắc", "Miền Trung", "Miền Nam"};
        JComboBox<String> cbRegion = new JComboBox<>(regions);

        int row = 0;

        // Các dòng nhập
        addRow(formPanel, gbc, row++, "Tên tài khoản:", txtUsername);
        addRow(formPanel, gbc, row++, "Mật khẩu:", txtPassword);
        addRow(formPanel, gbc, row++, "Xác nhận mật khẩu:", txtConfirmPassword);
        addRow(formPanel, gbc, row++, "Họ tên:", txtFullName);
        addRow(formPanel, gbc, row++, "Email:", txtEmail);
        addRow(formPanel, gbc, row++, "Tuổi:", spnAge);
        addRow(formPanel, gbc, row++, "Tiền sử bệnh lý:", scrollPane);
        addRow(formPanel, gbc, row++, "Khu vực sinh sống:", cbRegion);

        mainPanel.add(formPanel);

        // Nút
        JPanel buttonPanel = new JPanel();
        JButton btnBack = new JButton("Trở lại");
        JButton btnSubmit = new JButton("Xác nhận");
        buttonPanel.add(btnBack);
        buttonPanel.add(btnSubmit);

        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(buttonPanel);

        add(mainPanel);
    }

    private void addRow(JPanel panel, GridBagConstraints gbc, int row, String label, Component input) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel.add(input, gbc);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new CreateAccountView().setVisible(true));
    }
}

