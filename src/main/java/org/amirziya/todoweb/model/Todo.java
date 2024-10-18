package org.amirziya.todoweb.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Todo {

    private  Long id;
    private String title;
    private String description;
    private boolean isDO;
}
