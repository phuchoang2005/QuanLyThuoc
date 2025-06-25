package org.cacanhdaden.quanlythuoc.view.authentication.form;

import com.formdev.flatlaf.FlatClientProperties;
import lombok.Getter;
import lombok.Setter;
import net.miginfocom.swing.MigLayout;
import org.cacanhdaden.quanlythuoc.control.authentication.ForgotPasswordController;
import org.cacanhdaden.quanlythuoc.view.authentication.panel.PanelForgotPassword;

import javax.swing.*;

@Getter
@Setter
public class ForgotPasswordForm extends JPanel {

    private JButton btnConfirm;
    private JLabel lbTitle;
    private JLabel lbUser;
    private JTextField txtUser;
    private PanelForgotPassword panelForgotPassword;

    public ForgotPasswordForm() {
        initComponents();
        setupLayout();
        setupComponentStyle();
    }

    private void setupLayout() {
        setLayout(new MigLayout("al center center"));
    }

    private void setupComponentStyle() {
        lbTitle.putClientProperty(FlatClientProperties.STYLE, "font:$h1.font");
        txtUser.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Email");
        btnConfirm.putClientProperty(FlatClientProperties.STYLE, "borderWidth:0;focusWidth:0");
    }

    private void initComponents() {
        panelForgotPassword = new PanelForgotPassword();
        lbTitle = new JLabel("Quên mật khẩu", SwingConstants.CENTER);
        lbUser = new JLabel("Email");

        txtUser = new JTextField();

        btnConfirm = new JButton("Hoàn tất");

        btnConfirm.addActionListener(e -> {
            ForgotPasswordController forgotPasswordController = new ForgotPasswordController(this);
        });

        panelForgotPassword.add(lbTitle);
        panelForgotPassword.add(lbUser);
        panelForgotPassword.add(txtUser);
        panelForgotPassword.add(btnConfirm);

        setLayout(new GroupLayout(this));
        GroupLayout layout = (GroupLayout) getLayout();
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(218, Short.MAX_VALUE)
                                .addComponent(panelForgotPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(197))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(68)
                                .addComponent(panelForgotPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(96, Short.MAX_VALUE))
        );
    }
}