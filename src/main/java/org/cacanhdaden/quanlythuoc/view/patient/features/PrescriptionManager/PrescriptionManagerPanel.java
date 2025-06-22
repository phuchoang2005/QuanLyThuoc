package org.cacanhdaden.quanlythuoc.view.patient.features.PrescriptionManager;

import org.cacanhdaden.quanlythuoc.view.patient.features.PrescriptionManager.features.RequestPrescriptionPanel;
import org.cacanhdaden.quanlythuoc.view.patient.features.PrescriptionManager.features.TrackPrescriptionRequestPanel;
import org.cacanhdaden.quanlythuoc.view.patient.features.PrescriptionManager.features.ViewActivePrescriptionPanel;

import javax.swing.*;
public class PrescriptionManagerPanel extends JPanel {
    private final JTabbedPane  tabs = new JTabbedPane();
    private final RequestPrescriptionPanel requestPanel = new RequestPrescriptionPanel();
    private final TrackPrescriptionRequestPanel trackPanel = new TrackPrescriptionRequestPanel();
    private final ViewActivePrescriptionPanel activePanel = new ViewActivePrescriptionPanel();
    public  PrescriptionManagerPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        tabs.addTab("Yêu cầu đơn thuốc mới",this.requestPanel);
        tabs.addTab("Theo dõi tình trạng yêu cầu đơn thuốc",this.trackPanel);
        tabs.addTab("Theo dõi đơn thuốc khả dụng",this.activePanel);

        add(tabs);
    }
}
