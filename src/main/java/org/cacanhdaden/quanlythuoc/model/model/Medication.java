package org.cacanhdaden.quanlythuoc.model.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medication {

    private String id;

    private String name;

    private String unit;

    private String description;
}
