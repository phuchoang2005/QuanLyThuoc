package org.cacanhdaden.quanlythuoc.view.login.menu;

public class MenuAction {
    protected boolean isCancel() {
        return cancel;
    }

    public void cancel() {
        this.cancel = true;
    }

    private boolean cancel = false;
}
