package org.cacanhdaden.quanlythuoc.view.patient.features;

import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import java.awt.*;

public class RequestPrescriptionPanel extends JPanel {
    private final JLabel label;

    public RequestPrescriptionPanel() {
        label = createLabel("Xin chào tất cả các bạn, mình là Jenny Huỳnh");
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(label, BorderLayout.CENTER);
    }

    private JLabel createLabel(String text) {
        JLabel lb = new JLabel(text, SwingConstants.CENTER);
        lb.putClientProperty(FlatClientProperties.STYLE, "font:$h1.font");
        return lb;
    }
}
