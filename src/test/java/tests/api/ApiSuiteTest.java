package tests.api;

import api.steps.ProjectSteps;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import models.ProjectFactory;
import models.SuiteFactory;
import models.request.project.post.ProjectRequestModel;
import models.request.suite.post.SuiteRequestModel;
import models.response.suite.delete.SuiteDeleteResponseModel;
import models.response.suite.get.SuiteGetSuitesResponseModel;
import models.response.suite.post.SuiteCreateResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import static api.steps.SuiteSteps.*;
import static org.assertj.core.api.Assertions.assertThat;

@Feature("Suite")
public class ApiSuiteTest extends BaseTest {

    @Test
    @DisplayName("Успешное создание сьюта")
    @Story("Список Suite")
    @Severity(SeverityLevel.NORMAL)
    @Tags({
            @Tag("NORMAL"),
            @Tag("API-test"),
            @Tag("Suite")
    })
    void shouldCreateSuiteSuccessfully() {
        ProjectRequestModel projectData = ProjectFactory.randomProject();
        ProjectSteps.createProject(projectData, 200);
        String projectCode = projectData.getCode();

        SuiteRequestModel suiteRequest = SuiteFactory.randomSuite();

        SuiteCreateResponseModel response = createSuite(projectCode, suiteRequest);

        assertThat(response)
                .isNotNull()
                .extracting(SuiteCreateResponseModel::isStatus)
                .isEqualTo(true);
    }

    @Test
    @DisplayName("Успешное удаление сьюта")
    @Story("Список Suite")
    @Severity(SeverityLevel.NORMAL)
    @Tags({
            @Tag("NORMAL"),
            @Tag("API-test"),
            @Tag("Suite")
    })
    void shouldDeleteSuiteSuccessfully() {
        ProjectRequestModel projectData = ProjectFactory.randomProject();
        ProjectSteps.createProject(projectData, 200);
        String projectCode = projectData.getCode();

        SuiteRequestModel suiteRequest = SuiteFactory.randomSuite();

        SuiteCreateResponseModel createResponse =
                createSuite(projectCode, suiteRequest);

        Integer suiteId = createResponse.getResult().getId();

        SuiteDeleteResponseModel deleteResponse =
                deleteSuite(projectCode, suiteId);

        assertThat(deleteResponse)
                .isNotNull()
                .extracting(SuiteDeleteResponseModel::isStatus)
                .isEqualTo(true);
    }

    @Test
    @DisplayName("Получение всех сьютов проекта")
    @Story("Список Suite")
    @Severity(SeverityLevel.NORMAL)
    @Tags({
            @Tag("NORMAL"),
            @Tag("API-test"),
            @Tag("Suite")
    })
    void shouldReturnAllSuitesForProject() {
        ProjectRequestModel projectData = ProjectFactory.randomProject();
        ProjectSteps.createProject(projectData, 200);
        String projectCode = projectData.getCode();

        SuiteGetSuitesResponseModel suiteResponse =
                getSuites(projectCode);

        assertThat(suiteResponse)
                .isNotNull()
                .extracting(SuiteGetSuitesResponseModel::isStatus)
                .isEqualTo(true);

        assertThat(suiteResponse)
                .extracting(SuiteGetSuitesResponseModel::getResult)
                .isNotNull();
    }
}
