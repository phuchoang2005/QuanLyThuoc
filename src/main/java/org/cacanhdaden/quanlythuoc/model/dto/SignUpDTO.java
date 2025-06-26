package org.cacanhdaden.quanlythuoc.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDTO {
    private String email;
    private String password;
    private String fullName;
    private String dateOfBirth;
    private String gender;
    private String phoneNumber;
    private String address;
}
