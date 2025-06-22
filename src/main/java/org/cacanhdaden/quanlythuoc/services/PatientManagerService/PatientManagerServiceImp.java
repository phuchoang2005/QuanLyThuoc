package org.cacanhdaden.quanlythuoc.services.PatientManagerService;

import lombok.AllArgsConstructor;
import org.cacanhdaden.quanlythuoc.model.dao.LoadCurrentPatientInformationDAO;
import org.cacanhdaden.quanlythuoc.model.dao.UpdatePatientInformationDAO;
import org.cacanhdaden.quanlythuoc.services.Exception.InvalidInformationException;
import org.cacanhdaden.quanlythuoc.services.validator.DateValidatorImp;
import org.cacanhdaden.quanlythuoc.services.validator.EmailValidatorImp;
import org.cacanhdaden.quanlythuoc.services.validator.ValidatorInterface;
import org.cacanhdaden.quanlythuoc.view.patient.features.PatientManagerPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

@AllArgsConstructor
public class PatientManagerServiceImp implements PatientManagerServiceInterface{
    private final PatientManagerPanel panel;

    public void update(){
        this.panel.getUpdateButton().addActionListener(e->{
            checkInformationValid();
            final UpdatePatientInformationDAO dao = new UpdatePatientInformationDAO(panel);
            try {
                dao.update();
                JOptionPane.showMessageDialog(panel, "Cập nhật thành công", "Chúc mừng", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, ex.getMessage(), "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                loadCurrentInformation();
            }
        });
    }
    public void loadCurrentInformation() {
        final LoadCurrentPatientInformationDAO dao = new LoadCurrentPatientInformationDAO(panel);
        try {
            dao.load();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this.panel,e.getMessage(),"Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void checkInformationValid() {

        checkDateValid();
        checkEmailValid();
    }
    //---
    private void checkEmailValid(){
        this.panel.getEmailTextField().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent event){
                final ValidatorInterface emailValidator = new EmailValidatorImp(panel.getEmailTextField().getText());
                try {
                    emailValidator.checkValid();
                    panel.getEmailTextField().setBorder(BorderFactory.createLineBorder(Color.GREEN));
                } catch (InvalidInformationException e) {
                    JOptionPane.showMessageDialog(panel, e.getMessage(), "Lỗi", JOptionPane.WARNING_MESSAGE);
                    panel.getEmailTextField().setBorder(BorderFactory.createLineBorder(Color.RED));
                }
            }
        });
    }
    private void checkDateValid(){
        this.panel.getDobTextField().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent event){
                final ValidatorInterface dateValidator = new DateValidatorImp(panel.getDobTextField().getText());
                try {
                    dateValidator.checkValid();
                    panel.getDobTextField().setBorder(BorderFactory.createLineBorder(Color.GREEN));
                } catch (InvalidInformationException e) {
                    JOptionPane.showMessageDialog(panel, e.getMessage(), "Lỗi", JOptionPane.WARNING_MESSAGE);
                    panel.getDobTextField().setBorder(BorderFactory.createLineBorder(Color.RED));
                }
            }
        });
    }
}
