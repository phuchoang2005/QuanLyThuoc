package org.cacanhdaden.quanlythuoc.view.login.form;

import com.formdev.flatlaf.FlatClientProperties;
import lombok.Getter;
import lombok.Setter;
import net.miginfocom.swing.MigLayout;
import org.cacanhdaden.quanlythuoc.control.authentication.LoginController;
import org.cacanhdaden.quanlythuoc.control.authentication.OTPFillInController;
import org.cacanhdaden.quanlythuoc.model.object.Users;
import org.cacanhdaden.quanlythuoc.util.EmailSendingUtil;
import org.cacanhdaden.quanlythuoc.view.login.Launch;

import javax.swing.*;

/**
 * Login Form Panel
 */
@Getter
@Setter
public class OTPFillInForm extends JPanel {

    private JButton btnFinish;
    private JButton btnResend;
    private JLabel lbTitle;
    private JLabel lbOTP;
    private JLabel lbTimeCounter;
    private PanelOTPFillIn panelOTPFillIn;
    private JTextField txtOTP;
    private Users user;
    private int timeLimit = 120;
    private boolean isFinished = false;
    private String OTPCode;

    public OTPFillInForm() {
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
        panelOTPFillIn = new PanelOTPFillIn();
        lbTitle = new JLabel("Nhập mã OTP nhận được", SwingConstants.CENTER);
        lbOTP = new JLabel("OTP");
        lbTimeCounter = new JLabel("Thời gian còn lại: " + timeLimit + "s", SwingConstants.RIGHT);
        txtOTP = new JTextField();
        btnResend = new JButton("Gửi lại");
        btnResend.setName("btnResend");
        btnFinish = new JButton("Hoàn tất");
        btnFinish.setName("btnFinish");

        btnFinish.addActionListener(e -> {
            OTPFillInController otpFillInController = new OTPFillInController(this, e.getSource());
        });

        btnResend.addActionListener(e -> {
            OTPFillInController otpFillInController = new OTPFillInController(this, e.getSource());
        });

        panelOTPFillIn.add(lbTitle);
        panelOTPFillIn.add(lbOTP);
        panelOTPFillIn.add(txtOTP);
        panelOTPFillIn.add(lbTimeCounter);
        panelOTPFillIn.add(btnResend);
        panelOTPFillIn.add(btnFinish);

        setLayout(new GroupLayout(this));
        GroupLayout layout = (GroupLayout) getLayout();
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(218, Short.MAX_VALUE)
                                .addComponent(panelOTPFillIn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(197))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(68)
                                .addComponent(panelOTPFillIn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(96, Short.MAX_VALUE))
        );
    }
}
