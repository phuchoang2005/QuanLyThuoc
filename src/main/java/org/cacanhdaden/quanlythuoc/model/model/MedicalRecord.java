package org.cacanhdaden.quanlythuoc.model.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecord {
    private long recordId;
    private long patientId;
    private String patientName;
    private String diagnosis;
    private String symptoms;
    private String treatment;
    private LocalDateTime visitDate;
    private String notes;
}
