package org.cacanhdaden.quanlythuoc.control.patient;
import org.cacanhdaden.quanlythuoc.services.PatientManagerService.PatientManagerServiceImp;
import org.cacanhdaden.quanlythuoc.services.PatientManagerService.PatientManagerServiceInterface;
import org.cacanhdaden.quanlythuoc.view.patient.features.PatientManagerPanel;


public class PatientManagerController {
    private final PatientManagerServiceInterface services;

    public PatientManagerController(PatientManagerPanel panel) {
        services = new PatientManagerServiceImp(panel);
        //loadCurrentInformation();
        checkInformationValid();
        update();
    }
    private void checkInformationValid(){
        services.checkInformationValidOnProgress();
    }
    private void loadCurrentInformation(){
        services.loadCurrentInformation();
    }
    private void update(){
        services.update();
    }
}
