package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import models.request.suite.post.SuiteRequestModel;
import pages.pageElements.Button;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static pages.pageElements.Input.setValueByPlaceholder;

public class SuitePage extends BasePage {

    private static final SelenideElement SUITE_LABEL = $x("//h3[text()='Suites']");
    private static final SelenideElement PRECONDITIONS_INPUT = $x("//div[@aria-label='Preconditions']/div");
    private static final SelenideElement DESCRIPTION = $x("//div[@id='description']/p");
    private static final SelenideElement DELETE_SUITE_BUTTON = $x("//button[@aria-label='Delete suite']");
    private static final SelenideElement CREATE_NEW_SUITE_BUTTON = $x("//button[.//span[text()='Create new suite']]");

    @Step("Открыть страницу сьютов проекта {projectCode}")
    public SuitePage openSuitePage(String projectCode) {
        open("/project/" + projectCode);
        return this;
    }

    @Step("Заполнить поля для создания сьюты")
    public SuitePage fillSuiteCreationForm(SuiteRequestModel data) {
        setValueByPlaceholder("For example: Web Application", data.getTitle());
        PRECONDITIONS_INPUT.setValue(data.getPreconditions());
        DESCRIPTION.setValue(data.getDescription());
        return this;
    }

    @Step("Нажать на кнопку Create")
    public SuitePage clickCreateSuiteButton() {
        Button.clickButton("Create");
        return this;
    }

    @Step("Проверить, что сьюта успешно создана")
    public SuitePage verifySuiteCreated() {
        SUITE_LABEL.shouldBe(visible);
        return this;
    }

    @Step("Удалить сьюту")
    public SuitePage deleteSuite() {
        DELETE_SUITE_BUTTON.shouldBe(visible).click();
        Button.clickButton("Delete");
        return this;
    }

    @Step("Нажать кнопку Create new suite")
    public SuitePage clickCreateNewSuiteButton() {
        CREATE_NEW_SUITE_BUTTON.shouldBe(visible).click();
        return this;
    }

    @Step("Проверить, что сьюта успешно удалена")
    public SuitePage verifySuiteDeleted() {
        SUITE_LABEL.shouldBe(visible);
        return this;
    }
}
