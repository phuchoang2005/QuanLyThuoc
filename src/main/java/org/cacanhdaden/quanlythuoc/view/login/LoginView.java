package org.cacanhdaden.quanlythuoc.view.login;


import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class LoginView extends JFrame {
    private final JPanel mainPanel = new JPanel();
    private final JLabel lblSubTitle = new JLabel("Đăng nhập tài khoản", SwingConstants.CENTER);
    private final JPanel formPanel = new JPanel(new GridBagLayout());
    private final JTextField txtUsername = new JTextField(20);
    private final JPasswordField txtPassword = new JPasswordField(20);
    private final JPanel optionPanel = new JPanel(new GridLayout(2, 2, 10, 5));
    private final JButton btnForgot = new JButton("Nhấn vào đây");
    private final JButton btnRegister = new JButton("Nhấn vào đây");
    private final JButton btnLogin = new JButton("ĐĂNG NHẬP");
    private final GridBagConstraints gbc = new GridBagConstraints();
    private final JLabel lblAppTitle = new JLabel("ỨNG DỤNG QUẢN LÝ DƯỢC PHẨM CÁ NHÂN", SwingConstants.CENTER);

    public LoginView() {
        setTitle("Ứng dụng quản lý dược phẩm cá nhân");
        setSize(500, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Tiêu đề
        lblAppTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblAppTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblAppTitle);

        lblSubTitle.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblSubTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(lblSubTitle);

        // Biểu mẫu đăng nhập
        formPanel.setOpaque(false);
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Tên đăng nhập:"), gbc);

        gbc.gridx = 1;
        formPanel.add(txtUsername, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Mật khẩu:"), gbc);

        gbc.gridx = 1;
        formPanel.add(txtPassword, gbc);

        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(formPanel);

        // Quên mật khẩu & tạo tài khoản
        optionPanel.setOpaque(false);

        optionPanel.add(new JLabel("Quên mật khẩu"));
        optionPanel.add(btnForgot);

        optionPanel.add(new JLabel("Chưa có tài khoản?"));
        optionPanel.add(btnRegister);

        mainPanel.add(optionPanel);

        // Nút đăng nhập
        mainPanel.add(Box.createVerticalStrut(20));
        btnLogin.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnLogin.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        mainPanel.add(btnLogin);

        add(mainPanel);
    }
}

