package org.cacanhdaden.quanlythuoc.view.login.form;

import com.formdev.flatlaf.FlatClientProperties;
import lombok.Getter;
import net.miginfocom.swing.MigLayout;
import org.cacanhdaden.quanlythuoc.control.authentication.LoginController;
import org.cacanhdaden.quanlythuoc.view.login.Launch;

import javax.swing.*;

/**
 * Login Form Panel
 */
@Getter
public class LoginForm extends JPanel {

    private JButton btnLogin;
    private JButton btnSignUp;
    private JButton btnForgotPassword;
    private JLabel lbPass;
    private JLabel lbTitle;
    private JLabel lbUser;
    private PanelLogin panelLogin1;
    private JPasswordField txtPass;
    private JTextField txtUser;

    public LoginForm() {
        initComponents();
        setupLayout();
        setupComponentStyle();
    }

    private void setupLayout() {
        setLayout(new MigLayout("al center center"));
    }

    private void setupComponentStyle() {
        lbTitle.putClientProperty(FlatClientProperties.STYLE, "font:$h1.font");
        txtUser.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "User Name");
        txtPass.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Password");
        txtPass.putClientProperty(FlatClientProperties.STYLE, "showRevealButton:true;showCapsLock:true");
        btnLogin.putClientProperty(FlatClientProperties.STYLE, "borderWidth:0;focusWidth:0");
    }

    private void initComponents() {
        panelLogin1 = new org.cacanhdaden.quanlythuoc.view.login.form.PanelLogin();
        lbTitle = new JLabel("Đăng nhập", SwingConstants.CENTER);
        lbUser = new JLabel("Email / Số điện thoại");
        txtUser = new JTextField();
        lbPass = new JLabel("Mật khẩu");
        txtPass = new JPasswordField();
        btnSignUp = new JButton("Đăng ký");
        btnForgotPassword = new JButton("Quên mật khẩu");
        btnLogin = new JButton("Đăng nhập");

        btnLogin.addActionListener(e -> {
            LoginController loginController = new LoginController(this);
        });

        btnSignUp.addActionListener(e -> {
            Launch.showSignUpForm();
        });

        panelLogin1.add(lbTitle);
        panelLogin1.add(lbUser);
        panelLogin1.add(txtUser);
        panelLogin1.add(lbPass);
        panelLogin1.add(txtPass);
        panelLogin1.add(btnSignUp);
        panelLogin1.add(btnForgotPassword);
        panelLogin1.add(btnLogin);

        setLayout(new GroupLayout(this));
        GroupLayout layout = (GroupLayout) getLayout();
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(218, Short.MAX_VALUE)
                                .addComponent(panelLogin1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(197))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(68)
                                .addComponent(panelLogin1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(96, Short.MAX_VALUE))
        );
    }

    /*private void onLogin() {
        Application.login();
    }*/
}
