package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import models.request.project.post.ProjectRequestModel;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;
import static pages.pageElements.Input.enter;

public class ProjectsPage extends BasePage {

    private static final SelenideElement PROJECTS_PAGE_TITLE = $x("//h1[normalize-space()='Projects']");
    private static final SelenideElement CREATE_PROJECT_BUTTON =
            $x("//button[.//span[text()='Create new project']]");
    private static final SelenideElement SAVE_PROJECT_BUTTON =
            $x("//button[.//span[text()='Create project']]");
    private static final SelenideElement DESCRIPTION_TEXT_AREA = $("#description-area");
    private static final SelenideElement TITLE_CREATED_PROJECT = $("h1[class*='pOpqJc']");
    private static final SelenideElement ERROR_MESSAGE = $(".FKqFlv");
    private static final SelenideElement BURGER_MENU = $("button[aria-label='Open action menu']");
    private static final SelenideElement REMOVE_BUTTON = $x("//*[@data-testid='remove']");
    private static final SelenideElement DELETE_BUTTON = $x("//button[.//span[text()='Delete project']]");
    private static final SelenideElement RADIOBUTTON_PRIVATE =
            $x("//label[.//span[text()='Private']]//input");
    private static final SelenideElement RADIOBUTTON_PUBLIC =
            $x("//label[.//span[text()='Public']]//input");
    String projectProjectsList = "//tr/ancestor::tbody//div/div/a[text()='%s']";

    @Step("Вернуться на страницу Projects")
    public ProjectsPage openProjectsPage() {
        open("/projects");
        return this;
    }

    @Step("Получить заголовок страницы проекта")
    public ProjectsPage shouldBeOpened() {
        PROJECTS_PAGE_TITLE.shouldBe(visible).shouldHave(text("Projects"));
        return this;
    }

    @Step("Нажать на кнопку Create New Project")
    public ProjectsPage clickCreateProjectButton() {
        CREATE_PROJECT_BUTTON.shouldBe(visible).click();
        return this;
    }

    @Step("Создать новый проект")
    public ProjectsPage createProject(ProjectRequestModel data) {
        enter("For example: Web Application", data.getTitle());
        enter("For example: WA", data.getCode());
        DESCRIPTION_TEXT_AREA.setValue(data.getDescription());
        return this;
    }

    @Step("Нажать на кнопку Create Project")
    public ProjectsPage clickSaveProjectButton() {
        SAVE_PROJECT_BUTTON.shouldBe(visible).click();
        return this;
    }
    @Step("Убедиться, что проект создан")
    public ProjectsPage checkThatTheProjectHasBeenCreated(String expectedProjectCode) {
        TITLE_CREATED_PROJECT.shouldBe(visible)
                .shouldHave(text(expectedProjectCode));
        return this;
    }
    @Step("Получить сообщение об ошибке при создании проекта с невалидными данными")
    public ProjectsPage checkThatTheProjectHasBeenNotCreated() {
        ERROR_MESSAGE.shouldBe(visible);
        return this;
    }

    @Step("Удалить созданный проект")
    public ProjectsPage deleteCreatedProject() {
        BURGER_MENU.shouldBe(visible).click();
        REMOVE_BUTTON.shouldBe(visible).click();
        DELETE_BUTTON.shouldBe(visible).click();
        return this;
    }

    @Step("Убедиться, что радиокнопка Private выбрана")
    public ProjectsPage checkRadioButtonPrivate() {
        RADIOBUTTON_PRIVATE.scrollIntoView(true)
                .shouldBe(selected);
        return this;
    }

    @Step("Убедиться, что радиокнопка Public не выбрана")
    public ProjectsPage chekRadioButtonPublic() {
        RADIOBUTTON_PUBLIC.scrollIntoView(true)
                .shouldNotBe(selected);
        return this;
    }

    @Step("Проверка отсутствия проекта в списке")
    public ProjectsPage checkThatProjectIsDeleted(String projectTitle){
        $x(format(projectProjectsList,projectTitle)).shouldNotBe(visible);
        return this;
    }
}
