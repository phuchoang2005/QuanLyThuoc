package org.cacanhdaden.quanlythuoc.view.login.menu;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.util.UIScale;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class PopupSubmenu extends JPanel {
    private final Menu menu;
    private final int menuIndex;
    private final String[] menus;
    private final int subMenuLeftGap = 20;
    private final int subMenuItemHeight = 30;
    private final JPopupMenu popup;

    public PopupSubmenu(ComponentOrientation orientation, Menu menu, int menuIndex, String[] menus) {
        this.menu = menu;
        this.menuIndex = menuIndex;
        this.menus = menus;
        applyComponentOrientation(orientation);
        this.popup = createPopupMenu();
        initMenuItems();
    }

    private JPopupMenu createPopupMenu() {
        JPopupMenu popup = new JPopupMenu();
        popup.putClientProperty(FlatClientProperties.STYLE,
                "background:$Menu.background; borderColor:$Menu.background;");
        popup.add(this);
        putClientProperty(FlatClientProperties.STYLE,
                "border:0,3,0,3; background:$Menu.background; foreground:$Menu.lineColor");
        setLayout(new MenuLayout());
        return popup;
    }

    private void initMenuItems() {
        for (int i = 1; i < menus.length; i++) {
            JButton button = createButtonItem(menus[i]);
            final int subIndex = i;
            button.addActionListener(e -> {
                menu.runEvent(menuIndex, subIndex);
                popup.setVisible(false);
            });
            add(button);
        }
    }

    private JButton createButtonItem(String text) {
        JButton button = new JButton(text);
        button.putClientProperty(FlatClientProperties.STYLE,
                "background:$Menu.background; foreground:$Menu.foreground;"
                        + "selectedBackground:$Menu.button.selectedBackground; selectedForeground:$Menu.button.selectedForeground;"
                        + "borderWidth:0; arc:10; focusWidth:0; iconTextGap:10; margin:5,11,5,11");
        return button;
    }

    public void show(Component parent, int x, int y) {
        int adjustedX = getComponentOrientation().isLeftToRight() ? x : -getPreferredSize().width - UIScale.scale(5);
        popup.show(parent, adjustedX, y);
        applyAlignment();
        SwingUtilities.updateComponentTreeUI(popup);
    }

    private void applyAlignment() {
        for (Component c : getComponents()) {
            if (c instanceof JButton btn) {
                btn.setHorizontalAlignment(getComponentOrientation().isLeftToRight() ? JButton.LEFT : JButton.RIGHT);
            }
        }
    }

    public void setSelectedIndex(int index) {
        for (int i = 0; i < getComponentCount(); i++) {
            if (getComponent(i) instanceof JButton btn) {
                btn.setSelected(i == index - 1);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        int height = getComponent(getComponentCount() - 1).getY() + UIScale.scale(subMenuItemHeight / 2);
        int gap = UIScale.scale(subMenuLeftGap);
        int round = UIScale.scale(10);
        int x = getComponentOrientation().isLeftToRight() ? gap - round : getWidth() - (gap - round);

        Path2D path = new Path2D.Double();
        path.moveTo(x, 0);
        path.lineTo(x, height - round);

        for (Component comp : getComponents()) {
            int y = comp.getY() + UIScale.scale(subMenuItemHeight / 2);
            path.append(createCurve(round, x, y, getComponentOrientation().isLeftToRight()), false);
        }

        g2.setColor(getForeground());
        g2.setStroke(new BasicStroke(UIScale.scale(1f)));
        g2.draw(path);
        g2.dispose();
    }

    private Shape createCurve(int round, int x, int y, boolean ltr) {
        Path2D curve = new Path2D.Double();
        curve.moveTo(x, y - round);
        curve.curveTo(x, y - round, x, y, x + (ltr ? round : -round), y);
        return curve;
    }

    private class MenuLayout implements LayoutManager {
        @Override
        public void addLayoutComponent(String name, Component comp) {}
        @Override
        public void removeLayoutComponent(Component comp) {}

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            Insets insets = parent.getInsets();
            int width = getMaxComponentWidth(parent) + UIScale.scale(subMenuLeftGap) + insets.left + insets.right;
            int height = insets.top + insets.bottom + getVisibleComponentCount(parent) * UIScale.scale(subMenuItemHeight);
            return new Dimension(Math.max(width, UIScale.scale(150)), height);
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            return new Dimension(0, 0);
        }

        @Override
        public void layoutContainer(Container parent) {
            boolean ltr = parent.getComponentOrientation().isLeftToRight();
            Insets insets = parent.getInsets();
            int x = insets.left + (ltr ? UIScale.scale(subMenuLeftGap) : 0);
            int y = insets.top;
            int width = getMaxComponentWidth(parent);
            int itemHeight = UIScale.scale(subMenuItemHeight);

            for (Component comp : parent.getComponents()) {
                if (comp.isVisible()) {
                    comp.setBounds(x, y, width, itemHeight);
                    y += itemHeight;
                }
            }
        }

        private int getVisibleComponentCount(Container parent) {
            int count = 0;
            for (Component comp : parent.getComponents()) {
                if (comp.isVisible()) count++;
            }
            return count;
        }

        private int getMaxComponentWidth(Container parent) {
            int max = 0;
            for (Component comp : parent.getComponents()) {
                if (comp.isVisible()) {
                    max = Math.max(max, comp.getPreferredSize().width);
                }
            }
            return Math.max(max, UIScale.scale(150));
        }
    }
}
