package org.cacanhdaden.quanlythuoc.model.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    private String id;
    private String email;
    private String hashedPassword;
    private String full_name;
    private String date_of_birth;
    private String gender;
    private String phone_number;
    private String address;
    private RoleStatusEnum status;
    private String created_at = String.valueOf(Date.valueOf(LocalDate.now()));
    private String updated_at;
    public Users(final String email, final String hashedPassword){
        this.email = email;
        this.hashedPassword = hashedPassword;
    }
    public Users(final String id, final String full_name, final RoleStatusEnum role){
        this.id = id;
        this.full_name = full_name;
        this.status = role;
    }
    public enum RoleStatusEnum{
        PATIENT, DOCTOR
    }

    @Override
    public String toString(){
        return this.full_name;
    }
}
