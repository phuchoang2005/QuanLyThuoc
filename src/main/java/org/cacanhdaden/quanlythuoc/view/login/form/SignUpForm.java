package org.cacanhdaden.quanlythuoc.view.login.form;

import com.formdev.flatlaf.FlatClientProperties;
import lombok.Getter;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;
import org.cacanhdaden.quanlythuoc.model.object.Users;
import org.cacanhdaden.quanlythuoc.util.GenderPassingUtil;
import org.cacanhdaden.quanlythuoc.view.login.Launch;
import org.cacanhdaden.quanlythuoc.control.authentication.SignUpController;
import raven.datetime.DatePicker;

@Getter
public class SignUpForm extends JPanel {

    private JButton btnSignUp;
    private JButton btnReturn;
    private JLabel lbTitle;
    private JLabel lbEmail;
    private JLabel lbPass;
    private JLabel lbFullName;
    private JLabel lbDob;
    private JLabel lbGender;
    private JLabel lbPhone;
    private JLabel lbAddress;
    private JTextField txtEmail;
    private JPasswordField txtPass;
    private JTextField txtFullName;
    private DatePicker datePickerDob;
    private JComboBox<String> cbGender;
    private JTextField txtPhone;
    private JTextArea txtAddress;
    private PanelSignUp panelSignUp;

    public SignUpForm() {
        initComponents();
        setupLayout();
        setupComponentStyle();
    }

    private void setupLayout() {
        setLayout(new MigLayout("al center center"));
    }

    private void setupComponentStyle() {
        lbTitle.putClientProperty(FlatClientProperties.STYLE, "font:$h1.font");
        txtEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Email");
        txtPass.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Mật khẩu");
        txtPass.putClientProperty(FlatClientProperties.STYLE, "showRevealButton:true;showCapsLock:true");
        txtFullName.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Họ và tên");
        txtPhone.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Số điện thoại");
        txtAddress.setLineWrap(true);
        txtAddress.setWrapStyleWord(true);
        btnSignUp.putClientProperty(FlatClientProperties.STYLE, "borderWidth:0;focusWidth:0");
        btnReturn.putClientProperty(FlatClientProperties.STYLE, "borderWidth:0;focusWidth:0");
    }

    private void initComponents() {
        panelSignUp = new org.cacanhdaden.quanlythuoc.view.login.form.PanelSignUp();
        lbTitle = new JLabel("Đăng ký tài khoản", SwingConstants.CENTER);
        lbEmail = new JLabel("Email");
        lbPass = new JLabel("Mật khẩu");
        lbFullName = new JLabel("Họ và tên");
        lbDob = new JLabel("Ngày sinh");
        lbGender = new JLabel("Giới tính");
        lbPhone = new JLabel("Số điện thoại");
        lbAddress = new JLabel("Địa chỉ");

        txtEmail = new JTextField();
        txtPass = new JPasswordField();
        txtFullName = new JTextField();
        datePickerDob = new DatePicker();
        JFormattedTextField dateEditor = new JFormattedTextField();
        datePickerDob.setEditor(dateEditor);
        datePickerDob.setCloseAfterSelected(true);

        cbGender = new JComboBox<>(
            new String[]{
                GenderPassingUtil.UserEnumToString(
                    Users.GenderEnum.MALE
                ),
                GenderPassingUtil.UserEnumToString(
                    Users.GenderEnum.FEMALE
                )
            }
        );
        txtPhone = new JTextField();
        txtAddress = new JTextArea(2, 20);

        btnSignUp = new JButton("Hoàn tất");
        btnReturn = new JButton("Trở về đăng nhập");

        btnReturn.addActionListener(e -> {
            Launch.showLoginForm();
        });

        btnSignUp.addActionListener(e -> {
            SignUpController signUpController = new SignUpController(this);
        });

        panelSignUp.add(lbTitle);
        panelSignUp.add(lbEmail);
        panelSignUp.add(txtEmail);
        panelSignUp.add(lbPass);
        panelSignUp.add(txtPass);
        panelSignUp.add(lbFullName);
        panelSignUp.add(txtFullName);
        panelSignUp.add(lbDob);
        panelSignUp.add(dateEditor, "growx");
        panelSignUp.add(lbGender);
        panelSignUp.add(cbGender);
        panelSignUp.add(lbPhone);
        panelSignUp.add(txtPhone);
        panelSignUp.add(lbAddress);
        panelSignUp.add(new JScrollPane(txtAddress));
        panelSignUp.add(btnSignUp);
        panelSignUp.add(btnReturn);

        setLayout(new GroupLayout(this));
        GroupLayout layout = (GroupLayout) getLayout();
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(218, Short.MAX_VALUE)
                                .addComponent(panelSignUp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(197))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(68)
                                .addComponent(panelSignUp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(96, Short.MAX_VALUE))
        );
    }
}