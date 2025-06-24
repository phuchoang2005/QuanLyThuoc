package org.cacanhdaden.quanlythuoc.view.login;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import lombok.Getter;
import org.cacanhdaden.quanlythuoc.model.object.Users;
import org.cacanhdaden.quanlythuoc.view.login.form.*;
import org.cacanhdaden.quanlythuoc.view.patient.MainFormPatient;
import raven.toast.Notifications;

import javax.swing.*;
import java.awt.*;

@Getter
public class Launch extends JFrame {

    private static Launch instance;
    private final MainFormPatient mainFormPatient;
    private final LoginForm loginForm;
    private final SignUpForm signUpForm;
    private final OTPSignUpForm otpSignUpForm;
    private final OTPForgotPasswordForm otpForgotPasswordForm;
    private final ForgotPasswordForm forgotPasswordForm;
    private final ResetPasswordForm resetPasswordForm;

    public Launch() {
        this.mainFormPatient = new MainFormPatient();
        this.loginForm = new LoginForm();
        this.signUpForm = new SignUpForm();
        this.otpSignUpForm = new OTPSignUpForm();
        this.forgotPasswordForm = new ForgotPasswordForm();
        this.otpForgotPasswordForm = new OTPForgotPasswordForm();
        this.resetPasswordForm = new ResetPasswordForm();

        initUI();
    }

    private void initUI() {
        initComponents();
        setSize(new Dimension(1366, 768));
        setLocationRelativeTo(null);
        setContentPane(loginForm);
        getRootPane().putClientProperty("flatlaf.internal.fullWindowContent", true);
        Notifications.getInstance().setJFrame(this);
    }

    public static void showForm(Component component) {
        component.applyComponentOrientation(getInstance().getComponentOrientation());
        getInstance().mainFormPatient.showForm(component);
    }

    public static void showMainForm() {
        FlatAnimatedLafChange.showSnapshot();
        Launch app = getInstance();
        app.switchContent(app.mainFormPatient);
        setSelectedMenu(0, 0);
        app.mainFormPatient.hideMenu();
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }

    private void switchContent(Container content) {
        setContentPane(content);
        content.applyComponentOrientation(getComponentOrientation());
        SwingUtilities.updateComponentTreeUI(content);
    }

    public static void showLoginForm() {
        FlatAnimatedLafChange.showSnapshot();
        Launch app = getInstance();
        app.switchContent(app.loginForm);
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }

    public static void showSignUpForm() {
        FlatAnimatedLafChange.showSnapshot();
        Launch app = getInstance();
        app.switchContent(app.signUpForm);
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }

    public static void showOTPSignUpForm() {
        FlatAnimatedLafChange.showSnapshot();
        Launch app = getInstance();
        app.switchContent(app.otpSignUpForm);
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }

    public static void showOTPForgotPasswordForm() {
        FlatAnimatedLafChange.showSnapshot();
        Launch app = getInstance();
        app.switchContent(app.otpForgotPasswordForm);
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }

    public static void showForgotPasswordForm() {
        FlatAnimatedLafChange.showSnapshot();
        Launch app = getInstance();
        app.switchContent(app.forgotPasswordForm);
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }

    public static void showResetPasswordForm() {
        FlatAnimatedLafChange.showSnapshot();
        Launch app = getInstance();
        app.switchContent(app.resetPasswordForm);
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }

    public static void loadOTPSignUpInfo(Users user, String OTPCode) {
        getInstance().otpSignUpForm.setOTPCode(OTPCode);
        getInstance().otpSignUpForm.setUser(user);
    }

    public static void loadOTPForgotPasswordInfo(String email, String OTPCode) {
        getInstance().otpForgotPasswordForm.setEmail(email);
        getInstance().otpForgotPasswordForm.setOTPCode(OTPCode);
    }

    public static void loadResetPasswordInfo(String email) {
        getInstance().resetPasswordForm.setEmail(email);
    }

    public static void setSelectedMenu(int index, int subIndex) {
        getInstance().mainFormPatient.setSelectedMenu(index, subIndex);
    }

    private static Launch getInstance() {
        return instance;
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGap(0, 719, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGap(0, 521, Short.MAX_VALUE));

        pack();
    }

    public static void launch() {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("config/properties");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacDarkLaf.setup();

        EventQueue.invokeLater(() -> {
            instance = new Launch();
            // instance.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            instance.setVisible(true);
        });
    }
}
