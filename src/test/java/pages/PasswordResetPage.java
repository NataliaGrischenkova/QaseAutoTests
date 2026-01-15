package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class PasswordResetPage {

    private static final SelenideElement PASSWORD_RESET_TITLE = $x("//h1[normalize-space()='Reset your password']");

    public PasswordResetPage shouldBeOpened() {
        PASSWORD_RESET_TITLE.shouldBe(visible).shouldHave(text("Reset your password"));
        return this;
    }
}
