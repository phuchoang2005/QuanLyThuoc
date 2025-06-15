package org.cacanhdaden.quanlythuoc.model.model;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private String id;

    private String userName;

    private String fullName;

    private String email;

    private String phone;

    private int age;

    private String password_hash;

    private Role role;

    private LocalDateTime createdAt;

    public enum Role {
        DOCTOR, PATIENT
    }
}
