package org.cacanhdaden.quanlythuoc;

import com.formdev.flatlaf.FlatLightLaf;
import org.cacanhdaden.quanlythuoc.launch.Application;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> new Application());
    }
}