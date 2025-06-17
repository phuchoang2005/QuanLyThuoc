package org.cacanhdaden.quanlythuoc.view.login.menu.mode;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import lombok.Getter;
import org.cacanhdaden.quanlythuoc.util.IconPathUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
@Getter
public class LightDarkMode extends JPanel {

    private boolean menuFull = true;
    private final JButton buttonLight;
    private final JButton buttonDark;
    private final JButton buttonLightDark;
    private final IconPathUtil iconPath;
    public LightDarkMode() {
        iconPath = new IconPathUtil();
        setBorder(new EmptyBorder(2, 2, 2, 2));
        setLayout(new LightDarkModeLayout());
        putClientProperty(FlatClientProperties.STYLE,
                "arc:999; background:$Menu.lightdark.background");

        buttonLight = createButton("Light", iconPath.getLightModeIconPath(), false);
        buttonDark = createButton("Dark", iconPath.getDarkModeIconPath(), true);
        buttonLightDark = createToggleButton();

        add(buttonLight);
        add(buttonDark);
        add(buttonLightDark);

        updateStyle();
        updateVisibility();
    }

    public void setMenuFull(boolean menuFull) {
        this.menuFull = menuFull;
        updateVisibility();
    }

    private void updateVisibility() {
        buttonLight.setVisible(menuFull);
        buttonDark.setVisible(menuFull);
        buttonLightDark.setVisible(!menuFull);
    }

    private JButton createButton(String text, String iconPath, boolean darkMode) {
        JButton button = new JButton(text, new FlatSVGIcon(iconPath));
        button.addActionListener(e -> changeMode(darkMode));
        return button;
    }

    private JButton createToggleButton() {
        JButton button = new JButton();
        button.putClientProperty(FlatClientProperties.STYLE,
                "arc:999; background:$Menu.lightdark.button.background; foreground:$Menu.foreground;"
                        + "focusWidth:0; borderWidth:0; innerFocusWidth:0");
        button.addActionListener(e -> changeMode(!FlatLaf.isLafDark()));
        return button;
    }

    private void changeMode(boolean dark) {
        if (FlatLaf.isLafDark() == dark) return;

        EventQueue.invokeLater(() -> {
            FlatAnimatedLafChange.showSnapshot();
            if (dark) {
                FlatMacDarkLaf.setup();
            } else {
                FlatMacLightLaf.setup();
            }
            FlatLaf.updateUI();
            updateStyle();
            FlatAnimatedLafChange.hideSnapshotWithAnimation();
        });
    }

    private void updateStyle() {
        boolean isDark = FlatLaf.isLafDark();
        applyStyle(buttonLight, !isDark);
        applyStyle(buttonDark, isDark);
        buttonLightDark.setIcon(new FlatSVGIcon(isDark ? iconPath.getDarkModeIconPath() : iconPath.getLightModeIconPath()));
    }

    private void applyStyle(JButton button, boolean active) {
        String style = "arc:999; foreground:$Menu.foreground; focusWidth:0; borderWidth:0; innerFocusWidth:0; "
                + "background:$Menu.lightdark.button.background";
        if (!active) {
            style += "; background:null";
        }
        button.putClientProperty(FlatClientProperties.STYLE, style);
    }

    // --- Layout class ---
    private class LightDarkModeLayout implements LayoutManager {
        @Override public void addLayoutComponent(String name, Component comp) {}
        @Override public void removeLayoutComponent(Component comp) {}

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            int height = buttonDark.getPreferredSize().height + (menuFull ? 0 : 5);
            return new Dimension(5, height);
        }

        @Override public Dimension minimumLayoutSize(Container parent) {
            return new Dimension(0, 0);
        }

        @Override
        public void layoutContainer(Container parent) {
            Insets insets = parent.getInsets();
            int width = parent.getWidth() - insets.left - insets.right;
            int height = parent.getHeight() - insets.top - insets.bottom;
            int x = insets.left;
            int y = insets.top;
            int gap = 5;

            if (menuFull) {
                int buttonWidth = (width - gap) / 2;
                buttonLight.setBounds(x, y, buttonWidth, height);
                buttonDark.setBounds(x + buttonWidth + gap, y, buttonWidth, height);
            } else {
                buttonLightDark.setBounds(x, y, width, height);
            }
        }
    }
}
