package org.cacanhdaden.quanlythuoc.view.authentication.form;

import com.formdev.flatlaf.FlatClientProperties;
import lombok.Getter;
import lombok.Setter;
import net.miginfocom.swing.MigLayout;
import org.cacanhdaden.quanlythuoc.control.authentication.ResetPasswordController;
import org.cacanhdaden.quanlythuoc.view.login.panel.PanelResetPassword;

import javax.swing.*;

/**
 * Login Form Panel
 */
@Getter
@Setter
public class ResetPasswordForm extends JPanel {

    private JButton btnConfirm;
    private JLabel lbConfirmPass;
    private JLabel lbTitle;
    private JLabel lbPass;
    private PanelResetPassword panelResetPassword;
    private JPasswordField txtConfirmPass;
    private JPasswordField txtPass;
    private String email;

    public ResetPasswordForm() {
        initComponents();
        setupLayout();
        setupComponentStyle();
    }

    private void setupLayout() {
        setLayout(new MigLayout("al center center"));
    }

    private void setupComponentStyle() {
        lbTitle.putClientProperty(FlatClientProperties.STYLE, "font:$h1.font");
        txtPass.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Mật khẩu mới");
        txtPass.putClientProperty(FlatClientProperties.STYLE, "showRevealButton:true;showCapsLock:true");
        txtConfirmPass.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập lại mật khẩu mới");
        txtConfirmPass.putClientProperty(FlatClientProperties.STYLE, "showRevealButton:true;showCapsLock:true");
        btnConfirm.putClientProperty(FlatClientProperties.STYLE, "borderWidth:0;focusWidth:0");
    }

    private void initComponents() {
        panelResetPassword = new PanelResetPassword();
        lbTitle = new JLabel("Đặt lại mật khẩu mới", SwingConstants.CENTER);
        lbPass = new JLabel("Mật khẩu mới");
        txtPass = new JPasswordField();
        lbConfirmPass = new JLabel("Xác nhận mật khẩu mới");
        txtConfirmPass = new JPasswordField();
        btnConfirm = new JButton("Xác nhận");

        btnConfirm.addActionListener(e -> {
            ResetPasswordController resetPasswordController = new ResetPasswordController(this);
        });

        panelResetPassword.add(lbTitle);
        panelResetPassword.add(lbPass);
        panelResetPassword.add(txtPass);
        panelResetPassword.add(lbConfirmPass);
        panelResetPassword.add(txtConfirmPass);
        panelResetPassword.add(btnConfirm);

        setLayout(new GroupLayout(this));
        GroupLayout layout = (GroupLayout) getLayout();
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(218, Short.MAX_VALUE)
                                .addComponent(panelResetPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(197))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(68)
                                .addComponent(panelResetPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(96, Short.MAX_VALUE))
        );
    }

    /*private void onLogin() {
        Application.login();
    }*/
}
