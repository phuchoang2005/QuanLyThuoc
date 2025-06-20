package org.cacanhdaden.quanlythuoc.view.patient.menuPatient;

public class MenuAction {
    protected boolean isCancel() {
        return cancel;
    }

    public void cancel() {
        this.cancel = true;
    }

    private boolean cancel = false;
}
