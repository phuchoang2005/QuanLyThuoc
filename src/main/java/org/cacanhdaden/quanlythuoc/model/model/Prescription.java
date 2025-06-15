package org.cacanhdaden.quanlythuoc.model.model;


import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prescription {

    private String id;

    private User doctor;

    private User patient;

    private String diagnosis;

    private LocalDateTime createdAt;
}
