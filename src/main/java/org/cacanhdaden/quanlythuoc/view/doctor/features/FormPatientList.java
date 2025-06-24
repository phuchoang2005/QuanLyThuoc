package org.cacanhdaden.quanlythuoc.view.doctor.features;

import com.formdev.flatlaf.FlatClientProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.cacanhdaden.quanlythuoc.model.dao.PatientDAO;
import org.cacanhdaden.quanlythuoc.model.model.Patient;
import org.cacanhdaden.quanlythuoc.view.authentication.Launch;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
public class FormPatientList extends JPanel {

    // Constants
    private static final String TITLE_TEXT = "Danh sách bệnh nhân";
    private static final String VIEW_RECORD_TEXT = "Xem hồ sơ bệnh án";
    private static final String WARNING_SELECT_PATIENT = "Vui lòng chọn bệnh nhân để xem hồ sơ.";
    private static final String WARNING_TITLE = "Chú ý";
    private static final String DATE_PATTERN = "dd/MM/yyyy";
    private static final int BORDER_SIZE = 10;
    private static final int TABLE_COLUMN_COUNT = 7;
    private static final String[] COLUMN_HEADERS = {
            "Mã BN", "Họ tên", "Tuổi", "Giới tính", "Email", "SĐT", "Ngày khám gần nhất"
    };

    // Components với @Getter để controller có thể truy cập
    @Getter private JTable tblPatients;
    @Getter private JButton btnViewRecord;
    @Getter @Setter private PatientDAO patientDAO;

    public FormPatientList() {
        this.patientDAO = new PatientDAO();
        initializeComponents();
        setupEventListeners();
        loadPatients();
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
        tblPatients = new JTable();
        tblPatients.setFillsViewportHeight(true);
        add(new JScrollPane(tblPatients), BorderLayout.CENTER);
    }

    private void createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnViewRecord = new JButton(VIEW_RECORD_TEXT);
        buttonPanel.add(btnViewRecord);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void setupEventListeners() {
        btnViewRecord.addActionListener(e -> handleViewRecord());
    }

    private void loadPatients() {
        List<Patient> patients = patientDAO.getAllPatients();
        Object[][] tableData = createTableData(patients);
        tblPatients.setModel(new javax.swing.table.DefaultTableModel(tableData, COLUMN_HEADERS));
    }

    private Object[][] createTableData(List<Patient> patients) {
        Object[][] data = new Object[patients.size()][TABLE_COLUMN_COUNT];

        for (int i = 0; i < patients.size(); i++) {
            Patient patient = patients.get(i);
            data[i][0] = patient.getPatientId();
            data[i][1] = patient.getFullName();
            data[i][2] = patient.getAge();
            data[i][3] = patient.getGender();
            data[i][4] = patient.getEmail();
            data[i][5] = patient.getPhone();
            data[i][6] = patient.getLastVisit() != null ?
                    patient.getLastVisit().format(DateTimeFormatter.ofPattern(DATE_PATTERN)) :
                    "Chưa có";
        }

        return data;
    }

    private void handleViewRecord() {
        int selectedRow = tblPatients.getSelectedRow();

        if (selectedRow < 0) {
            showSelectionWarning();
            return;
        }

        long patientId = (Long) tblPatients.getValueAt(selectedRow, 0);
        Launch.showForm(new FormMedicalRecord(patientId));
    }

    private void showSelectionWarning() {
        JOptionPane.showMessageDialog(
                this,
                WARNING_SELECT_PATIENT,
                WARNING_TITLE,
                JOptionPane.WARNING_MESSAGE
        );
    }

    // Public methods for external access (used by controller)
    public void refreshPatientList() {
        loadPatients();
    }

    public void updateTableModel(javax.swing.table.DefaultTableModel model) {
        tblPatients.setModel(model);
    }

    public int getSelectedPatientRow() {
        return tblPatients.getSelectedRow();
    }

    public Object getValueAt(int row, int column) {
        return tblPatients.getValueAt(row, column);
    }
}