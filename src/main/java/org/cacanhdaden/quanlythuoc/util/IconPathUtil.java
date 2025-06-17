package org.cacanhdaden.quanlythuoc.util;

import lombok.Data;

@Data
public class IconPathUtil {
    private final String lightModeIconPath;
    private final String darkModeIconPath;
    private final String svgIconPath;
    private final String svgIconMenuPath;

    public IconPathUtil(){
        lightModeIconPath = "images/DarkMode_LightMode/light.svg";
        darkModeIconPath = "images/DarkMode_LightMode/dark.svg";
        svgIconPath = "images/icon/svg/";
        svgIconMenuPath = "images/iconMenu/";
    }
}
