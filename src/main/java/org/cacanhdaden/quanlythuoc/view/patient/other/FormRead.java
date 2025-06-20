package org.cacanhdaden.quanlythuoc.view.patient.other;

import javax.swing.*;
import java.awt.*;
import com.formdev.flatlaf.FlatClientProperties;

public class FormRead extends JPanel {

    private final JLabel label;

    public FormRead() {
        label = createLabel("Read");
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
