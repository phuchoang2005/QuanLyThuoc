package org.cacanhdaden.quanlythuoc.control.patient;
import org.cacanhdaden.quanlythuoc.services.PatientManagerService.PatientManagerServiceImp;
import org.cacanhdaden.quanlythuoc.services.PatientManagerService.PatientManagerServiceInterface;
import org.cacanhdaden.quanlythuoc.view.patient.features.PatientManagerPanel;

import javax.swing.*;


public class PatientManagerController {
    private final PatientManagerServiceInterface services;

    public PatientManagerController(PatientManagerPanel panel) {
        services = new PatientManagerServiceImp(panel);
        //loadCurrentInformation();
        checkInformationValid();
        update();
    }
    private void checkInformationValid(){
        services.checkInformationValid();
    }
    private void loadCurrentInformation(){
        services.loadCurrentInformation();
    }
    private void update(){
        services.update();
    }
}
