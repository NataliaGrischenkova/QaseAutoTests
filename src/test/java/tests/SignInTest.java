package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;


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
                .setEmail("hf1bg@virgilian.com")
                .setPassword("gdft1ywbo123")
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
                .setPassword("gdft1ywbo123")
                .clickSignInButton();

        signInPage.errorAlertIsVisible();
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

    @ParameterizedTest(name = "Проверка отображения ошибки при пустых полях Email: {0} и/или Password: {1}")
    @CsvFileSource(resources = "/testData/fieldsEmpty.csv")
    @Tags({
            @Tag("BLOCKER"),
            @Tag("UI-test")
    })
    void shouldShowErrorWhenFieldsEmailOrPasswordAreEmpty(String email, String password) {
        signInPage.setEmail(email)
                .setPassword(password)
                .clickSignInButton();

        signInPage.requiredFieldErrorShouldBeVisible("This field is required");
    }
}
