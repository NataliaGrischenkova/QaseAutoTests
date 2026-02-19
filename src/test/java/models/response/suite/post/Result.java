package models.response.suite.post;

import lombok.Data;

@Data
public class Result {
    private boolean status;
    private SuiteCreateResult result;
}
