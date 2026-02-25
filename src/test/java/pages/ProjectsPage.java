package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import models.request.project.post.ProjectRequestModel;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;
import static pages.pageElements.Input.setValueByPlaceholder;

public class ProjectsPage extends BasePage {

    private static final SelenideElement PROJECTS_PAGE_TITLE = $x("//h1[normalize-space()='Projects']");
    private static final SelenideElement CREATE_NEW_PROJECT_BUTTON =
            $x("//button[.//span[text()='Create new project']]");
    private static final SelenideElement CREATE_PROJECT_BUTTON =
            $x("//button[.//span[text()='Create project']]");
    private static final SelenideElement DESCRIPTION_AREA = $("#description-area");
    private static final SelenideElement TITLE_PROJECTS_PAGE = $x("//div[@id='application-content']//h1");
    private static final SelenideElement ACTION_MENU = $("button[aria-label='Open action menu']");
    private static final SelenideElement REMOVE_BUTTON = $x("//*[@data-testid='remove']");
    private static final SelenideElement DELETE_BUTTON = $x("//button[.//span[text()='Delete project']]");

    String projectProjectsList = "//tr/ancestor::tbody//div/div/a[text()='%s']";

    @Step("Перейти на страницу Projects")
    public ProjectsPage openProjectsPage() {
        open("/projects");
        return this;
    }

    @Step("Проверить, что страница проектов открыта")
    public ProjectsPage shouldBeOpened() {
        PROJECTS_PAGE_TITLE.shouldBe(visible).shouldHave(text("Projects"));
        return this;
    }

    @Step("Начать создание нового проекта")
    public ProjectsPage clickCreateProjectButton() {
        CREATE_NEW_PROJECT_BUTTON.shouldBe(visible).click();
        return this;
    }

    @Step("Заполнить форму создания проекта")
    public ProjectsPage createProject(ProjectRequestModel data) {
        setValueByPlaceholder("For example: Web Application", data.getTitle());
        setValueByPlaceholder("For example: WA", data.getCode());
        DESCRIPTION_AREA.setValue(data.getDescription());
        return this;
    }

    @Step("Подтвердить создание проекта")
    public ProjectsPage clickSaveProjectButton() {
        CREATE_PROJECT_BUTTON.shouldBe(visible).click();
        return this;
    }

    @Step("Убедиться, что проект создан")
    public ProjectsPage shouldSeeProject(String expectedProjectCode) {
        TITLE_PROJECTS_PAGE.shouldBe(visible)
                .shouldHave(text(expectedProjectCode));
        return this;
    }

    @Step("Удалить созданный проект")
    public ProjectsPage deleteProject() {
        clickBurgerMenuButton();
        REMOVE_BUTTON.shouldBe(visible).click();
        DELETE_BUTTON.shouldBe(visible).click();
        return this;
    }

    @Step("Открыть меню")
    public ProjectsPage clickBurgerMenuButton() {
        ACTION_MENU.shouldBe(visible).click();
        return this;
    }

    @Step("Проверить, что проект отсутствует в списке")
    public void shouldNotSeeProject(String projectTitle) {
        $x(format(projectProjectsList, projectTitle)).shouldNotBe(visible);
    }
}
