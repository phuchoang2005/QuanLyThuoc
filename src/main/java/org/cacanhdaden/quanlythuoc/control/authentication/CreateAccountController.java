package org.cacanhdaden.quanlythuoc.control.authentication;
import org.cacanhdaden.quanlythuoc.launch.authentication.Launch;
import org.cacanhdaden.quanlythuoc.model.dao.CreateNewPatientDAO;
import org.cacanhdaden.quanlythuoc.model.dto.NewPatientDTO;
import org.cacanhdaden.quanlythuoc.model.model.User;
import org.cacanhdaden.quanlythuoc.view.login.CreateAccountView;

import javax.swing.*;
import java.util.UUID;

public class CreateAccountController {
    private CreateAccountView createView;
    private NewPatientDTO dto = new NewPatientDTO();
    public CreateAccountController(CreateAccountView create) {
        this.createView = create;
        handleButtonBack();
        handleButtonCommit();
        handleInformation();
    }
    void handleInvalidPass(){
        String pass = this.createView.getPassword();
        String pass_confirm = this.createView.getConfirmPassword();

        if (pass.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(createView, "Mật khẩu không được để trống", "Yêu cầu nhập lại",JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!pass.equals(pass_confirm)) {
            JOptionPane.showMessageDialog(createView, "Mật khẩu không trùng khớp", "Yêu cầu nhập lại",JOptionPane.WARNING_MESSAGE);
            return;
        }
    }
    void handleInformation(){
        dto.setAge(this.createView.getAge());
        dto.setUserName(this.createView.getUserName());
        dto.setFullName(this.createView.getFullName());
        dto.setPhone(this.createView.getPhone());
        dto.setPassword_no_hash(this.createView.getPassword());
        dto.setId(UUID.randomUUID().toString());
        dto.setEmail(this.createView.getEmail());
        dto.setRole(User.Role.PATIENT);
    }
    void handleButtonBack(){
        createView.getBtnBack().addActionListener(e->{
            createView.dispose();
            new Launch();
        });
    }
    void handleButtonCommit(){
        createView.getBtnSubmit().addActionListener(e->{
            handleInvalidPass();
            CreateNewPatientDAO dao = new CreateNewPatientDAO(dto);
            if (dao.Register()){
                JOptionPane.showMessageDialog(createView, "Register successful", "Register successful", JOptionPane.INFORMATION_MESSAGE);
                this.createView.dispose();
                new Launch();
            }else{
                JOptionPane.showMessageDialog(createView, "Register failed", "Register failed", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
