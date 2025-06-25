package org.cacanhdaden.quanlythuoc.services.PatientManagerService.Implement;

import org.cacanhdaden.quanlythuoc.model.dao.PatientManagerDAO.LoadCurrentPatientInformationDAO;
import org.cacanhdaden.quanlythuoc.model.dao.PatientManagerDAO.UpdatePatientInformationDAO;
import org.cacanhdaden.quanlythuoc.services.PatientManagerService.PatientManagerServiceInterface;
import org.cacanhdaden.quanlythuoc.util.Exception.InvalidInformationException;
import org.cacanhdaden.quanlythuoc.view.patient.features.PatientManager.PatientManagerPanel;

import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class PatientManagerServiceImp implements PatientManagerServiceInterface {
    private final PatientManagerPanel panel;
    private PatientManagerServiceHandler handler;

    public PatientManagerServiceImp(PatientManagerPanel panel){
        this.panel = panel;
        handler = new PatientManagerServiceHandler(panel);
    }

    @Override
    public void update() {
        this.panel.getUpdateButton().addActionListener(e->{
            try {
                handler.checkInformationValidBeforeSubmit();
                final UpdatePatientInformationDAO dao = new UpdatePatientInformationDAO(panel);
                try {
                    dao.update();
                    JOptionPane.showMessageDialog(panel, "Cập nhật thành công", "Chúc mừng", JOptionPane.INFORMATION_MESSAGE);
                } catch (IllegalStateException ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                    loadCurrentInformation();
                }
            } catch (InvalidInformationException ex) {
                JOptionPane.showMessageDialog(panel, ex.getMessage(), "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    @Override
    public void loadCurrentInformation() {
        this.panel.getIdJTextField().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent eventListener){
                try {
                    final LoadCurrentPatientInformationDAO dao = new LoadCurrentPatientInformationDAO(panel);
                    dao.load();
                } catch (IllegalStateException e) {
                    JOptionPane.showMessageDialog(panel,e.getMessage(),"Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    @Override
    public void checkInformationValidOnProgress() {
        handler.checkInformationOnProgress();
    }
}
