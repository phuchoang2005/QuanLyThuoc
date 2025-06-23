package org.cacanhdaden.quanlythuoc.view.patient.features.PrescriptionManager;

import org.cacanhdaden.quanlythuoc.view.patient.features.PrescriptionManager.features.RequestPrescriptionPanel;

import javax.swing.*;
public class PrescriptionManagerPanel extends JPanel {
    private final RequestPrescriptionPanel requestPanel = new RequestPrescriptionPanel();
    public  PrescriptionManagerPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(this.requestPanel);
    }
}
