package models;


import api.steps.ProjectSteps;
import com.github.javafaker.Faker;
import models.request.project.post.ProjectRequestModel;

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
