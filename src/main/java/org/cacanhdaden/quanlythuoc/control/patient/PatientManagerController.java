package org.cacanhdaden.quanlythuoc.control.patient;

import org.cacanhdaden.quanlythuoc.model.dao.LoadCurrentPatientInformationDAO;
import org.cacanhdaden.quanlythuoc.model.dao.UpdatePatientInformationDAO;
import org.cacanhdaden.quanlythuoc.services.CheckUserInformationInvalid;
import org.cacanhdaden.quanlythuoc.view.patient.features.PatientManagerPanel;

import javax.swing.*;
import java.awt.*;


public class PatientManagerController {
    private final PatientManagerPanel panel;
    public PatientManagerController(PatientManagerPanel panel) {
        this.panel = panel;
        loadCurrentInformation();
        update();
    }
    private void loadCurrentInformation(){
        final LoadCurrentPatientInformationDAO dao = new LoadCurrentPatientInformationDAO(panel);
        if (dao.isLoadSuccessful()){

        }else{
            JOptionPane.showMessageDialog(this.panel, "Tải thông tin từ Cơ sở dữ liệu lỗi", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void update(){
        final UpdatePatientInformationDAO dao = new UpdatePatientInformationDAO(panel);
        if (dao.isUpdateSuccessful()){
            JOptionPane.showMessageDialog(this.panel, "Cập nhật thành công", "Chúc mừng", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this.panel, "Cập nhật thất bại", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            panel.getIdJTextField().setText("");
            panel.getEmailTextField().setText("");
            panel.getFull_nameField().setText("");
            panel.getDobTextField().setText("");
            panel.getPhone_numberTextField().setText("");
            panel.getAddressTextField().setText("");
            panel.getIdJTextField().setText("");
        }
    }
}
