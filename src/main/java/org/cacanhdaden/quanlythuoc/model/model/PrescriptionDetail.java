package org.cacanhdaden.quanlythuoc.model.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionDetail {
    private long detailId;
    private long prescriptionId;
    private long medicineId;
    private String medicineName;
    private String dosage;
    private String frequency;
    private int quantity;
    private String duration;
    private String notes;
}
