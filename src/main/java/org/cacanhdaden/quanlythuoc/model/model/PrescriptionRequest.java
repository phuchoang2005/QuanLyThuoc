package org.cacanhdaden.quanlythuoc.model.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionRequest {
    private String id;
    private String patient_id;
    private String doctor_id;
    private String reason;
    private StatusRequestEnum status =  StatusRequestEnum.PENDING;
    private String doctor_notes;
    private String created_at = String.valueOf(java.sql.Date.valueOf(LocalDate.now()));
    private String updated_at;
    public enum StatusRequestEnum{
        PENDING, APPROVED, REJECTED
    }
}
