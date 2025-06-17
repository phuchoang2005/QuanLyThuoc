package org.cacanhdaden.quanlythuoc.model.dto;

import lombok.Getter;
import lombok.Setter;

//add import for hashing the password if needed
import org.cacanhdaden.quanlythuoc.util.PasswordUtil;

@Getter
@Setter
public class LoginDTO {
    private String email;
    private String hashedPassword;

    public LoginDTO() {
    }

    public LoginDTO(String email, String password) {
        this.email = email;
        this.hashedPassword = PasswordUtil.hashPassword(password); // Assuming you have a utility to hash passwords
    }

    public boolean isValid() {
        return email != null && !email.isEmpty() && hashedPassword != null && !hashedPassword.isEmpty();
    }

}
