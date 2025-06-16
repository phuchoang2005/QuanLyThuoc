package org.cacanhdaden.quanlythuoc.view.login.application.form;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import org.cacanhdaden.quanlythuoc.view.login.application.Application;

import javax.swing.*;

/**
 * Login Form Panel
 */
public class LoginForm extends JPanel {

    private JButton cmdLogin;
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
        cmdLogin.putClientProperty(FlatClientProperties.STYLE, "borderWidth:0;focusWidth:0");
    }

    private void initComponents() {
        panelLogin1 = new org.cacanhdaden.quanlythuoc.view.login.application.form.PanelLogin();
        lbTitle = new JLabel("Login", SwingConstants.CENTER);
        lbUser = new JLabel("User Name");
        txtUser = new JTextField();
        lbPass = new JLabel("Password");
        txtPass = new JPasswordField();
        cmdLogin = new JButton("Login");

        cmdLogin.addActionListener(e -> onLogin());

        panelLogin1.add(lbTitle);
        panelLogin1.add(lbUser);
        panelLogin1.add(txtUser);
        panelLogin1.add(lbPass);
        panelLogin1.add(txtPass);
        panelLogin1.add(cmdLogin);

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

    private void onLogin() {
        Application.login();
    }
}
