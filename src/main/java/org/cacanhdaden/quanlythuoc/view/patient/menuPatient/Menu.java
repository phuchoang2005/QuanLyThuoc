package org.cacanhdaden.quanlythuoc.view.patient.menuPatient;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import lombok.Data;
import org.cacanhdaden.quanlythuoc.view.patient.menuPatient.mode.LightDarkMode;
import org.cacanhdaden.quanlythuoc.view.patient.menuPatient.mode.ToolBarAccentColor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class Menu extends JPanel {

    private static final String[][] MENU_ITEMS = {
            {"~Trang chủ~"},
            {"Thông tin cá nhân"},
            {"~Đơn thuốc~"},
            {"Yêu cầu kê đơn", "Chi tiết đơn thuốc", "Lịch sử dùng thuốc", "Yêu cầu kê đơn"},
            {"Tra cứu trực tuyến"},
            {"Quản lý thông tin bệnh nhân"},
            {"Logout"}
    };

    private final List<MenuEvent> events = new ArrayList<>();
    private final String headerName = "Quản lý toa thuốc";

    private JLabel header;
    private JScrollPane scroll;
    private JPanel panelMenu;
    private LightDarkMode lightDarkMode;
    private ToolBarAccentColor toolBarAccentColor;

    private boolean menuFull = true;

    protected final boolean hideMenuTitleOnMinimum = true;
    protected final int menuTitleLeftInset = 5;
    protected final int menuTitleVgap = 5;
    protected final int menuMaxWidth = 250;
    protected final int menuMinWidth = 60;
    protected final int headerFullHgap = 5;

    public Menu() {
        initComponents();
        layoutComponents();
        buildMenu();
    }

    private void initComponents() {
        setLayout(new MenuLayout());
        putClientProperty(FlatClientProperties.STYLE,
                "border:20,2,2,2; background:$Menu.background; arc:10");

        header = createHeaderLabel();
        panelMenu = createMenuPanel();
        scroll = createScrollPane(panelMenu);
        lightDarkMode = new LightDarkMode();
        toolBarAccentColor = new ToolBarAccentColor(this);
        toolBarAccentColor.setVisible(FlatUIUtils.getUIBoolean("AccentControl.show", false));
    }

    private void layoutComponents() {
        add(header);
        add(scroll);
        add(lightDarkMode);
        add(toolBarAccentColor);
    }

    private void buildMenu() {
        int index = 0;
        for (String[] itemGroup : MENU_ITEMS) {
            String first = itemGroup[0];
            if (first.startsWith("~") && first.endsWith("~")) {
                panelMenu.add(createTitleLabel(first));
            } else {
                panelMenu.add(new MenuItem(this, itemGroup, index++, events));
            }
        }
    }

    private JLabel createHeaderLabel() {
        JLabel label = new JLabel(headerName);
        label.setIcon(new ImageIcon(getClass().getResource("/images/icon/png/logo.png")));
        label.putClientProperty(FlatClientProperties.STYLE,
                "font:$Menu.header.font; foreground:$Menu.foreground");
        return label;
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel(new MenuItemLayout(this));
        panel.putClientProperty(FlatClientProperties.STYLE,
                "border:5,5,5,5; background:$Menu.background");
        return panel;
    }

    private JScrollPane createScrollPane(JPanel content) {
        JScrollPane pane = new JScrollPane(content);
        JScrollBar vScroll = pane.getVerticalScrollBar();
        vScroll.setUnitIncrement(10);
        vScroll.putClientProperty(FlatClientProperties.STYLE,
                "width:$Menu.scroll.width; trackInsets:$Menu.scroll.trackInsets;" +
                        "thumbInsets:$Menu.scroll.thumbInsets; background:$Menu.ScrollBar.background;" +
                        "thumb:$Menu.ScrollBar.thumb");
        pane.putClientProperty(FlatClientProperties.STYLE, "border:null");
        return pane;
    }

    private JLabel createTitleLabel(String rawTitle) {
        String title = rawTitle.substring(1, rawTitle.length() - 1);
        JLabel label = new JLabel(title);
        label.putClientProperty(FlatClientProperties.STYLE,
                "font:$Menu.label.font; foreground:$Menu.title.foreground");
        return label;
    }

    public void setMenuFull(boolean menuFull) {
        this.menuFull = menuFull;
        header.setText(menuFull ? headerName : "");
        header.setHorizontalAlignment(menuFull
                ? (getComponentOrientation().isLeftToRight() ? JLabel.LEFT : JLabel.RIGHT)
                : JLabel.CENTER);

        for (Component com : panelMenu.getComponents()) {
            if (com instanceof MenuItem) {
                ((MenuItem) com).setFullMode(menuFull);
            }
        }
        lightDarkMode.setMenuFull(menuFull);
        toolBarAccentColor.setMenuFull(menuFull);
    }

    public void addMenuEvent(MenuEvent event) {
        events.add(event);
    }

    public void setSelectedMenu(int index, int subIndex) {
        runEvent(index, subIndex);
    }

    protected void runEvent(int index, int subIndex) {
        MenuAction action = new MenuAction();
        for (MenuEvent event : events) {
            event.menuSelected(index, subIndex, action);
        }
        if (!action.isCancel()) {
            setSelected(index, subIndex);
        }
    }

    protected void setSelected(int index, int subIndex) {
        for (Component com : panelMenu.getComponents()) {
            if (com instanceof MenuItem item) {
                item.setSelectedIndex(item.getMenuIndex() == index ? subIndex : -1);
            }
        }
    }

    public void hideMenuItem() {
        for (Component com : panelMenu.getComponents()) {
            if (com instanceof MenuItem) {
                ((MenuItem) com).hideMenu();
            }
        }
        revalidate();
    }

    private class MenuLayout implements LayoutManager {
        @Override
        public void addLayoutComponent(String name, Component comp) {}
        @Override
        public void removeLayoutComponent(Component comp) {}

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
            Insets insets = parent.getInsets();
            int width = parent.getWidth() - (insets.left + insets.right);
            int height = parent.getHeight() - (insets.top + insets.bottom);
            int gap = UIScale.scale(5);
            int hgap = menuFull ? UIScale.scale(headerFullHgap) : 0;

            int headerHeight = header.getPreferredSize().height;
            header.setBounds(insets.left + hgap, insets.top, width - (hgap * 2), headerHeight);

            int ldGap = UIScale.scale(10);
            int ldHeight = lightDarkMode.getPreferredSize().height;
            int accentHeight = toolBarAccentColor.isVisible()
                    ? toolBarAccentColor.getPreferredSize().height + gap
                    : 0;

            int scrollY = insets.top + headerHeight + gap;
            int scrollHeight = height - (headerHeight + gap + ldHeight + ldGap * 2 + accentHeight);
            scroll.setBounds(insets.left, scrollY, width, scrollHeight);

            int ldx = insets.left + ldGap;
            int ldy = insets.top + height - ldHeight - ldGap - accentHeight;
            int ldWidth = width - ldGap * 2;
            lightDarkMode.setBounds(ldx, ldy, ldWidth, ldHeight);

            if (toolBarAccentColor.isVisible()) {
                int tbHeight = toolBarAccentColor.getPreferredSize().height;
                int tbWidth = Math.min(toolBarAccentColor.getPreferredSize().width, ldWidth);
                int tbX = ldx + (ldWidth - tbWidth) / 2;
                int tbY = insets.top + height - tbHeight - ldGap;
                toolBarAccentColor.setBounds(tbX, tbY, tbWidth, tbHeight);
            }
        }
    }
}

