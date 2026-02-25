package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static pages.pageElements.Input.setValueByPlaceholder;


public class LoginPage extends BasePage {

    private static final SelenideElement SIGN_IN_BUTTON = $x(
            "//button[@type='submit' and normalize-space()='Sign in']");
    private static final SelenideElement ALERT_ERROR = $x("//div[@role='alert']");
    private static final SelenideElement EMAIL_ERROR = $x("//input[@name='email']/../..//small");
    private static final SelenideElement PASSWORD_ERROR = $x("//input[@name='password']/../..//small");

    @Step("Ввести логин: {email}")
    public LoginPage setEmail(String email) {
        setValueByPlaceholder("Work email", email);
        return this;
    }

    @Step("Ввести валидный пароль {0}")
    public LoginPage setPassword(String password) {
        setValueByPlaceholder("Password", password);
        return this;
    }

    @Step("Нажать на кнопку Sign In")
    public LoginPage clickSignInButton() {
        SIGN_IN_BUTTON.click();
        return this;
    }

    @Step("Проверить отображение алерта с ошибкой")
    public void errorAlertIsVisible() {
        ALERT_ERROR.shouldBe(visible);
    }

    @Step("Проверить отображение ошибки для пустого поля Email: {expectedText}")
    public LoginPage emailFieldShouldShowRequiredError(String expectedText) {
        EMAIL_ERROR.shouldBe(visible).shouldHave(text(expectedText));
        return this;
    }

    @Step("Проверить отображение ошибки для пустого поля Password: {expectedText}")
    public LoginPage passwordFieldShouldShowRequiredError(String expectedText) {
        PASSWORD_ERROR.shouldBe(visible).shouldHave(text(expectedText));
        return this;
    }
}
