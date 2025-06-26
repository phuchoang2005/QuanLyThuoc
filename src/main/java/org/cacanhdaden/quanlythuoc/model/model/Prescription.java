package org.cacanhdaden.quanlythuoc.model.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prescription {
    private long prescriptionId;
    private long patientId;
    private long doctorId;
    private String diagnosis;
    private String notes;
    private LocalDateTime createdAt;
    private String status;
    private String patientName; // Để hiển thị trong bảng
}
