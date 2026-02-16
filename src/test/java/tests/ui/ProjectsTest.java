package tests.ui;

import io.qameta.allure.*;
import models.CreateProjectFactory;
import models.request.project.post.ProjectRequestModel;
import org.junit.jupiter.api.*;
import tests.BaseTest;
import tests.api.steps.ProjectSteps;

import static io.qameta.allure.Allure.step;

@Feature("Project")
public class ProjectsTest extends BaseTest {

    @BeforeEach
    void openLoginPage() {
        step("Открыть страницу авторизации",
                ()-> signInPage.openPage("/login"));
    }

    @Test
    @DisplayName("Проверка создания нового проекта с валидными данными")
    @Story("Создание нового проекта")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({
            @Tag("BLOCKER"),
            @Tag("UI-test"),
            @Tag("Project")
    })
    public void projectMustBeCreated() {
        signInPage.setEmail(email)
                .setPassword(password)
                .clickSignInButton();

        projectsPage.clickCreateProjectButton();

        ProjectRequestModel createProject = CreateProjectFactory.getRandomData();
        String expectedProjectCode = createProject.getCode();

        projectsPage.createProject(createProject)
                .clickSaveProjectButton()
                .checkThatTheProjectHasBeenCreated(expectedProjectCode);

        projectFactory.deleteProject(expectedProjectCode, 200);
    }

    @Test
    @DisplayName("Проверка удаления проекта")
    @Story("Удаление проекта")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({
            @Tag("BLOCKER"),
            @Tag("UI-test"),
            @Tag("Project")
    })
    public void projectMustBeDeleted() {
        signInPage.setEmail(email)
                .setPassword(password)
                .clickSignInButton();

        ProjectRequestModel projectData = CreateProjectFactory.getRandomData();
        String projectTitle = projectData.getTitle();

        ProjectSteps.createProject(projectData, 200);

        try {
            projectsPage .openProjectsPage()
                    .deleteCreatedProject()
                    .checkThatProjectIsDeleted(projectTitle);
        } catch (Exception e) {
            ProjectSteps.deleteProject(projectData.getCode(), 200);
        }
    }
}
