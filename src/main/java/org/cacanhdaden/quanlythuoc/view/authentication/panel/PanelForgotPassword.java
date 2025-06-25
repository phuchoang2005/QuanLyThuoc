package org.cacanhdaden.quanlythuoc.view.authentication.panel;


import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

/**
 *
 * @author Raven
 */
public class PanelForgotPassword extends JPanel {

    public PanelForgotPassword() {
        setLayout(new MigLayout("fillx,wrap,insets 30 40 50 40, width 320", "[fill]", "[]30[][]30[]"));
        putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Login.background;"
                + "arc:20;");
    }

}