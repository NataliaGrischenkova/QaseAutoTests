package tests.ui;

import io.qameta.allure.*;
import models.ProjectFactory;
import models.request.project.post.ProjectRequestModel;
import org.junit.jupiter.api.*;
import tests.BaseTest;
import api.steps.ProjectSteps;

import static io.qameta.allure.Allure.step;
import static api.steps.ProjectSteps.deleteProject;

@Feature("Project")
public class ProjectsTest extends BaseTest {

    @BeforeEach
    void deleteAllProjectsIfNeeded() {
        step("Удалить все проекты",
                ()-> projectsPage.deleteAllProjects());
    }

    @BeforeEach
    void openLoginPage() {
        step("Открыть страницу авторизации",
                ()-> loginPage.openPage("/login"));
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
        loginPage.setEmail(email)
                .setPassword(password)
                .clickSignInButton();

        projectsPage.clickCreateProjectButton();

        ProjectRequestModel createProject = ProjectFactory.randomProject();
        String expectedProjectCode = createProject.getCode();

        projectsPage.createProject(createProject)
                .clickSaveProjectButton()
                .shouldSeeProject(expectedProjectCode);

        deleteProject(expectedProjectCode, 200);
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
        loginPage.setEmail(email)
                .setPassword(password)
                .clickSignInButton();

        ProjectRequestModel projectData = ProjectFactory.randomProject();
        String projectTitle = projectData.getTitle();

        ProjectSteps.createProject(projectData, 200);

        try {
            projectsPage .openProjectsPage()
                    .deleteProject()
                    .shouldNotSeeProject(projectTitle);
        } catch (Exception e) {
            deleteProject(projectData.getCode(), 200);
        }
    }
}
