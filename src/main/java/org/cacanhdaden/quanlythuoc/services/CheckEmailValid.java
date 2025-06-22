package org.cacanhdaden.quanlythuoc.services;

import lombok.AllArgsConstructor;
import org.cacanhdaden.quanlythuoc.view.patient.features.PatientManagerPanel;

import javax.swing.*;
import java.awt.*;
@AllArgsConstructor
public class CheckEmailValid implements CheckUserInformationInvalid {
    private final PatientManagerPanel panel;

    @Override
    public void checkInformationValid() {
        final String email = this.panel.getEmailTextField().getText();
        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(this.panel, "Định dạng email không hợp lệ", "Lỗi", JOptionPane.WARNING_MESSAGE);
            this.panel.getEmailTextField().setBorder(BorderFactory.createLineBorder(Color.RED));
        }else{
            this.panel.getEmailTextField().setBorder(BorderFactory.createLineBorder(Color.GREEN));
        }
    }
    private static boolean isValidEmail(String email) {
        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(regex);
    }
}
