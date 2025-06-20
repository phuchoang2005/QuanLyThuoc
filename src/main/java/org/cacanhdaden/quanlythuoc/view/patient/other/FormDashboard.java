package org.cacanhdaden.quanlythuoc.view.patient.other;

import javax.swing.*;
import java.awt.*;
import com.formdev.flatlaf.FlatClientProperties;
import raven.toast.Notifications;

public class FormDashboard extends JPanel {

    private final JLabel lb;
    private final JButton btnShowNotification;

    public FormDashboard() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title label
        lb = new JLabel("Dashboard", SwingConstants.CENTER);
        lb.setAlignmentX(Component.CENTER_ALIGNMENT);
        lb.putClientProperty(FlatClientProperties.STYLE, "font:$h1.font");

        // Button
        btnShowNotification = new JButton("Show Notifications Test");
        btnShowNotification.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnShowNotification.addActionListener(e -> showNotification());

        // Add components with spacing
        add(lb);
        add(Box.createVerticalStrut(150));
        add(btnShowNotification);
    }

    private void showNotification() {
        Notifications.getInstance().show(
                Notifications.Type.INFO,
                Notifications.Location.TOP_CENTER,
                "Hello sample message"
        );
    }
}
