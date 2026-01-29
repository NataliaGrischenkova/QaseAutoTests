package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static pages.pageElements.Input.enter;


public class SignInPage extends BasePage {

    private static final SelenideElement SIGN_IN_BUTTON = $x(
            "//button[@type='submit' and normalize-space()='Sign in']");
    private static final SelenideElement ALERT_ERROR = $x("//div[@role='alert']");
    private static final SelenideElement REQUIRED_FIELD_ERROR = $x("//div[contains(@class,'rgwegO')]//small");

    public SignInPage setEmail(String email) {
        enter("Work email", email);
        return this;
    }

    public SignInPage setPassword(String password) {
        enter("Password", password);
        return this;
    }

    public SignInPage clickSignInButton() {
        SIGN_IN_BUTTON.click();
        return this;
    }

    public void errorAlertIsVisible() {
        ALERT_ERROR.shouldBe(visible);
    }

    public SignInPage requiredFieldErrorShouldBeVisible(String expectedText) {
        REQUIRED_FIELD_ERROR.shouldBe(visible).shouldHave(text("This field is required"));
        return this;
    }
}
