package models.response.suite.get;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Entity {
    public int id;
    public String title;
    public Object description;
    public Object preconditions;
    public int position;
    @JsonProperty("cases_count")
    public int casesCount;
    @JsonProperty("parent_id")
    public Object parentId;
    public String created;
    public String updated;
    @JsonProperty("created_at")
    public Date createdAt;
    @JsonProperty("update_at")
    public Date updateAt;
}
