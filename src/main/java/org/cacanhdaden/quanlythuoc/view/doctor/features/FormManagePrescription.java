package org.cacanhdaden.quanlythuoc.view.doctor.features;

import com.formdev.flatlaf.FlatClientProperties;
import lombok.Data;
import lombok.Getter;
import org.cacanhdaden.quanlythuoc.model.dao.PrescriptionDAO;
import org.cacanhdaden.quanlythuoc.model.model.Prescription;
import org.cacanhdaden.quanlythuoc.view.authentication.Launch;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
public class FormManagePrescription extends JPanel {

    // Constants
    private static final String TITLE_TEXT = "Quản lý đơn thuốc";
    private static final String BTN_ADD_TEXT = "Thêm";
    private static final String BTN_EDIT_TEXT = "Sửa";
    private static final String BTN_DELETE_TEXT = "Xóa";
    private static final String BTN_VIEW_TEXT = "Xem thông tin";
    private static final String WARNING_SELECT_ROW = "Vui lòng chọn đơn để xem chi tiết.";
    private static final String WARNING_TITLE = "Chú ý";
    private static final String DATE_TIME_PATTERN = "dd/MM/yyyy HH:mm";
    private static final int BORDER_SIZE = 10;
    private static final int BUTTON_SPACING = 5;
    private static final int TABLE_COLUMN_COUNT = 5;
    private static final int COLUMN_ID = 0;
    private static final int COLUMN_PATIENT_ID = 1;
    private static final int COLUMN_PATIENT_NAME = 2;
    private static final int COLUMN_CREATED_AT = 3;
    private static final int COLUMN_STATUS = 4;

    private static final String[] COLUMN_HEADERS = {"Mã đơn", "Mã BN", "Tên BN", "Ngày tạo", "Trạng thái"};
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

    // Components
    @Getter private JTable tblPrescriptions;
    @Getter private JButton btnAdd;
    @Getter private JButton btnEdit;
    @Getter private JButton btnDelete;
    @Getter private JButton btnView;

    private final PrescriptionDAO prescriptionDAO;

    public FormManagePrescription() {
        this.prescriptionDAO = new PrescriptionDAO();
        initializeComponents();
        setupEventListeners();
        loadPrescriptions();
    }

    private void initializeComponents() {
        setupMainLayout();
        createTitlePanel();
        createTablePanel();
        createButtonPanel();
    }

    private void setupMainLayout() {
        setLayout(new BorderLayout(BORDER_SIZE, BORDER_SIZE));
        setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));
    }

    private void createTitlePanel() {
        JLabel titleLabel = new JLabel(TITLE_TEXT, SwingConstants.CENTER);
        titleLabel.putClientProperty(FlatClientProperties.STYLE, "font:$h1.font");
        add(titleLabel, BorderLayout.NORTH);
    }

    private void createTablePanel() {
        tblPrescriptions = new JTable();
        tblPrescriptions.setFillsViewportHeight(true);
        add(new JScrollPane(tblPrescriptions), BorderLayout.CENTER);
    }

    private void createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, BUTTON_SPACING, 0));

        btnAdd = new JButton(BTN_ADD_TEXT);
        btnEdit = new JButton(BTN_EDIT_TEXT);
        btnDelete = new JButton(BTN_DELETE_TEXT);
        btnView = new JButton(BTN_VIEW_TEXT);

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnView);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void setupEventListeners() {
        btnView.addActionListener(e -> handleViewAction());
    }

    private void loadPrescriptions() {
        List<Prescription> prescriptions = prescriptionDAO.getAllPrescriptions();
        Object[][] tableData = createTableData(prescriptions);
        tblPrescriptions.setModel(new javax.swing.table.DefaultTableModel(tableData, COLUMN_HEADERS));
    }

    private Object[][] createTableData(List<Prescription> prescriptions) {
        Object[][] data = new Object[prescriptions.size()][TABLE_COLUMN_COUNT];

        for (int i = 0; i < prescriptions.size(); i++) {
            Prescription prescription = prescriptions.get(i);
            data[i][COLUMN_ID] = prescription.getPrescriptionId();
            data[i][COLUMN_PATIENT_ID] = prescription.getPatientId();
            data[i][COLUMN_PATIENT_NAME] = prescription.getPatientName();
            data[i][COLUMN_CREATED_AT] = prescription.getCreatedAt().format(DATE_FORMATTER);
            data[i][COLUMN_STATUS] = prescription.getStatus();
        }

        return data;
    }

    private void handleViewAction() {
        int selectedRow = tblPrescriptions.getSelectedRow();

        if (selectedRow < 0) {
            showSelectionWarning();
            return;
        }

        long prescriptionId = extractPrescriptionId(selectedRow);
        Launch.showForm(new DetailPrescriptionPanel(prescriptionId));
    }

    private void showSelectionWarning() {
        JOptionPane.showMessageDialog(
                this,
                WARNING_SELECT_ROW,
                WARNING_TITLE,
                JOptionPane.WARNING_MESSAGE
        );
    }

    private long extractPrescriptionId(int selectedRow) {
        Object idObject = tblPrescriptions.getValueAt(selectedRow, COLUMN_ID);
        return Long.parseLong(idObject.toString());
    }
}