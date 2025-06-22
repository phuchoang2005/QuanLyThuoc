package org.cacanhdaden.quanlythuoc.model.dao;

import lombok.AllArgsConstructor;
import org.cacanhdaden.quanlythuoc.view.patient.features.PatientManagerPanel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
@AllArgsConstructor
public class UpdatePatientInformationDAO {
    private final PatientManagerPanel panel;
    public boolean isUpdateSuccessful(){
        try{
            String sql = "Update users " +
                    "set " +
                    "email = ?, " +
                    "full_name = ?, " +
                    "date_of_birth = ?, " +
                    "gender = ?, " +
                    "phone_number = ?, " +
                    "address = ?, " +
                    "updated_at = ? ," +
                    "where id = ?";
            Connection conn = MySQLConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, this.panel.getEmailTextField().getText());
            stmt.setString(2, this.panel.getFull_nameField().getText());
            stmt.setDate(3, Date.valueOf(LocalDate.parse(this.panel.getDobTextField().getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
            if (this.panel.getGengerComboBox().getSelectedItem().equals("Nam")) {
                stmt.setString(4, "male");
            }else if (this.panel.getGengerComboBox().getSelectedItem().equals("Nữ")) {
                stmt.setString(4, "female");
            }else {
                stmt.setString(4, "other");
            }
            stmt.setString(5, this.panel.getPhone_numberTextField().getText());
            stmt.setString(6, this.panel.getAddressTextField().getText());
            stmt.setDate(7, Date.valueOf(LocalDate.now()));
            stmt.setString(8, this.panel.getIdJTextField().getText());
            return stmt.executeUpdate() > 0;
        }catch(Exception ex){
            return false;
        }
    }
}
