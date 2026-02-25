package pages;

import models.response.project.get.Entity;
import models.response.project.get.ProjectGetResponseModel;

import static api.steps.ProjectSteps.deleteProject;
import static api.steps.ProjectSteps.getProjects;
import static com.codeborne.selenide.Selenide.open;

public class BasePage {

    public void openPage(String endpoint) {
        open(endpoint);
    }

    public void deleteAllProjects() {
        ProjectGetResponseModel response = getProjects()
                .extract().as(ProjectGetResponseModel.class);

        if (response.getResult().getTotal() > 0) {
            response.getResult().getEntities().stream()
                    .map(Entity::getCode)
                    .forEach(code -> deleteProject(code, 200));
        } else {
            System.out.println("Нет созданных проектов");
        }
    }
}
