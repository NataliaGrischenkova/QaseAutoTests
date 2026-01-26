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
    private static final SelenideElement EMAIL_ERROR = $x(
            "//input[@name='email']/ancestor::div[contains(@class,'rgwegO')]//small");
    private static final SelenideElement PASSWORD_ERROR = $x(
            "//input[@name='password']/ancestor::div[contains(@class,'rgwegO')]//small");

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

    public SignInPage emailFieldShouldShowRequiredError(String expectedText) {
        EMAIL_ERROR.shouldBe(visible).shouldHave(text(expectedText));
        return this;
    }

    public SignInPage passwordFieldShouldShowRequiredError(String expectedText) {
        PASSWORD_ERROR.shouldBe(visible).shouldHave(text(expectedText));
        return this;
    }
}
