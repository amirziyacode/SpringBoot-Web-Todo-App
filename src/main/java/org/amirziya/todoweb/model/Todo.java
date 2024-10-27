package org.amirziya.todoweb.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

    private UUID id;
    private String title;
    private String description;
    private boolean isDO;
}
