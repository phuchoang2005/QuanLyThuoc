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
    private void checkInformationValidBeforeSubmit() throws InvalidInformationException {
        checkEmailValidBeforeSubmit();
        checkDobValidBeforeSubmit();
    }
    private void checkEmailValidBeforeSubmit() throws InvalidInformationException {
        final String emailString = panel.getEmailTextField().getText();
        try{
            ValidatorInterface validator = new EmailValidatorImp(emailString);
            validator.checkValid();
        }catch(InvalidInformationException exception){
            throw new InvalidInformationException(exception.getMessage());
        }
    }
    private void checkDobValidBeforeSubmit() throws InvalidInformationException {
        final String dobString = panel.getDobTextField().getText();
        try {
            ValidatorInterface validator = new DateValidatorImp(dobString);
            validator.checkValid();
        }catch(InvalidInformationException exception){
            throw new InvalidInformationException(exception.getMessage());
        }
    }
    public void update(){
        this.panel.getUpdateButton().addActionListener(e->{
            try{
                checkInformationValidBeforeSubmit();
                final UpdatePatientInformationDAO dao = new UpdatePatientInformationDAO(panel);
                try {
                    dao.update();
                    JOptionPane.showMessageDialog(panel, "Cập nhật thành công", "Chúc mừng", JOptionPane.INFORMATION_MESSAGE);
                } catch (IllegalStateException ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                    //loadCurrentInformation();
                }

            }catch(InvalidInformationException exception){
                JOptionPane.showMessageDialog(panel, exception.getMessage(), "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            }
        });
    }
    public void loadCurrentInformation() {
        try {
            final LoadCurrentPatientInformationDAO dao = new LoadCurrentPatientInformationDAO(panel);
            dao.load();
        } catch (IllegalStateException e) {
            JOptionPane.showMessageDialog(this.panel,e.getMessage(),"Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void checkInformationValidOnProgress() {

        checkDateValid();
        checkEmailValid();
    }
    //---
    private void checkEmailValid(){
        this.panel.getEmailTextField().addFocusListener(new FocusAdapter() {
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
    private void checkDateValid(){
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
