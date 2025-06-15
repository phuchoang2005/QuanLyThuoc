package org.cacanhdaden.quanlythuoc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusModel {
    private boolean AutoStartUp, AutoClearHistory, NotiOnLowLevel, EnableConfirmButton, EnabelSkipButton;
}
