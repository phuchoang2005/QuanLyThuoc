package org.cacanhdaden.quanlythuoc.view.login.menu;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import lombok.Getter;
import lombok.Setter;
import org.cacanhdaden.quanlythuoc.util.IconPathUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.util.List;
@Getter
@Setter
public class MenuItem extends JPanel {

    // Constants
    private static final int MENU_ITEM_HEIGHT = 38;
    private static final int SUB_MENU_ITEM_HEIGHT = 35;
    private static final int SUB_MENU_LEFT_GAP = 34;
    private static final int FIRST_GAP = 5;
    private static final int BOTTOM_GAP = 5;

    // Core fields
    private final List<MenuEvent> events;
    private final Menu menu;
    private final String[] menuTexts;
    private final int menuIndex;
    private boolean menuVisible = false;
    private float animationProgress = 0f;
    private PopupSubmenu popup;
    private final IconPathUtil iconPath;
    // Constructor
    public MenuItem(Menu menu, String[] menuTexts, int menuIndex, List<MenuEvent> events) {
        iconPath = new IconPathUtil();
        this.menu = menu;
        this.menuTexts = menuTexts;
        this.menuIndex = menuIndex;
        this.events = events;
        initMenu();
    }

    public void hideMenu() {
        this.animationProgress = 0f;
        this.menuVisible = false;
    }

    public void setFullMode(boolean full) {
        for (int i = 0; i < getComponentCount(); i++) {
            if (getComponent(i) instanceof JButton btn) {
                btn.setText(full ? menuTexts[i] : "");
                btn.setHorizontalAlignment(full
                        ? getComponentOrientation().isLeftToRight() ? JButton.LEFT : JButton.RIGHT
                        : JButton.CENTER);
            }
        }
        if (!full) hideMenu();
    }

    public void setSelectedIndex(int index) {
        for (int i = 0; i < getComponentCount(); i++) {
            if (getComponent(i) instanceof JButton btn) {
                btn.setSelected(i == index);
            }
        }
        ((JButton) getComponent(0)).setSelected(index > 0);
        popup.setSelectedIndex(index);
    }

    // Initialization
    private void initMenu() {
        setLayout(new MenuLayout());
        putClientProperty(FlatClientProperties.STYLE, "background:$Menu.background;foreground:$Menu.lineColor");

        for (int i = 0; i < menuTexts.length; i++) {
            JButton btn = createMenuButton(menuTexts[i]);
            btn.setHorizontalAlignment(getComponentOrientation().isLeftToRight() ? JButton.LEADING : JButton.TRAILING);

            if (i == 0) {
                btn.setIcon(loadIcon(menuIndex));
                btn.addActionListener(e -> onMainButtonClick(btn));
            } else {
                final int subIndex = i;
                btn.addActionListener(e -> menu.runEvent(menuIndex, subIndex));
            }

            add(btn);
        }

        popup = new PopupSubmenu(getComponentOrientation(), menu, menuIndex, menuTexts);
    }

    private void onMainButtonClick(JButton source) {
        if (menuTexts.length <= 1) {
            menu.runEvent(menuIndex, 0);
        } else if (menu.isMenuFull()) {
            MenuAnimation.animate(this, !menuVisible);
        } else {
            popup.show(this, getWidth() + UIScale.scale(5), UIScale.scale(MENU_ITEM_HEIGHT) / 2);
        }
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.putClientProperty(FlatClientProperties.STYLE, """
                background:$Menu.background;
                foreground:$Menu.foreground;
                selectedBackground:$Menu.button.selectedBackground;
                selectedForeground:$Menu.button.selectedForeground;
                borderWidth:0;
                focusWidth:0;
                innerFocusWidth:0;
                arc:10;
                iconTextGap:10;
                margin:3,11,3,11
                """);
        return button;
    }

    private Icon loadIcon(int index) {
        Color light = FlatUIUtils.getUIColor("Menu.icon.lightColor", Color.LIGHT_GRAY);
        Color dark = FlatUIUtils.getUIColor("Menu.icon.darkColor", Color.LIGHT_GRAY);
        FlatSVGIcon icon = new FlatSVGIcon(iconPath.getSvgIconMenuPath() + index + ".svg");
        FlatSVGIcon.ColorFilter filter = new FlatSVGIcon.ColorFilter();
        filter.add(Color.decode("#969696"), light, dark);
        icon.setColorFilter(filter);
        return icon;
    }

    // Paint
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (animationProgress <= 0) return;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int itemHeight = UIScale.scale(MENU_ITEM_HEIGHT);
        int gap = UIScale.scale(FIRST_GAP);
        int lastY = getComponent(getComponentCount() - 1).getY() + (UIScale.scale(SUB_MENU_ITEM_HEIGHT) / 2);
        boolean ltr = getComponentOrientation().isLeftToRight();
        int round = UIScale.scale(10);
        int x = ltr ? UIScale.scale(SUB_MENU_LEFT_GAP) - round : getWidth() - UIScale.scale(SUB_MENU_LEFT_GAP) + round;

        Path2D path = new Path2D.Double();
        path.moveTo(x, itemHeight + gap);
        path.lineTo(x, lastY - round);

        for (int i = 1; i < getComponentCount(); i++) {
            int y = getComponent(i).getY() + (UIScale.scale(SUB_MENU_ITEM_HEIGHT) / 2);
            path.append(createCurve(round, x, y, ltr), false);
        }

        g2.setColor(getForeground());
        g2.setStroke(new BasicStroke(UIScale.scale(1f)));
        g2.draw(path);
        g2.dispose();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (menuTexts.length <= 1) return;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2.setColor(FlatUIUtils.getUIColor("Menu.arrowColor", getForeground()));
        g2.setStroke(new BasicStroke(UIScale.scale(1f)));

        boolean ltr = getComponentOrientation().isLeftToRight();
        int itemHeight = UIScale.scale(MENU_ITEM_HEIGHT);
        int ax, ay;

        Path2D arrow = new Path2D.Double();
        if (menu.isMenuFull()) {
            int w = UIScale.scale(10), h = UIScale.scale(5);
            ax = ltr ? getWidth() - w * 2 : w;
            ay = (itemHeight - h) / 2;
            arrow.moveTo(0, animationProgress * h);
            arrow.lineTo(w / 2f, (1f - animationProgress) * h);
            arrow.lineTo(w, animationProgress * h);
        } else {
            int w = UIScale.scale(4), h = UIScale.scale(8);
            ax = ltr ? getWidth() - w - UIScale.scale(3) : UIScale.scale(3);
            ay = (itemHeight - h) / 2;
            if (ltr) {
                arrow.moveTo(0, 0);
                arrow.lineTo(w, h / 2f);
                arrow.lineTo(0, h);
            } else {
                arrow.moveTo(w, 0);
                arrow.lineTo(0, h / 2f);
                arrow.lineTo(w, h);
            }
        }

        g2.translate(ax, ay);
        g2.draw(arrow);
        g2.dispose();
    }

    private Shape createCurve(int round, int x, int y, boolean ltr) {
        Path2D curve = new Path2D.Double();
        curve.moveTo(x, y - round);
        curve.curveTo(x, y - round, x, y, x + (ltr ? round : -round), y);
        return curve;
    }

    // Custom layout class
    private class MenuLayout implements LayoutManager {
        @Override public void addLayoutComponent(String name, Component comp) {}
        @Override public void removeLayoutComponent(Component comp) {}

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                Insets insets = parent.getInsets();
                int height = insets.top + insets.bottom;
                int width = parent.getWidth();

                Component topItem = parent.getComponent(0);
                if (!topItem.isVisible()) return new Dimension(width, 0);

                height += UIScale.scale(MENU_ITEM_HEIGHT);

                int extraHeight = (menuTexts.length > 1)
                        ? UIScale.scale(FIRST_GAP) + UIScale.scale(BOTTOM_GAP)
                        : 0;

                for (int i = 1; i < parent.getComponentCount(); i++) {
                    if (parent.getComponent(i).isVisible()) {
                        extraHeight += UIScale.scale(SUB_MENU_ITEM_HEIGHT);
                    }
                }

                height += extraHeight * animationProgress;
                return new Dimension(width, height);
            }
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            return new Dimension(0, 0);
        }

        @Override
        public void layoutContainer(Container parent) {
            synchronized (parent.getTreeLock()) {
                Insets insets = parent.getInsets();
                int x = insets.left;
                int y = insets.top;
                int width = parent.getWidth() - insets.left - insets.right;
                boolean ltr = parent.getComponentOrientation().isLeftToRight();

                for (int i = 0; i < parent.getComponentCount(); i++) {
                    Component comp = parent.getComponent(i);
                    if (!comp.isVisible()) continue;

                    if (i == 0) {
                        int h = UIScale.scale(MENU_ITEM_HEIGHT);
                        comp.setBounds(x, y, width, h);
                        y += h + UIScale.scale(FIRST_GAP);
                    } else {
                        int leftGap = UIScale.scale(SUB_MENU_LEFT_GAP);
                        int subX = ltr ? x + leftGap : x;
                        int subW = width - leftGap;
                        int h = UIScale.scale(SUB_MENU_ITEM_HEIGHT);
                        comp.setBounds(subX, y, subW, h);
                        y += h;
                    }
                }
            }
        }
    }
}
