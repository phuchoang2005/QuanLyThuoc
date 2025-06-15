package org.cacanhdaden.quanlythuoc.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.cacanhdaden.quanlythuoc.model.model.User;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewPatientDTO {
    private String id;

    private String userName;

    private String fullName;

    private String email;

    private String phone;

    private int age;

    private String password_no_hash;

    private User.Role role = User.Role.PATIENT;
}
