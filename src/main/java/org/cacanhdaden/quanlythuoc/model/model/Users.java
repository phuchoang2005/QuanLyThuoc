package org.cacanhdaden.quanlythuoc.model.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    private String id;
    private String email;
    private String hashedPassword;
    private String fullName;
    private String dateOfBirth;
    private GenderEnum gender;
    private String phoneNumber;
    private String address;
    private RoleEnum role;
    private String created_at = new Date().toString();
    private String updated_at;

    public Users(String email, String hashedPassword) {
        this.email = email;
        this.hashedPassword = hashedPassword;
    }
    public enum RoleEnum{
        PATIENT, DOCTER
    }
    public enum GenderEnum{
      MALE,FEMALE,OTHER
    };
}
