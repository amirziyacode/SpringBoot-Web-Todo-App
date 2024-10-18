package org.amirziya.todoweb.model;


import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Todo {

    private UUID id;
    private String title;
    private String description;
    private boolean isDO;
}
