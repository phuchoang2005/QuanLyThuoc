package org.cacanhdaden.quanlythuoc.model.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionDetail {
    private String id;
    private String prescription_id;
    private String drug_id;
    private String dosage;
    private String frequency;
    private String quantity;
    private String duration;
    private String notes;
}
