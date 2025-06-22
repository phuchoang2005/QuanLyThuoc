package org.cacanhdaden.quanlythuoc.view.patient;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.util.UIScale;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import lombok.Data;
import lombok.Getter;
import org.cacanhdaden.quanlythuoc.util.IconPathUtil;
import org.cacanhdaden.quanlythuoc.view.authentication.Launch;
import org.cacanhdaden.quanlythuoc.view.patient.features.*;
import org.cacanhdaden.quanlythuoc.view.patient.menuPatient.Menu;

@Getter
public class MainFormPatient extends JLayeredPane {

    private final Menu menu;
    private final JPanel panelBody;
    private final JButton menuButton;
    private final IconPathUtil iconPath;
    public MainFormPatient() {
        this.menu = new Menu();
        iconPath = new IconPathUtil();
        this.panelBody = new JPanel(new BorderLayout());
        this.menuButton = new JButton();
        init();
    }

    private void init() {
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new MainFormLayout());
        initMenuButton();
        initMenu();
        add(menuButton, POPUP_LAYER);
        add(menu);
        add(panelBody);
    }

    private void initMenuButton() {
        menuButton.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Menu.button.background;"
                + "arc:999;"
                + "focusWidth:0;"
                + "borderWidth:0");

        menuButton.addActionListener(e -> toggleMenu());

        setMenuButtonIcon(menu.isMenuFull());
    }

    private void initMenu() {
        menu.addMenuEvent((index, subIndex, action) -> {
            switch (index) {
                case 0 -> Launch.showForm(new FormDashboard());
                case 1 -> {
                    switch (subIndex) {
                        case 1 -> Launch.showForm(new FormInbox());
                        case 2 -> Launch.showForm(new FormRead());
                        case 3 -> Launch.showForm(new RequestPrescriptionPanel());
                        default -> action.cancel();
                    }
                }
                case 3 -> Launch.showForm(new PatientManagerPanel());
                case 4 -> Launch.showLoginForm();
                default -> action.cancel();
            }
        });
    }

    private void toggleMenu() {
        boolean full = !menu.isMenuFull();
        setMenuButtonIcon(full);
        menu.setMenuFull(full);
        revalidate();
    }

    private void setMenuButtonIcon(boolean full) {
        String iconName = full ^ getComponentOrientation().isLeftToRight()
                ? "menu_right.svg"
                : "menu_left.svg";
        menuButton.setIcon(new FlatSVGIcon(iconPath.getSvgIconPath() + iconName, 0.8f));
    }

    public void hideMenu() {
        menu.hideMenuItem();
    }

    public void showForm(Component component) {
        panelBody.removeAll();
        panelBody.add(component);
        panelBody.repaint();
        panelBody.revalidate();
    }

    public void setSelectedMenu(int index, int subIndex) {
        menu.setSelectedMenu(index, subIndex);
    }

    @Override
    public void applyComponentOrientation(ComponentOrientation o) {
        super.applyComponentOrientation(o);
        setMenuButtonIcon(menu.isMenuFull());
    }

    // Inner layout class for MainForm
    private class MainFormLayout implements LayoutManager {
        @Override public void addLayoutComponent(String name, Component comp) {}
        @Override public void removeLayoutComponent(Component comp) {}

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            return new Dimension(5, 5);
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            return new Dimension(0, 0);
        }

        @Override
        public void layoutContainer(Container parent) {
            synchronized (parent.getTreeLock()) {
                boolean ltr = parent.getComponentOrientation().isLeftToRight();
                Insets insets = UIScale.scale(parent.getInsets());

                int x = insets.left;
                int y = insets.top;
                int width = parent.getWidth() - insets.left - insets.right;
                int height = parent.getHeight() - insets.top - insets.bottom;

                int menuWidth = com.formdev.flatlaf.util.UIScale.scale(menu.isMenuFull() ? menu.getMenuMaxWidth() : menu.getMenuMinWidth());
                int menuX = ltr ? x : x + width - menuWidth;

                menu.setBounds(menuX, y, menuWidth, height);

                int btnWidth = menuButton.getPreferredSize().width;
                int btnHeight = menuButton.getPreferredSize().height;
                float offsetFactor = menu.isMenuFull() ? 0.5f : (ltr ? 0.3f : 0.7f);
                int btnX = ltr
                        ? (int) (x + menuWidth - btnWidth * offsetFactor)
                        : (int) (menuX - btnWidth * offsetFactor);

                menuButton.setBounds(btnX, UIScale.scale(30), btnWidth, btnHeight);

                int gap = UIScale.scale(5);
                int bodyX = ltr ? x + menuWidth + gap : x;
                int bodyWidth = width - menuWidth - gap;

                panelBody.setBounds(bodyX, y, bodyWidth, height);
            }
        }
    }
}

