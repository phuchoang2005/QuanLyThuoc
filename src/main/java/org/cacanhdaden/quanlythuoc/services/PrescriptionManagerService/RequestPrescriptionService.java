package org.cacanhdaden.quanlythuoc.services.PrescriptionManagerService;

import org.cacanhdaden.quanlythuoc.model.dao.PrescriptionManagerDAO.LoadDoctorDAO;
import org.cacanhdaden.quanlythuoc.model.dao.PrescriptionManagerDAO.RequestPrescriptionDAO;
import org.cacanhdaden.quanlythuoc.model.model.PrescriptionRequest;
import org.cacanhdaden.quanlythuoc.model.model.Users;
import org.cacanhdaden.quanlythuoc.util.Exception.InvalidInformationException;
import org.cacanhdaden.quanlythuoc.view.patient.features.PrescriptionManager.features.RequestPrescriptionPanel;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class RequestPrescriptionService {
    private final RequestPrescriptionPanel panel;
    private final PrescriptionRequest request;
    public RequestPrescriptionService(final RequestPrescriptionPanel panel) {
        this.panel = panel;
        request = new PrescriptionRequest();
    }
    private void getRequest() {
        request.setPatient_id(panel.getPatientIdTextField().getText());
        request.setDoctor_id(((Users) panel.getDoctorComboBox().getSelectedItem()).getId());
        request.setReason(panel.getReasonTextArea().getText());
        request.setUpdated_at(String.valueOf(Date.valueOf(LocalDate.now())));
    }
    public void loadDoctor(){
        LoadDoctorDAO dao = new LoadDoctorDAO();
        ArrayList<Users> doctors = dao.load();
        for (Users u : doctors) {
            this.panel.getDoctorComboBox().addItem(u);
        }
    }
    private void checkIfReasonEmpty() throws InvalidInformationException {
        if (request.getReason().isEmpty()) {
            throw new InvalidInformationException("Tình trạng bệnh không được rỗng");
        }
    }
    public void update(){
        this.panel.getSubmitButton().addActionListener(e->{
            try {
                getRequest();
                checkIfReasonEmpty();
                // ----
                RequestPrescriptionDAO dao = new RequestPrescriptionDAO(request, (Users) panel.getDoctorComboBox().getSelectedItem());
                try {
                    dao.update();
                    panel.getReasonTextArea().setBorder(BorderFactory.createLineBorder(Color.GREEN));
                    JOptionPane.showMessageDialog(panel, "Cập nhật thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (InvalidInformationException ex) {
                JOptionPane.showMessageDialog(panel, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                panel.getReasonTextArea().setBorder(BorderFactory.createLineBorder(Color.RED));
            }
        });
    }
}
