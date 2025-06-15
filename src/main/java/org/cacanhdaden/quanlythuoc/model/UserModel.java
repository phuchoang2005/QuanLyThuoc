package org.cacanhdaden.quanlythuoc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private String IdenName, UserName, Email, KVucSinhSong, TienSuBenhLy;
    private int Tuoi;
}
