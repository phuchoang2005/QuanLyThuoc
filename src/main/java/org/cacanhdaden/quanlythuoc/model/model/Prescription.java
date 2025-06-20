package org.cacanhdaden.quanlythuoc.model.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prescription {
    private String id;
    private String patient_id;
    private String doctor_id;
    private String diagnosis;
    private String notes;
    private String issue_date;
    private String created_at = new Date().toString();
}
