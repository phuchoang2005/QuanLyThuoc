package org.cacanhdaden.quanlythuoc.model.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    private long patientId;
    private String fullName;
    private int age;
    private String gender;
    private LocalDate lastVisit;
    private String email;
    private String phone;
}