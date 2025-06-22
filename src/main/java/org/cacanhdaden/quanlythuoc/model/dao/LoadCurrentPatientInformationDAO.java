package org.cacanhdaden.quanlythuoc.model.dao;


import lombok.AllArgsConstructor;
import org.cacanhdaden.quanlythuoc.view.patient.features.PatientManagerPanel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@AllArgsConstructor
public class LoadCurrentPatientInformationDAO {
    private final PatientManagerPanel panel;

    public void load(){
        try{
            String sql = "select * from users where id = ?";
            Connection conn = MySQLConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, this.panel.getIdJTextField().getText());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                this.panel.getFull_nameField().setText(rs.getString("full_name"));
                this.panel.getEmailTextField().setText(rs.getString("email"));
                this.panel.getDobTextField().setText(rs.getString("dob"));
                this.panel.getGengerComboBox().setSelectedItem(rs.getString("gender"));
                this.panel.getAddressTextField().setText(rs.getString("address"));
                this.panel.getPhone_numberTextField().setText(rs.getString("phone_number"));
            }
        }catch(Exception ex){
            throw new IllegalStateException(ex);
        }
    }
}
