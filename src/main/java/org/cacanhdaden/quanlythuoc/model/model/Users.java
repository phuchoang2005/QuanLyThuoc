package org.cacanhdaden.quanlythuoc.model.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cacanhdaden.quanlythuoc.util.PasswordUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private String id;
    private String email;
    private String password;
    private String hashedPassword;
    private String fullName;
    private String dateOfBirth;
    private GenderEnum gender;
    private String phoneNumber;
    private String address;
    private RoleEnum role;
    private String created_at = sdf.format(new Date());
    private String updated_at;

    public static Users EmailUsers (String email, String password) {
        Users user = new Users();
        user.setEmail(email);
        user.setPassword(password);
        user.setHashedPassword(PasswordUtil.hashPassword(password));
        return user;
    }

    public static Users PhoneNumberUsers (String phoneNumber, String password) {
        Users user = new Users();
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);
        user.setHashedPassword(PasswordUtil.hashPassword(password));
        return user;
    }

    public Users (
            String email,
            String password,
            String fullName,
            String dateOfBirth,
            String Gender,
            String phoneNumber,
            String address
    ) {
        this.email = email;
        this.password = password;
        this.hashedPassword = PasswordUtil.hashPassword(password);
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.gender = GenderEnum.valueOf(Gender);
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = RoleEnum.PATIENT; // Default role is PATIENT
    }

    public enum RoleEnum{
        PATIENT, DOCTOR
    }
    public enum GenderEnum{
        MALE,FEMALE
    };
}
