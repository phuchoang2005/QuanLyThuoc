package org.cacanhdaden.quanlythuoc.model.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor_profiles {
    private String id;
    private String speciality;
    private String license_number;
    private String clinic_address;
}
