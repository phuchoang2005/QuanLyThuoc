package org.cacanhdaden.quanlythuoc.services.PatientManagerService.Implement;

import lombok.AllArgsConstructor;
import org.cacanhdaden.quanlythuoc.util.Exception.InvalidInformationException;
import org.cacanhdaden.quanlythuoc.util.validator.DateValidatorImp;
import org.cacanhdaden.quanlythuoc.util.validator.EmailValidatorImp;
import org.cacanhdaden.quanlythuoc.util.validator.ValidatorInterface;
import org.cacanhdaden.quanlythuoc.view.patient.features.PatientManager.PatientManagerPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
@AllArgsConstructor
class PatientManagerServiceHandlerOnProgress {
    private final PatientManagerPanel panel;
    void checkEmailValid(){
        panel.getEmailTextField().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent event){
                final String emailString = panel.getEmailTextField().getText();
                final ValidatorInterface emailValidator = new EmailValidatorImp(emailString);
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
    void checkDateValid(){
        this.panel.getDobTextField().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent event){
                final String  dateString = panel.getDobTextField().getText();
                final ValidatorInterface dateValidator = new DateValidatorImp(dateString);
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
