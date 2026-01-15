package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class ProjectsPage {

    private static final SelenideElement PROJECTS_PAGE_TITLE = $x("//h1[normalize-space()='Projects']");

    public ProjectsPage shouldBeOpened() {
        PROJECTS_PAGE_TITLE.shouldBe(visible).shouldHave(text("Projects"));
        return this;
    }
}
