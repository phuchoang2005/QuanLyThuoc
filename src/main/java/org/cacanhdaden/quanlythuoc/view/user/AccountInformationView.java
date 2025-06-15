package org.cacanhdaden.quanlythuoc.view.user;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;

public class AccountInformationView extends JFrame {

    public AccountInformationView() {
        setTitle("Tài Khoản");
        setSize(500, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("TÀI KHOẢN");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(20));

        // Form fields
        mainPanel.add(createFormRow("Tên tài khoản:", new JTextField(20)));
        mainPanel.add(createFormRow("Họ tên:", new JTextField(20)));
        mainPanel.add(createFormRow("Email:", new JTextField(20)));

        JSpinner ageSpinner = new JSpinner(new SpinnerNumberModel(15, 1, 120, 1));
        mainPanel.add(createFormRow("Tuổi:", ageSpinner));

        JComboBox<String> regionBox = new JComboBox<>(new String[]{"Miền Bắc", "Miền Trung", "Miền Nam"});
        mainPanel.add(createFormRow("Khu vực sinh sống:", regionBox));

        // Medical history
        JLabel historyLabel = new JLabel("Tiền sử bệnh lý:");
        JTextArea historyArea = new JTextArea(4, 20);
        historyArea.setLineWrap(true);
        historyArea.setWrapStyleWord(true);
        JScrollPane historyScroll = new JScrollPane(historyArea);

        JPanel historyPanel = new JPanel(new BorderLayout());
        historyPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        historyPanel.add(historyLabel, BorderLayout.NORTH);
        historyPanel.add(historyScroll, BorderLayout.CENTER);
        mainPanel.add(historyPanel);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        JButton updateButton = new JButton("Cập nhật");
        JButton backButton = new JButton("Trở về");

        buttonPanel.add(updateButton);
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel);

        add(mainPanel);
    }

    private JPanel createFormRow(String labelText, JComponent inputComponent) {
        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(130, 25));

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        panel.add(label);
        panel.add(inputComponent);

        return panel;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new AccountInformationView().setVisible(true));
    }
}
