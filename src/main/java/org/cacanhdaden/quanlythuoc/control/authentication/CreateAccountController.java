package org.cacanhdaden.quanlythuoc.control.authentication;
import org.cacanhdaden.quanlythuoc.launch.authentication.Launch;
import org.cacanhdaden.quanlythuoc.view.login.CreateAccountView;
public class CreateAccountController {
    private CreateAccountView create;
    public CreateAccountController(CreateAccountView create) {
        this.create = create;
        handleButtonBack();
        handleButtonCommit();
    }
    void handleButtonBack(){
        create.getBtnBack().addActionListener(e->{
            create.dispose();
            new Launch();
        });
    }
    void handleButtonCommit(){
        create.getBtnSubmit().addActionListener(e->{

        });
    }
}
