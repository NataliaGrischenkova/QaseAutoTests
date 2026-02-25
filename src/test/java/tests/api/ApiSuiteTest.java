package tests.api;

import api.steps.ProjectSteps;
import io.qameta.allure.*;
import models.ProjectFactory;
import models.SuiteFactory;
import models.request.project.post.ProjectRequestModel;
import models.request.suite.post.SuiteRequestModel;
import models.response.suite.delete.SuiteDeleteResponseModel;
import models.response.suite.get.SuiteGetSuitesResponseModel;
import models.response.suite.post.SuiteCreateResponseModel;
import org.junit.jupiter.api.*;
import tests.BaseTest;

import static api.steps.SuiteSteps.*;
import static org.assertj.core.api.Assertions.assertThat;

@Owner("natalia")
@Feature("Suite API")
@Link(value = "GitHub репозиторий проекта", url = "https://github.com/NataliaGrischenkova/QaseAutoTests")
public class ApiSuiteTest extends BaseTest {

    @Test
    @DisplayName("Проверка успешного создания сьюта")
    @Story("Управление сьютами")
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
    @DisplayName("Проверка успешного удаления сьюта")
    @Story("Управление сьютами")
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
    @DisplayName("Проверка получения всех сьютов проекта")
    @Story("Управление сьютами")
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
