package models;


import models.request.project.post.ProjectRequestModel;
import com.github.javafaker.Faker;
import tests.api.steps.ProjectSteps;

public class CreateProjectFactory {

    static Faker faker = new Faker();
    public ProjectRequestModel project = CreateProjectFactory.getRandomData();

    public static ProjectRequestModel getRandomData() {
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
