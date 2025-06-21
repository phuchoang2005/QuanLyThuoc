package org.cacanhdaden.quanlythuoc.view.authentication.form;


import com.formdev.flatlaf.FlatClientProperties;
import lombok.Getter;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

/**
 *
 * @author Raven
 */
@Getter
public class PanelSignUp extends JPanel {
    public PanelSignUp() {
        setLayout(new MigLayout("fillx,wrap,insets 30 40 50 40, width 320", "[fill]", "[]10[]10[]10[]10[]10[]10[]10[]10[]20[]10[]10[]"));
        putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Login.background;"
                + "arc:20;");
    }
}