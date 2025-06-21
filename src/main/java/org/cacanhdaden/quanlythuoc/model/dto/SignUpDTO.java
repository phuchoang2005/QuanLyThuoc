package org.cacanhdaden.quanlythuoc.model.dto;

import org.cacanhdaden.quanlythuoc.util.PasswordUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpDTO {
    private String Email;
    private String Password;
    private String hashedPassword;
    private String FullName;
    private String DateOfBirth;
    private String Gender;
    private String PhoneNumber;
    private String Address;

    public SignUpDTO() {

    }

    public SignUpDTO (
            String email,
            String password,
            String fullName,
            String dateOfBirth,
            String Gender,
            String phoneNumber,
            String address
    ) {
        this.Email = email;
        this.Password = password;
        this.hashedPassword = PasswordUtil.hashPassword(this.Password);
        this.FullName = fullName;
        this.DateOfBirth = dateOfBirth;
        this.Gender = Gender;
        this.PhoneNumber = phoneNumber;
        this.Address = address;
    }

    public boolean isValid() {
        return Email != null && !Email.isEmpty() &&
                Password != null && !Password.isEmpty() &&
                FullName != null && !FullName.isEmpty() &&
                DateOfBirth != null && !DateOfBirth.isEmpty() &&
                Gender != null && !Gender.isEmpty() &&
                PhoneNumber != null && !PhoneNumber.isEmpty() &&
                Address != null && !Address.isEmpty();
    }
}
