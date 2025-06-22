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
public class CheckDateValid implements CheckUserInformationInvalid {
    private final PatientManagerPanel panel;

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
    @Override
    public void checkInformationValid() {
        final String date = this.panel.getDobTextField().getText();
        if (!isValidDate(date)) {
            JOptionPane.showMessageDialog(this.panel, "Định dạng thời gian không hợp lệ", "Lỗi", JOptionPane.WARNING_MESSAGE);
            this.panel.getDobTextField().setBorder(BorderFactory.createLineBorder(Color.RED));
        } else {
            this.panel.getDobTextField().setBorder(BorderFactory.createLineBorder(Color.GREEN));
        }
    }
}
