package tests.ui;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import tests.BaseTest;

@Feature("Authorization")
public class SignInTest extends BaseTest {

    @BeforeEach
    void open() {
        signInPage.openPage("/login");
    }

    @Test
    @DisplayName("Проверка успешного входа в систему с использованием корректного Email и Password")
    @Tags({
            @Tag("BLOCKER"),
            @Tag("UI-test")
    })
    public void userShouldBeLoginWithValidLoginAndPassword() {
        signInPage
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
        signInPage
                .setEmail(invalidEmail)
                .setPassword(password)
                .clickSignInButton();

        signInPage.errorAlertIsVisible();
    }

    @Test
    @DisplayName("Проверка отображения ошибки при пустом поле Email")
    @Tags({
            @Tag("BLOCKER"),
            @Tag("UI-test")
    })
    void shouldShowErrorWhenEmailIsEmpty() {
        signInPage
                .setPassword(password)
                .clickSignInButton()
                .emailFieldShouldShowRequiredError("This field is required");
    }

    @Test
    @DisplayName("Проверка отображения ошибки при пустом поле Password")
    @Tags({
            @Tag("BLOCKER"),
            @Tag("UI-test")
    })
    void shouldShowErrorWhenPasswordIsEmpty() {
        signInPage
                .setEmail(email)
                .clickSignInButton()
                .passwordFieldShouldShowRequiredError("This field is required");
    }

    @Test
    @DisplayName("Проверка отображения ошибки при пустых полях Email и Password")
    @Tags({
            @Tag("BLOCKER"),
            @Tag("UI-test")
    })
    void shouldShowErrorWhenBothFieldsAreEmpty() {
        signInPage.clickSignInButton();

        Assertions.assertAll(
                () -> signInPage.emailFieldShouldShowRequiredError("This field is required"),
                () -> signInPage.passwordFieldShouldShowRequiredError("This field is required")
        );
    }

    @ParameterizedTest(name = "Проверка отображения ошибки при неверном Email: {0} и/или Password: {1}")
    @CsvFileSource(resources = "/testData/wrongCredentialsData.csv")
    @Tags({
            @Tag("BLOCKER"),
            @Tag("UI-test")
    })
    void alertWithErrorMessageShouldBeVisible(String email, String password) {
        signInPage.setEmail(email)
                .setPassword(password)
                .clickSignInButton();

        signInPage.errorAlertIsVisible();
    }
}
