package org.cacanhdaden.quanlythuoc.view.login.form;

import com.formdev.flatlaf.FlatClientProperties;
import lombok.Getter;
import lombok.Setter;
import net.miginfocom.swing.MigLayout;
import org.cacanhdaden.quanlythuoc.control.authentication.OTPForgotPasswordController;
import org.cacanhdaden.quanlythuoc.control.authentication.OTPSignUpController;
import org.cacanhdaden.quanlythuoc.model.object.Users;
import org.cacanhdaden.quanlythuoc.view.login.panel.PanelGenericOTPFillIn;

import javax.swing.*;

/**
 * Login Form Panel
 */
@Getter
@Setter
public class OTPForgotPasswordForm extends JPanel {

    private JButton btnFinish;
    private JButton btnResend;
    private JLabel lbTitle;
    private JLabel lbOTP;
    private PanelGenericOTPFillIn panelGenericOTPFillIn;
    private JTextField txtOTP;
    private Users user;
    private String OTPCode;

    public OTPForgotPasswordForm() {
        initComponents();
        setupLayout();
        setupComponentStyle();
    }

    private void setupLayout() {
        setLayout(new MigLayout("al center center"));
    }

    private void setupComponentStyle() {
        lbTitle.putClientProperty(FlatClientProperties.STYLE, "font:$h1.font");
        txtOTP.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Mã OTP");
        btnFinish.putClientProperty(FlatClientProperties.STYLE, "borderWidth:0;focusWidth:0");
    }

    private void initComponents() {
        panelGenericOTPFillIn = new PanelGenericOTPFillIn();
        lbTitle = new JLabel("Nhập mã OTP nhận được", SwingConstants.CENTER);
        lbOTP = new JLabel("OTP");
        txtOTP = new JTextField();
        btnResend = new JButton("Gửi lại");
        btnResend.setName("btnResend");
        btnFinish = new JButton("Hoàn tất");
        btnFinish.setName("btnFinish");

        btnFinish.addActionListener(e -> {
            OTPForgotPasswordController otpSignUpController = new OTPForgotPasswordController(this, e.getSource());
        });

        btnResend.addActionListener(e -> {
            OTPForgotPasswordController otpSignUpController = new OTPForgotPasswordController(this, e.getSource());
        });

        panelGenericOTPFillIn.add(lbTitle);
        panelGenericOTPFillIn.add(lbOTP);
        panelGenericOTPFillIn.add(txtOTP);
        panelGenericOTPFillIn.add(btnResend);
        panelGenericOTPFillIn.add(btnFinish);

        setLayout(new GroupLayout(this));
        GroupLayout layout = (GroupLayout) getLayout();
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(218, Short.MAX_VALUE)
                                .addComponent(panelGenericOTPFillIn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(197))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(68)
                                .addComponent(panelGenericOTPFillIn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(96, Short.MAX_VALUE))
        );
    }
}
