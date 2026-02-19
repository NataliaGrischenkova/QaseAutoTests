package models.response.project.get;

import lombok.Data;

@Data
public class Entity{
    private String title;
    private String code;
    private Counts counts;
}