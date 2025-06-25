package org.cacanhdaden.quanlythuoc.services.PatientManagerService;

import org.cacanhdaden.quanlythuoc.util.Exception.InvalidInformationException;

public interface PatientManagerServiceInterface {
    public void loadCurrentInformation();
    public void update();
    public void checkInformationValidOnProgress();
}
