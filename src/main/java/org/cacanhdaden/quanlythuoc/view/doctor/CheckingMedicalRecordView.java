package org.cacanhdaden.quanlythuoc.view.doctor;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class CheckingMedicalRecordView extends JFrame {

    public CheckingMedicalRecordView() {
        setTitle("👨‍⚕️ Patient Profile Viewer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(15, 10));
        mainPanel.setBorder(new EmptyBorder(15, 20, 15, 20));
        add(mainPanel);

        // Top: Profile Title
        JLabel title = new JLabel("Patient Profile", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(title, BorderLayout.NORTH);

        // Center: Profile Info
        JPanel profilePanel = new JPanel(new GridBagLayout());
        profilePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "🧍 Patient Information",
                TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14)
        ));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        String[][] info = {
                {"Full Name:", "John Doe"},
                {"Date of Birth:", "1990-03-25"},
                {"Gender:", "Male"},
                {"Phone:", "0123456789"},
                {"Email:", "john.doe@example.com"},
                {"Address:", "123 Main Street, District 1, HCMC"},
        };

        for (int i = 0; i < info.length; i++) {
            gbc.gridx = 0; gbc.gridy = i;
            profilePanel.add(new JLabel(info[i][0]), gbc);
            gbc.gridx = 1;
            profilePanel.add(new JLabel(info[i][1]), gbc);
        }

        mainPanel.add(profilePanel, BorderLayout.CENTER);

        // Bottom: Medical History
        JPanel historyPanel = new JPanel(new BorderLayout());
        historyPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "📜 Medical History",
                TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14)
        ));

        JTextArea txtHistory = new JTextArea(
                "- 2023-06: Diagnosed with hypertension\n" +
                        "- 2024-01: Recovered from flu\n" +
                        "- 2025-03: Treated for stomach pain"
        );
        txtHistory.setEditable(false);
        txtHistory.setFont(new Font("Monospaced", Font.PLAIN, 13));
        txtHistory.setLineWrap(true);
        txtHistory.setWrapStyleWord(true);

        JScrollPane historyScroll = new JScrollPane(txtHistory);
        historyPanel.add(historyScroll, BorderLayout.CENTER);
        historyPanel.setPreferredSize(new Dimension(550, 130));

        mainPanel.add(historyPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CheckingMedicalRecordView::new);
    }
}
