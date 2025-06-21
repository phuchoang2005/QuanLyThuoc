package org.cacanhdaden.quanlythuoc.services;

import lombok.AllArgsConstructor;
import org.cacanhdaden.quanlythuoc.view.patient.features.PatientManagerPanel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
@AllArgsConstructor
public class CheckUserInformationInvalid {
    private final PatientManagerPanel panel;

    public void checkingEmailInvalid(){
        final String email = this.panel.getEmailTextField().getText();
        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(null, "Định dạng email không hợp lệ", "Lỗi", JOptionPane.WARNING_MESSAGE);
            this.panel.getEmailTextField().setBorder(BorderFactory.createLineBorder(Color.RED));
        }
    }
    public void checkingDateInvalid(){
        final String date = this.panel.getDobTextField().getText();
        if (!isValidDate(date)) {
            JOptionPane.showMessageDialog(null, "Định dạng thời gian không hợp lệ", "Lỗi", JOptionPane.WARNING_MESSAGE);
            this.panel.getDobTextField().setBorder(BorderFactory.createLineBorder(Color.RED));
        }
    }
    private static boolean isValidEmail(String email) {
        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(regex);
    }
    private static boolean isValidDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu")
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDate.parse(dateStr, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
