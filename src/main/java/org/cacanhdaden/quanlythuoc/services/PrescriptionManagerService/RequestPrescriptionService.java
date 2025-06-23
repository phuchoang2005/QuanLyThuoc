package org.cacanhdaden.quanlythuoc.services.PrescriptionManagerService;

import org.cacanhdaden.quanlythuoc.model.dao.PrescriptionManagerDAO.LoadDoctorDAO;
import org.cacanhdaden.quanlythuoc.model.dao.PrescriptionManagerDAO.RequestPrescriptionDAO;
import org.cacanhdaden.quanlythuoc.model.model.PrescriptionRequest;
import org.cacanhdaden.quanlythuoc.model.model.Users;
import org.cacanhdaden.quanlythuoc.view.patient.features.PrescriptionManager.features.RequestPrescriptionPanel;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class RequestPrescriptionService {
    private final RequestPrescriptionPanel panel;
    private final PrescriptionRequest request;
    public RequestPrescriptionService(final RequestPrescriptionPanel panel) {
        this.panel = panel;
        request = new PrescriptionRequest();
        getRequest();
    }
    private void getRequest() {
        request.setPatient_id(panel.getPatientIdTextField().getText());
        request.setDoctor_id(panel.getPatientIdTextField().getText());
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
    public void update(){
        this.panel.getSubmitButton().addActionListener(e->{
            RequestPrescriptionDAO dao = new RequestPrescriptionDAO(request, (Users) panel.getDoctorComboBox().getSelectedItem());
            dao.update();
        });
    }
}
