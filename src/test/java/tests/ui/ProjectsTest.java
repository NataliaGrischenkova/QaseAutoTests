package tests.ui;

import api.steps.ProjectSteps;
import io.qameta.allure.*;
import models.ProjectFactory;
import models.request.project.post.ProjectRequestModel;
import org.junit.jupiter.api.*;
import tests.BaseTest;

@Owner("natalia")
@Feature("Project UI")
@Link(value = "GitHub репозиторий проекта", url = "https://github.com/NataliaGrischenkova/QaseAutoTests")
public class ProjectsTest extends BaseTest {

    @BeforeEach
    void cleanProjects() {
        login(email, password);
        projectsPage.deleteAllProjects();
    }

    @Test
    @DisplayName("Проверка создания нового проекта с валидными данными")
    @Story("Управление проектами")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({
            @Tag("BLOCKER"),
            @Tag("UI-test"),
            @Tag("Project")
    })
    public void shouldCreateProjectSuccessfully() {
        projectsPage.clickCreateProjectButton();

        ProjectRequestModel createProject = ProjectFactory.randomProject();
        String expectedProjectCode = createProject.getCode();

        projectsPage.createProject(createProject)
                .clickSaveProjectButton()
                .shouldSeeProject(expectedProjectCode);
    }

    @Test
    @DisplayName("Проверка удаления проекта")
    @Story("Управление проектами")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({
            @Tag("BLOCKER"),
            @Tag("UI-test"),
            @Tag("Project")
    })
    public void shouldDeleteProjectSuccessfully() {
        ProjectRequestModel projectData = ProjectFactory.randomProject();
        String projectTitle = projectData.getTitle();

        ProjectSteps.createProject(projectData, 200);

        projectsPage.openProjectsPage()
                .deleteProject()
                .shouldNotSeeProject(projectTitle);
    }
}
