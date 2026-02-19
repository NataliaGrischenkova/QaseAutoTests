package models;


import models.request.project.post.ProjectRequestModel;
import com.github.javafaker.Faker;
import api.steps.ProjectSteps;

public class ProjectFactory {

    static Faker faker = new Faker();
    public ProjectRequestModel project = ProjectFactory.randomProject();

    public static ProjectRequestModel randomProject() {
        return ProjectRequestModel.builder()
                .title(faker.name().name())
                .code(faker.bothify("???"))
                .description(faker.lorem().sentence())
                .build();
    }

    public void deleteProject(String projectCode, Integer statusCode) {
        ProjectSteps.deleteProject(projectCode, statusCode);
    }
}
