package org.cacanhdaden.quanlythuoc.model.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Drugs {
    private String id;
    private String api_drug_id;
    private String name;
    private String description;
    private String dosage_form;
    private String manufacturer;
    private String created_at = new Date().toString();
}
