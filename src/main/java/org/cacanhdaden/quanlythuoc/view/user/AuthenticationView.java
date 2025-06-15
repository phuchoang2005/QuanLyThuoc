package org.cacanhdaden.quanlythuoc.view.user;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;

public class AuthenticationView extends JFrame {

    public AuthenticationView() {
        setTitle("Ứng dụng quản lý dược phẩm cá nhân");
        setSize(500, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Tiêu đề
        JLabel lblAppTitle = new JLabel("ỨNG DỤNG QUẢN LÝ DƯỢC PHẨM CÁ NHÂN", SwingConstants.CENTER);
        lblAppTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblAppTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblAppTitle);

        JLabel lblSubTitle = new JLabel("Đăng nhập tài khoản", SwingConstants.CENTER);
        lblSubTitle.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblSubTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(lblSubTitle);

        // Biểu mẫu đăng nhập
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField txtUsername = new JTextField(20);
        JPasswordField txtPassword = new JPasswordField(20);

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
        JPanel optionPanel = new JPanel(new GridLayout(2, 2, 10, 5));
        optionPanel.setOpaque(false);

        optionPanel.add(new JLabel("Quên mật khẩu"));
        JButton btnForgot = new JButton("Nhấn vào đây");
        optionPanel.add(btnForgot);

        optionPanel.add(new JLabel("Chưa có tài khoản?"));
        JButton btnRegister = new JButton("Nhấn vào đây");
        optionPanel.add(btnRegister);

        mainPanel.add(optionPanel);

        // Nút đăng nhập
        mainPanel.add(Box.createVerticalStrut(20));
        JButton btnLogin = new JButton("ĐĂNG NHẬP");
        btnLogin.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnLogin.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        mainPanel.add(btnLogin);

        add(mainPanel);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new AuthenticationView().setVisible(true));
    }
}

