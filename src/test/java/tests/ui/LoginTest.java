package tests.ui;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import tests.BaseTest;

@Feature("Authorization")
public class LoginTest extends BaseTest {

    String errorMessage = "This field is required";

    @BeforeEach
    void open() {
        loginPage.openPage("/login");
    }

    @Test
    @DisplayName("Проверка успешного входа в систему с использованием корректного Email и Password")
    @Tags({
            @Tag("BLOCKER"),
            @Tag("UI-test")
    })
    public void userShouldBeLoginWithValidLoginAndPassword() {
        loginPage
                .setEmail(email)
                .setPassword(password)
                .clickSignInButton();

        projectsPage.shouldBeOpened();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "hf1bg@",
            "@virgilian.com",
            "hf1bgvirgilian.com",

    })
    @DisplayName("Проверка отображения ошибки при Email в неверном формате")
    @Tags({
            @Tag("BLOCKER"),
            @Tag("UI-test")
    })
    void shouldShowErrorForInvalidEmailFormat(String invalidEmail) {
        loginPage
                .setEmail(invalidEmail)
                .setPassword(password)
                .clickSignInButton();

        loginPage.errorAlertIsVisible();
    }

    @Test
    @DisplayName("Проверка отображения ошибки при пустом поле Email")
    @Tags({
            @Tag("BLOCKER"),
            @Tag("UI-test")
    })
    void shouldShowErrorWhenEmailIsEmpty() {
        loginPage
                .setPassword(password)
                .clickSignInButton()
                .emailFieldShouldShowRequiredError(errorMessage);
    }

    @Test
    @DisplayName("Проверка отображения ошибки при пустом поле Password")
    @Tags({
            @Tag("BLOCKER"),
            @Tag("UI-test")
    })
    void shouldShowErrorWhenPasswordIsEmpty() {
        loginPage
                .setEmail(email)
                .clickSignInButton()
                .passwordFieldShouldShowRequiredError(errorMessage);
    }

    @Test
    @DisplayName("Проверка отображения ошибки при пустых полях Email и Password")
    @Tags({
            @Tag("BLOCKER"),
            @Tag("UI-test")
    })
    void shouldShowErrorWhenBothFieldsAreEmpty() {
        loginPage.clickSignInButton();

        Assertions.assertAll(
                () -> loginPage.emailFieldShouldShowRequiredError(errorMessage),
                () -> loginPage.passwordFieldShouldShowRequiredError(errorMessage)
        );
    }

    @ParameterizedTest(name = "Проверка отображения ошибки при неверном Email: {0} и/или Password: {1}")
    @CsvFileSource(resources = "/testData/wrongCredentialsData.csv")
    @Tags({
            @Tag("BLOCKER"),
            @Tag("UI-test")
    })
    void alertWithErrorMessageShouldBeVisible(String email, String password) {
        loginPage.setEmail(email)
                .setPassword(password)
                .clickSignInButton();

        loginPage.errorAlertIsVisible();
    }
}
