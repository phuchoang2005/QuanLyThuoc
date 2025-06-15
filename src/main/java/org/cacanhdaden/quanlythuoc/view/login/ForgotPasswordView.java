package org.cacanhdaden.quanlythuoc.view.login;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;

public class ForgotPasswordView extends JFrame {

    public ForgotPasswordView() {
        setTitle("Đặt lại mật khẩu");
        setSize(450, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel lblTitle = new JLabel("ĐẶT LẠI MẬT KHẨU");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblTitle);
        mainPanel.add(Box.createVerticalStrut(20));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField txtUsername = new JTextField(20);
        JTextField txtEmail = new JTextField(20);
        JPasswordField txtNewPassword = new JPasswordField(20);
        JPasswordField txtConfirmPassword = new JPasswordField(20);

        int row = 0;
        addRow(formPanel, gbc, row++, "Tên tài khoản:", txtUsername);
        addRow(formPanel, gbc, row++, "Email:", txtEmail);
        addRow(formPanel, gbc, row++, "Mật khẩu mới:", txtNewPassword);
        addRow(formPanel, gbc, row++, "Xác nhận mật khẩu:", txtConfirmPassword);

        mainPanel.add(formPanel);
        mainPanel.add(Box.createVerticalStrut(20));

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        JButton btnBack = new JButton("Trở về");
        JButton btnSubmit = new JButton("Hoàn tất");
        btnPanel.add(btnBack);
        btnPanel.add(btnSubmit);

        mainPanel.add(btnPanel);

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
}

