package org.cacanhdaden.quanlythuoc.control.patient;

import org.cacanhdaden.quanlythuoc.model.dao.UpdatePatientInformationDAO;
import org.cacanhdaden.quanlythuoc.services.CheckUserInformationInvalid;
import org.cacanhdaden.quanlythuoc.view.patient.features.PatientManagerPanel;

import javax.swing.*;
import java.awt.*;


public class PatientManagerController {
    private final PatientManagerPanel panel;
    public PatientManagerController(PatientManagerPanel panel) {
        this.panel = panel;
        update();
    }
    private void update(){
        UpdatePatientInformationDAO dao = new UpdatePatientInformationDAO(panel);
        if (dao.isUpdateSuccessful()){
            JOptionPane.showMessageDialog(null, "Cập nhật thành công", "Chúc mừng", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "Cập nhật thất bại", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        }
    }
}
