package org.cacanhdaden.quanlythuoc.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoadDoctorDTO {
    private String id;
    private String full_name;

    @Override
    public String toString() {
        return this.full_name;
    }

}
