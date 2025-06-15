package org.cacanhdaden.quanlythuoc.view.login;

import javax.swing.*;
import java.awt.*;
public class CreateAccountView extends JFrame {
    private JPanel mainPanel = new JPanel();
    private JLabel lblTitle = new JLabel("TẠO TÀI KHOẢN");
    private JPanel formPanel = new JPanel(new GridBagLayout());
    private JTextField txtUsername = new JTextField(20);
    private JPasswordField txtPassword = new JPasswordField(20);
    private JPasswordField txtConfirmPassword = new JPasswordField(20);
    private JTextField txtFullName = new JTextField(20);
    private JTextField txtEmail = new JTextField(20);
    private JSpinner spnAge = new JSpinner(new SpinnerNumberModel(18, 1, 120, 1));
    private JTextArea txtMedicalHistory = new JTextArea(3, 20);
    private JPanel buttonPanel = new JPanel();
    private JButton btnBack = new JButton("Trở lại");
    private JButton btnSubmit = new JButton("Xác nhận");
    private JScrollPane scrollPane = new JScrollPane(txtMedicalHistory);
    private GridBagConstraints gbc = new GridBagConstraints();

    public CreateAccountView() {
        setTitle("Tạo tài khoản");
        setSize(500, 520);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblTitle);
        mainPanel.add(Box.createVerticalStrut(20));

        formPanel.setOpaque(false);
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        txtMedicalHistory.setLineWrap(true);
        txtMedicalHistory.setWrapStyleWord(true);
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
    public JButton getBtnBack(){
        return btnBack;
    }
    public JButton getBtnSubmit(){
        return btnSubmit;
    }
}

