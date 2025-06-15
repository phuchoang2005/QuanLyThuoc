package org.cacanhdaden.quanlythuoc.model.model;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private String id;

    private String fullName;

    private String email;

    private String password;

    private String phone;


    private Role role;

    private LocalDateTime createdAt;

    public enum Role {
        DOCTOR, PATIENT
    }
}
