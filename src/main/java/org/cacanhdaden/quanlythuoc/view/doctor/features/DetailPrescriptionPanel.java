package org.cacanhdaden.quanlythuoc.view.doctor.features;

import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import java.awt.*;

public class DetailPrescriptionPanel extends JPanel{
    private final JLabel label;

    public DetailPrescriptionPanel() {
        label = createLabel("Call for Detail Prescription");
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
