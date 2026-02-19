package models.response.project.post;

import lombok.Data;

@Data
public class ProjectCreateResponseModel {

    private boolean status;
    private CreateSuiteResult result;
}
