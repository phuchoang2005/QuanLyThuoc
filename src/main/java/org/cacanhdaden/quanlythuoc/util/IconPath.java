package org.cacanhdaden.quanlythuoc.util;

import lombok.Data;
import lombok.Getter;

@Data
public class IconPath {
    private final String lightModeIconPath;
    private final String darkModeIconPath;
    private final String svgIconPath;
    private final String svgIconMenuPath;

    public IconPath(){
        lightModeIconPath = "images/DarkMode_LightMode/light.svg";
        darkModeIconPath = "images/DarkMode_LightMode/dark.svg";
        svgIconPath = "images/icon/svg/";
        svgIconMenuPath = "images/iconMenu/";
    }
}
