package tests.ui;

import api.steps.ProjectSteps;
import io.qameta.allure.*;
import models.ProjectFactory;
import models.SuiteFactory;
import models.request.project.post.ProjectRequestModel;
import models.request.suite.post.SuiteRequestModel;
import org.junit.jupiter.api.*;
import tests.BaseTest;


@Owner("natalia")
@Feature("Suite UI")
@Link(value = "GitHub репозиторий проекта", url = "https://github.com/NataliaGrischenkova/QaseAutoTests")
public class SuiteTest extends BaseTest {

    @BeforeEach
    void cleanProjects() {
        login(email, password);
        projectsPage.deleteAllProjects();
    }

    @Test
    @DisplayName("Проверка создания сьюты с валидными данными")
    @Story("Управление сьютами")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({
            @Tag("BLOCKER"),
            @Tag("UI-test"),
            @Tag("Suite")
    })
    public void shouldCreateSuiteSuccessfully() {
        ProjectRequestModel projectData = ProjectFactory.randomProject();
        ProjectSteps.createProject(projectData, 200);
        String projectCode = projectData.getCode();

        SuiteRequestModel suiteData = SuiteFactory.randomSuite();

        suitePage.openSuitePage(projectCode.toUpperCase())
                .clickCreateNewSuiteButton()
                .fillSuiteCreationForm(suiteData)
                .clickCreateSuiteButton()
                .verifySuiteCreated();
    }

    @Test
    @DisplayName("Проверка удаления сьюты")
    @Story("Управление сьютами")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({
            @Tag("BLOCKER"),
            @Tag("UI-test"),
            @Tag("Suite")
    })
    public void shouldDeleteSuiteSuccessfully() {
        ProjectRequestModel projectData = ProjectFactory.randomProject();
        ProjectSteps.createProject(projectData, 200);
        String projectCode = projectData.getCode();

        SuiteRequestModel suiteData = SuiteFactory.randomSuite();

        suitePage.openSuitePage(projectCode.toUpperCase())
                .clickCreateNewSuiteButton()
                .fillSuiteCreationForm(suiteData)
                .clickCreateSuiteButton()
                .verifySuiteCreated()
                .deleteSuite()
                .verifySuiteDeleted();
    }
}
