package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


public class SignInTest extends BaseTest {

    @Test
    @DisplayName("Успешный вход в систему с использованием корректного Email-адреса и пароля")
    public void userShouldBeLoginWithValidLoginAndPassword() {
        signInPage.openPage()
                .setEmail("hf1bg@virgilian.com")
                .setPassword("gdft1ywbo123")
                .clickSignInButton();

        projectsPage.shouldBeOpened();
    }

    @Test
    @DisplayName("Ошибка при пустом поле Email")
    void shouldShowErrorWhenEmailIsEmpty() {
        signInPage.openPage()
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
        signInPage.openPage()
                .setEmail(invalidEmail)
                .setPassword("gdft1ywbo123")
                .clickSignInButton()
                .getErrorMessage("does not match format email");
    }

    @Test
    @DisplayName("Ошибка при пустом поле Password")
    void shouldShowErrorWhenPasswordIsEmpty() {
        signInPage.openPage()
                .setEmail("hf1bg@virgilian.com")
                .clickSignInButton()
                .passwordFieldShouldShowRequiredError("This field is required");
    }

    @Test
    @DisplayName("Ошибка при неверном пароле")
    void shouldShowErrorForInvalidPassword() {
        signInPage.openPage()
                .setEmail("hf1bg@virgilian.com")
                .setPassword("wrongPassword")
                .clickSignInButton();

        passwordResetPage.shouldBeOpened();
    }

    @Test
    @DisplayName("Ошибка при пустых полях Email и Password")
    void shouldShowErrorWhenBothFieldsAreEmpty() {
        signInPage.openPage()
                .clickSignInButton()
                .emailFieldShouldShowRequiredError("This field is required")
                .passwordFieldShouldShowRequiredError("This field is required");
    }
}
