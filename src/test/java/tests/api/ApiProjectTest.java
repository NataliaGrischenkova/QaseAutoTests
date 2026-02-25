package tests.api;

import api.steps.ProjectSteps;
import io.qameta.allure.*;
import io.restassured.response.ValidatableResponse;
import models.ProjectFactory;
import models.request.project.post.ProjectRequestModel;
import models.response.project.delete.ProjectDeleteResponseModel;
import models.response.project.get.ProjectGetResponseModel;
import models.response.project.post.CreateSuiteResult;
import models.response.project.post.ProjectCreateResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import static api.steps.ProjectSteps.deleteProject;
import static api.steps.ProjectSteps.getProjects;
import static org.assertj.core.api.Assertions.assertThat;

@Owner("natalia")
@Feature("Project API")
@Link(value = "GitHub репозиторий проекта", url = "https://github.com/NataliaGrischenkova/QaseAutoTests")
public class ApiProjectTest extends BaseTest {

    @Test
    @DisplayName("Проверка создания нового проекта с валидными данными")
    @Story("Управление проектами")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({
            @Tag("BLOCKER"),
            @Tag("API-test"),
            @Tag("Project")
    })
    void shouldCreateProjectSuccessfully() {
        ProjectRequestModel data = ProjectFactory.randomProject();
        ProjectCreateResponseModel response = ProjectSteps.createProject(data, 200)
                .extract()
                .as(ProjectCreateResponseModel.class);

        assertThat(response)
                .isNotNull()
                .extracting(ProjectCreateResponseModel::getResult)
                .extracting(CreateSuiteResult::getCode)
                .isEqualTo(data.getCode().toUpperCase());

        projectFactory.deleteProject(data.getCode(), 200);
    }

    @Test
    @DisplayName("Проверка успешного удаления проекта")
    @Story("Управление проектами")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({
            @Tag("BLOCKER"),
            @Tag("API-test"),
            @Tag("Project")
    })
    void shouldDeleteProjectSuccessfully() {
        ProjectRequestModel data = ProjectFactory.randomProject();
        ValidatableResponse response = ProjectSteps.createProject(data, 200);
        ProjectCreateResponseModel projectCreateResponseModel = response.extract().as(ProjectCreateResponseModel.class);
        ProjectDeleteResponseModel deleteResponse = deleteProject(projectCreateResponseModel.getResult().getCode(), 200)
                .extract()
                .as(ProjectDeleteResponseModel.class);

        assertThat(deleteResponse)
                .isNotNull()
                .extracting(ProjectDeleteResponseModel::isStatus)
                .isEqualTo(true);
    }

    @Test
    @DisplayName("Проверка успешного статуса при получении списка проектов")
    @Story("Управление проектами")
    @Severity(SeverityLevel.NORMAL)
    @Tags({
            @Tag("NORMAL"),
            @Tag("API-test"),
            @Tag("Project")
    })
    void shouldReturnProjectsListWithSuccessStatus() {
        ProjectGetResponseModel response = getProjects()
                .extract().as(ProjectGetResponseModel.class);

        assertThat(response)
                .isNotNull()
                .extracting(ProjectGetResponseModel::isStatus)
                .isEqualTo(true);
    }
}
