package org.cacanhdaden.quanlythuoc.view.doctor.menuDoctor.mode;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.icons.FlatAbstractIcon;
import com.formdev.flatlaf.util.ColorFunctions;
import com.formdev.flatlaf.util.LoggingFacade;
import com.formdev.flatlaf.util.UIScale;
import lombok.Getter;
import org.cacanhdaden.quanlythuoc.view.doctor.menuDoctor.Menu;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
@Getter
public class ToolBarAccentColor extends JPanel {

    private final Menu menu;
    private final JPopupMenu popup = new JPopupMenu();
    private final JToolBar toolbar = new JToolBar();
    private final JToggleButton selectedButton = new JToggleButton();
    private boolean menuFull;

    private static final String[] ACCENT_COLOR_KEYS = {
            "App.accent.default", "App.accent.blue", "App.accent.purple",
            "App.accent.red", "App.accent.orange", "App.accent.yellow", "App.accent.green"
    };

    private static final String[] ACCENT_COLOR_NAMES = {
            "Default", "Blue", "Purple", "Red", "Orange", "Yellow", "Green"
    };

    public ToolBarAccentColor(Menu menu) {
        this.menu = menu;
        initComponent();
    }

    public void setMenuFull(boolean menuFull) {
        this.menuFull = menuFull;
        removeAll();
        if (menuFull) {
            add(toolbar);
            popup.remove(toolbar);
        } else {
            add(selectedButton);
            popup.add(toolbar);
        }
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void show(Component com, int x, int y) {
        int adjustedX = menu.getComponentOrientation().isLeftToRight() ? x
                : -toolbar.getPreferredSize().width - UIScale.scale(5);
        popup.show(com, adjustedX, y);
        SwingUtilities.updateComponentTreeUI(popup);
    }

    private void initComponent() {
        setLayout(new BorderLayout());
        putClientProperty(FlatClientProperties.STYLE, "background:$Menu.background");

        toolbar.putClientProperty(FlatClientProperties.STYLE, "background:$Menu.background");
        popup.putClientProperty(FlatClientProperties.STYLE, "background:$Menu.background; borderColor:$Menu.background");

        add(toolbar);
        initToolbar();
    }

    private void initToolbar() {
        ButtonGroup group = new ButtonGroup();

        selectedButton.setIcon(new AccentColorIcon(ACCENT_COLOR_KEYS[0]));
        selectedButton.addActionListener(e -> {
            int yOffset = (selectedButton.getPreferredSize().height
                    - toolbar.getPreferredSize().height - UIScale.scale(10)) / 2;
            show(this, getWidth() + UIScale.scale(4), yOffset);
        });

        for (String colorKey : ACCENT_COLOR_KEYS) {
            JToggleButton btn = createAccentButton(colorKey);
            group.add(btn);
            toolbar.add(btn);
        }
    }

    private JToggleButton createAccentButton(String colorKey) {
        JToggleButton button = new JToggleButton(new AccentColorIcon(colorKey));
        boolean isSelected = UIManager.getColor("Component.accentColor").equals(UIManager.getColor(colorKey));
        button.setSelected(isSelected);
        button.addActionListener(e -> applyAccentColor(colorKey));
        return button;
    }

    private void applyAccentColor(String colorKey) {
        if (popup.isVisible()) {
            popup.setVisible(false);
        }
        selectedButton.setIcon(new AccentColorIcon(colorKey));

        Color color = UIManager.getColor(colorKey);
        if (color == null) return;

        try {
            Class<? extends LookAndFeel> lafClass = UIManager.getLookAndFeel().getClass();
            FlatLaf.setGlobalExtraDefaults(Collections.singletonMap("@accentColor", toHexCode(color)));
            FlatLaf.setup(lafClass.getDeclaredConstructor().newInstance());
            FlatLaf.updateUI();
        } catch (ReflectiveOperationException ex) {
            LoggingFacade.INSTANCE.logSevere(null, ex);
        }
    }

    private String toHexCode(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    // --- Icon class ---
    private static class AccentColorIcon extends FlatAbstractIcon {
        private final String colorKey;

        public AccentColorIcon(String colorKey) {
            super(16, 16, null);
            this.colorKey = colorKey;
        }

        @Override
        protected void paintIcon(Component c, Graphics2D g) {
            Color color = UIManager.getColor(colorKey);
            if (color == null) {
                color = Color.LIGHT_GRAY;
            } else if (!c.isEnabled()) {
                color = FlatLaf.isLafDark()
                        ? ColorFunctions.shade(color, 0.5f)
                        : ColorFunctions.tint(color, 0.6f);
            }
            g.setColor(color);
            g.fillRoundRect(1, 1, width - 2, height - 2, 5, 5);
        }
    }
}

