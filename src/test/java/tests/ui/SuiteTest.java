package tests.ui;

import api.steps.ProjectSteps;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import models.ProjectFactory;
import models.SuiteFactory;
import models.request.project.post.ProjectRequestModel;
import models.request.suite.post.SuiteRequestModel;
import org.junit.jupiter.api.*;
import tests.BaseTest;

import static io.qameta.allure.Allure.step;


@Feature("Suite")
public class SuiteTest extends BaseTest {

    @BeforeEach
    void openLoginPage() {
        step("Открыть страницу авторизации",
                ()-> loginPage.openPage("/login"));
    }

    @Test
    @DisplayName("Проверка создания сьюты с валидными данными")
    @Story("Создание сьюты")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({
            @Tag("BLOCKER"),
            @Tag("UI-test"),
            @Tag("Suite")
    })
    public void suiteMustBeCreatedWithValidData() {
        loginPage.setEmail(email)
                .setPassword(password)
                .clickSignInButton();

        ProjectRequestModel projectData = ProjectFactory.randomProject();
        ProjectSteps.createProject(projectData, 200);
        String projectCode = projectData.getCode();

        SuiteRequestModel suiteData = SuiteFactory.randomSuite();

        suitePage.openSuitePage(projectCode.toUpperCase())
                .clickButtonCreateNewSuite()
                .fillFieldsToCreateSuite(suiteData)
                .clickCreateButton()
                .checkTheSuiteIsCreated();
    }

    @Test
    @DisplayName("Проверка удаления сьюты")
    @Story("Удаление сьюты")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({
            @Tag("BLOCKER"),
            @Tag("UI-test"),
            @Tag("Suite")
    })
    public void suiteMustBeDeleted() {
        loginPage.setEmail(email)
                .setPassword(password)
                .clickSignInButton();

        ProjectRequestModel projectData = ProjectFactory.randomProject();
        ProjectSteps.createProject(projectData, 200);
        String projectCode = projectData.getCode();

        SuiteRequestModel suiteData = SuiteFactory.randomSuite();

        suitePage.openSuitePage(projectCode.toUpperCase())
                .clickButtonCreateNewSuite()
                .fillFieldsToCreateSuite(suiteData)
                .clickCreateButton()
                .checkTheSuiteIsCreated()
                .deleteSuite()
                .checkTheSuiteIsDeleted();
    }
}
