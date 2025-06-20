package org.cacanhdaden.quanlythuoc.view.doctor.menuDoctor;

public class MenuAction {
    protected boolean isCancel() {
        return cancel;
    }

    public void cancel() {
        this.cancel = true;
    }

    private boolean cancel = false;
}
