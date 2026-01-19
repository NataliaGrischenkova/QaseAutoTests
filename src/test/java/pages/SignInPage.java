package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class SignInPage {

    private static final SelenideElement EMAIL_INPUT = $x("//input[@name='email']");
    private static final SelenideElement PASSWORD_INPUT = $x("//input[@name='password']");
    private static final SelenideElement SIGN_IN_BUTTON = $x(
            "//button[@type='submit' and normalize-space()='Sign in']");
    private static final SelenideElement ERROR_MESSAGE = $x(
            "//div[@role='alert']//span[contains(text(),'does not match format email')]");
    private static final SelenideElement EMAIL_ERROR = $x(
            "//input[@name='email']/ancestor::div[contains(@class,'rgwegO')]//small");
    private static final SelenideElement PASSWORD_ERROR = $x(
            "//input[@name='password']/ancestor::div[contains(@class,'rgwegO')]//small");


    public SignInPage openPage() {
        open("https://app.qase.io/login");
        return this;
    }

    public SignInPage setEmail(String email) {
        EMAIL_INPUT.setValue(email);
        return this;
    }

    public SignInPage setPassword(String password) {
        PASSWORD_INPUT.setValue(password);
        return this;
    }

    public SignInPage clickSignInButton() {
        SIGN_IN_BUTTON.click();
        return this;
    }

    public SignInPage getErrorMessage(String expectedText) {
        ERROR_MESSAGE.shouldBe(visible) .shouldHave(text(expectedText));
        return this;
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
