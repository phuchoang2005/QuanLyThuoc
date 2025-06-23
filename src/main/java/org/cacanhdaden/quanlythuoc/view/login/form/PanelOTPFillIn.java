package org.cacanhdaden.quanlythuoc.view.login.form;


import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

/**
 *
 * @author Raven
 */
public class PanelOTPFillIn extends JPanel {

    public PanelOTPFillIn() {
        setLayout(new MigLayout("fillx,wrap,insets 30 40 50 40, width 320", "[fill]", "[]20[][]10[][]"));
        putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Login.background;"
                + "arc:20;");
    }

}