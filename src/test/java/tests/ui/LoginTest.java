package tests.ui;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import tests.BaseTest;

@Owner("natalia")
@Feature("Authorization UI")
@Link(value = "GitHub репозиторий проекта", url = "https://github.com/NataliaGrischenkova/QaseAutoTests")
public class LoginTest extends BaseTest {

    String errorMessage = "This field is required";

    @BeforeEach
    void openLoginPage() {
        loginPage.openPage("/login");
    }

    @Test
    @DisplayName("Проверка успешного входа в систему с использованием корректного Email и Password")
    @Story("Авторизация пользователя")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({
            @Tag("BLOCKER"),
            @Tag("UI-test")
    })
    public void userShouldBeLoginWithValidLoginAndPassword() {
        login(email, password);

        projectsPage.shouldBeOpened();
    }

    @ParameterizedTest(name = "Проверка отображения ошибки при Email в неверном формате")
    @ValueSource(strings = {
            "hf1bg@",
            "@virgilian.com",
            "hf1bgvirgilian.com",

    })
    @Story("Авторизация пользователя")
    @Severity(SeverityLevel.BLOCKER)
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
    @DisplayName("Проверка отображения ошибки при пустых полях Email и Password")
    @Story("Авторизация пользователя")
    @Severity(SeverityLevel.BLOCKER)
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
    @Story("Авторизация пользователя")
    @Severity(SeverityLevel.BLOCKER)
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
