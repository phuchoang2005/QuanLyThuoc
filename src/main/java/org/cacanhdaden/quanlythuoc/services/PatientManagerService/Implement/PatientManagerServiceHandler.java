package org.cacanhdaden.quanlythuoc.services.PatientManagerService.Implement;
import org.cacanhdaden.quanlythuoc.util.Exception.InvalidInformationException;
import org.cacanhdaden.quanlythuoc.view.patient.features.PatientManager.PatientManagerPanel;


public class PatientManagerServiceHandler {
    private final PatientManagerPanel panel;
    PatientManagerServiceHandlerBeforeSubmit handlerBeforeSubmit;
    PatientManagerServiceHandlerOnProgress handlerOnProgress;
    PatientManagerServiceHandler(final PatientManagerPanel panel) {
        this.panel = panel;
        handlerBeforeSubmit = new PatientManagerServiceHandlerBeforeSubmit(panel);
        handlerOnProgress = new PatientManagerServiceHandlerOnProgress(panel);
    }
    void checkInformationValidBeforeSubmit() throws InvalidInformationException {
        handlerBeforeSubmit.checkDateValid();
        handlerBeforeSubmit.checkEmailValid();
    }
    void checkInformationOnProgress(){
        handlerOnProgress.checkDateValid();
        handlerOnProgress.checkEmailValid();
    }
}
