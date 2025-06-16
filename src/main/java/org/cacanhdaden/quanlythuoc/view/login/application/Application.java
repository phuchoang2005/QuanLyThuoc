package org.cacanhdaden.quanlythuoc.view.login.application;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.FlatClientProperties;
import org.cacanhdaden.quanlythuoc.view.login.application.form.LoginForm;
import org.cacanhdaden.quanlythuoc.view.login.application.form.MainForm;
import raven.toast.Notifications;

import javax.swing.*;
import java.awt.*;
public class Application extends JFrame {

    private static Application instance;
    private final MainForm mainForm;
    private final LoginForm loginForm;

    public Application() {
        this.mainForm = new MainForm();
        this.loginForm = new LoginForm();

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
        getInstance().mainForm.showForm(component);
    }

    public static void login() {
        FlatAnimatedLafChange.showSnapshot();
        Application app = getInstance();
        app.switchContent(app.mainForm);
        setSelectedMenu(0, 0);
        app.mainForm.hideMenu();
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }

    public static void logout() {
        FlatAnimatedLafChange.showSnapshot();
        Application app = getInstance();
        app.switchContent(app.loginForm);
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }

    private void switchContent(Container content) {
        setContentPane(content);
        content.applyComponentOrientation(getComponentOrientation());
        SwingUtilities.updateComponentTreeUI(content);
    }



    public static void setSelectedMenu(int index, int subIndex) {
        getInstance().mainForm.setSelectedMenu(index, subIndex);
    }

    private static Application getInstance() {
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

    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("raven.theme");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacDarkLaf.setup();

        EventQueue.invokeLater(() -> {
            instance = new Application();
            // instance.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            instance.setVisible(true);
        });
    }
}
