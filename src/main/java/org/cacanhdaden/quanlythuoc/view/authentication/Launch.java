package org.cacanhdaden.quanlythuoc.view.authentication;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import lombok.Getter;
import org.cacanhdaden.quanlythuoc.view.authentication.form.LoginForm;
import org.cacanhdaden.quanlythuoc.view.authentication.form.SignUpForm;
import org.cacanhdaden.quanlythuoc.view.doctor.MainFormDoctor;
import org.cacanhdaden.quanlythuoc.view.patient.MainFormPatient;
import raven.toast.Notifications;

import javax.swing.*;
import java.awt.*;

@Getter
public class Launch extends JFrame {

    private static Launch instance;
    private final MainFormDoctor mainFormDoctor;
    private final LoginForm loginForm;
    private final SignUpForm signUpForm;

    public Launch() {
        this.mainFormDoctor = new MainFormDoctor();
        this.loginForm = new LoginForm();
        this.signUpForm = new SignUpForm();

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
        getInstance().mainFormDoctor.showForm(component);
    }

    public static void showMainForm() {
        FlatAnimatedLafChange.showSnapshot();
        Launch app = getInstance();
        app.switchContent(app.mainFormDoctor);
        setSelectedMenu(0, 0);
        app.mainFormDoctor.hideMenu();
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

    public static void setSelectedMenu(int index, int subIndex) {
        getInstance().mainFormDoctor.setSelectedMenu(index, subIndex);
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
