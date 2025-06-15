package org.cacanhdaden.quanlythuoc.model.model;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionDetail {

    private Long id;

    private Prescription prescription;

    private Medication medication;

    private String dosage;

    private int durationDays;
}

