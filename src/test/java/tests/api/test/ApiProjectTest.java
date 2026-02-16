package tests.api.test;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import models.CreateProjectFactory;
import models.request.project.post.ProjectRequestModel;
import models.responce.project.delete.ProjectDeleteResponseModel;
import models.responce.project.get.ProjectGetResponseModel;
import models.responce.project.post.ProjectCreateResponseModel;
import models.responce.project.post.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import tests.BaseTest;
import tests.api.steps.ProjectSteps;

import static org.assertj.core.api.Assertions.assertThat;
import static tests.api.steps.ProjectSteps.deleteProject;
import static tests.api.steps.ProjectSteps.getProjects;

@Feature("Project")
public class ApiProjectTest extends BaseTest {

    @Test
    @DisplayName("Проверка создания нового проекта с валидным телом запроса")
    @Story("Создание проекта через API")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({
            @Tag("BLOCKER"),
            @Tag("API-test"),
            @Tag("Project")
    })
    void projectMustBeCreatedWithApi() {
        ProjectRequestModel data = CreateProjectFactory.getRandomData();
        ProjectCreateResponseModel response = ProjectSteps.createProject(data, 200)
                .extract()
                .as(ProjectCreateResponseModel.class);

        assertThat(response)
                .isNotNull()
                .extracting(ProjectCreateResponseModel::getResult)
                .extracting(Result::getCode)
                .isEqualTo(data.getCode().toUpperCase());

        projectFactory.deleteProject(data.getCode(), 200);
    }

    @Test
    @DisplayName("Проверка удаления проекта")
    @Story("Удаление проекта через API")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({
            @Tag("BLOCKER"),
            @Tag("API-test"),
            @Tag("Project")
    })
    void projectMustBeDeletedWithApi() {
        ProjectRequestModel data = CreateProjectFactory.getRandomData();
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
    @DisplayName("Проверка успешного статуса в ответе при получении списка проектов")
    @Story("Получение списка проектов")
    @Severity(SeverityLevel.NORMAL)
    @Tags({
            @Tag("NORMAL"),
            @Tag("API-test"),
            @Tag("Project")
    })
    void getAllProjectsAndVerifyStatus() {
        ProjectGetResponseModel response = getProjects()
                .extract().as(ProjectGetResponseModel.class);

        assertThat(response)
                .isNotNull()
                .extracting(ProjectGetResponseModel::isStatus)
                .isEqualTo(true);
    }
}
