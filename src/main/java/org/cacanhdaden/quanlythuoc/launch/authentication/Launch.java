package org.cacanhdaden.quanlythuoc.launch.authentication;

import org.cacanhdaden.quanlythuoc.control.authentication.CreateAccountController;
import org.cacanhdaden.quanlythuoc.control.authentication.LoginController;
import org.cacanhdaden.quanlythuoc.view.login.CreateAccountView;
import org.cacanhdaden.quanlythuoc.view.login.LoginView;

public class Launch {
    public Launch() {
        ShowLoginView();
        ShowCreateAccountView();
    }
    private static void ShowLoginView(){
        LoginView loginView = new LoginView();
        LoginController controller = new LoginController(loginView);
        loginView.setVisible(true);
    }
    private static void ShowCreateAccountView(){
        CreateAccountView createView = new CreateAccountView();
        CreateAccountController controller = new CreateAccountController(createView);
    }
}
