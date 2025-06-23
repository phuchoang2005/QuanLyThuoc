package org.cacanhdaden.quanlythuoc.control.patient.PrescriptionManagerController;

import org.cacanhdaden.quanlythuoc.services.PrescriptionManagerService.RequestPrescriptionService;
import org.cacanhdaden.quanlythuoc.view.patient.features.PrescriptionManager.features.RequestPrescriptionPanel;

public class RequestPrescriptionController {
    private RequestPrescriptionService service;
    public RequestPrescriptionController(final RequestPrescriptionPanel panel) {
       service = new  RequestPrescriptionService(panel);
       loadDoctor();
       update();
    }
    private void loadDoctor(){
            service.loadDoctor();
    }
    private void update(){
            service.update();
    }
}

