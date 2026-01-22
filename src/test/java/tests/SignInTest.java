package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;



public class SignInTest extends BaseTest {

    @BeforeEach
    void open() {
        signInPage.openPage("/login");
    }

    @Test
    @DisplayName("Успешный вход в систему с использованием корректного Email-адреса и пароля")
    public void userShouldBeLoginWithValidLoginAndPassword() {
        signInPage
                .setEmail("hf1bg@virgilian.com")
                .setPassword("gdft1ywbo123")
                .clickSignInButton();

        projectsPage.shouldBeOpened();
    }

    @Test
    @DisplayName("Ошибка при пустом поле Email")
    void shouldShowErrorWhenEmailIsEmpty() {
        signInPage
                .setPassword("gdft1ywbo123")
                .clickSignInButton()
                .emailFieldShouldShowRequiredError("This field is required");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "hf1bg@",
            "@virgilian.com",
            "hf1bgvirgilian.com",
            " hf1bg@virgilian.com",
            "hf1bg@virgilian.com "
    })
    @DisplayName("Ошибка при Email в неверном формате")
    void shouldShowErrorForInvalidEmailFormat(String invalidEmail) {
        signInPage
                .setEmail(invalidEmail)
                .setPassword("gdft1ywbo123")
                .clickSignInButton();

        signInPage.errorAlertIsVisible();
    }

    @Test
    @DisplayName("Ошибка при пустом поле Password")
    void shouldShowErrorWhenPasswordIsEmpty() {
        signInPage
                .setEmail("hf1bg@virgilian.com")
                .clickSignInButton()
                .passwordFieldShouldShowRequiredError("This field is required");
    }

    @CsvSource({
            "hf1bg@virgilian.com, wrongPassword",
    })
    @ParameterizedTest
    void shouldRedirectToPasswordResetWhenPasswordIsInvalid (String email, String password) {
        signInPage.setEmail(email)
                .setPassword(password)
                .clickSignInButton();

        passwordResetPage.shouldBeOpened();
    }

    @Test
    @DisplayName("Ошибка при пустых полях Email и Password")
    void shouldShowErrorWhenBothFieldsAreEmpty() {
        signInPage
                .clickSignInButton()
                .emailFieldShouldShowRequiredError("This field is required")
                .passwordFieldShouldShowRequiredError("This field is required");
    }
}
