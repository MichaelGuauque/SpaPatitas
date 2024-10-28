package com.spapatitas.persistence.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionEntity {

    private Long id;
    private String name;
}
