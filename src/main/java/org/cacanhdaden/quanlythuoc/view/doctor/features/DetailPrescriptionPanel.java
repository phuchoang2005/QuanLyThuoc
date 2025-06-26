package org.cacanhdaden.quanlythuoc.view.doctor.features;

import com.formdev.flatlaf.FlatClientProperties;
import lombok.Data;
import org.cacanhdaden.quanlythuoc.model.dao.PrescriptionDetailDAO;
import org.cacanhdaden.quanlythuoc.model.model.PrescriptionDetail;
import org.cacanhdaden.quanlythuoc.view.authentication.Launch;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Data
public class DetailPrescriptionPanel extends JPanel {
    private static final int PANEL_PADDING = 10;
    private static final int COLUMN_COUNT = 7;
    private static final String[] COLUMN_HEADERS = {
            "Mã thuốc", "Tên thuốc", "Liều lượng", "Tần suất",
            "Số lượng", "Thời gian", "Ghi chú"
    };

    private JTable tblDetails;
    private JButton btnBack;
    private final PrescriptionDetailDAO detailDAO;
    private final long prescriptionId;

    // Constructor chính - chỉ nhận prescriptionId
    public DetailPrescriptionPanel(long prescriptionId) {
        this.prescriptionId = prescriptionId;
        this.detailDAO = new PrescriptionDetailDAO();

        initializeComponents();
        initializeLayout();
        createTitlePanel();
        createTablePanel();
        createBottomPanel();
        loadPrescriptionDetails();
    }

    private void initializeComponents() {
        this.tblDetails = new JTable();
        this.btnBack = new JButton("Quay lại");
    }

    private void initializeLayout() {
        setLayout(new BorderLayout(PANEL_PADDING, PANEL_PADDING));
        setBorder(BorderFactory.createEmptyBorder(PANEL_PADDING, PANEL_PADDING,
                PANEL_PADDING, PANEL_PADDING));
    }

    private void createTitlePanel() {
        JLabel title = new JLabel("Chi tiết đơn thuốc #" + prescriptionId, SwingConstants.CENTER);
        title.putClientProperty(FlatClientProperties.STYLE, "font:$h1.font");
        add(title, BorderLayout.NORTH);
    }

    private void createTablePanel() {
        tblDetails.setFillsViewportHeight(true);
        add(new JScrollPane(tblDetails), BorderLayout.CENTER);
    }

    private void createBottomPanel() {
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnBack.addActionListener(e -> Launch.showForm(new FormManagePrescription()));
        bottomPanel.add(btnBack);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void loadPrescriptionDetails() {
        List<PrescriptionDetail> details = detailDAO.getDetailsByPrescriptionId(prescriptionId);
        Object[][] tableData = createTableData(details);
        tblDetails.setModel(new javax.swing.table.DefaultTableModel(tableData, COLUMN_HEADERS));
    }

    private Object[][] createTableData(List<PrescriptionDetail> details) {
        Object[][] data = new Object[details.size()][COLUMN_COUNT];

        for (int i = 0; i < details.size(); i++) {
            PrescriptionDetail detail = details.get(i);
            data[i][0] = detail.getMedicineId();
            data[i][1] = detail.getMedicineName();
            data[i][2] = detail.getDosage();
            data[i][3] = detail.getFrequency();
            data[i][4] = detail.getQuantity();
            data[i][5] = detail.getDuration();
            data[i][6] = detail.getNotes();
        }

        return data;
    }
}